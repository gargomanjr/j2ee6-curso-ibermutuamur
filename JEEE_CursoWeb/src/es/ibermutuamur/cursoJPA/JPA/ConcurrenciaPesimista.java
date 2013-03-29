package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Category;
import es.ibermutuamur.curso.modelo.Country;
import es.ibermutuamur.curso.modelo.Film;
import es.ibermutuamur.curso.modelo.FilmCategory;
import es.ibermutuamur.curso.modelo.FilmCategoryPK;
import es.ibermutuamur.curso.modelo.Language;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/ConcurrenciaPesimista", urlPatterns="/ConcurrenciaPesimista")
public class ConcurrenciaPesimista extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConcurrenciaPesimista() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado función</title>");
        out.println("</head>");
        out.println("<body>");
        
		lectura(request,response,out);
		lecturaForceIncremente(request,response,out);
		//escritura(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void lectura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {      	 	
        	utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_READ);
			HiloPesimista hilo = new HiloPesimista();
			hilo.start();
			out.println("PESSIMISTIC_READ");
			try {
			  System.out.println("Durmiendo hilo principal(lectura1) 5 segundos");
			  while(hilo.isAlive()){
				  System.out.println("Sigo durmiendo hilo 1...zzzz");
				  Thread.sleep(2000);
			  }
			  System.out.println("Despierta hilo principal(lectura 1) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
				ie.printStackTrace();
			}
			pais.getVersion();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error de lectura: "+e.getMessage()+"</h4>");
		}
	}
	
	
	private void lecturaForceIncremente(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {      	 	
        	utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_FORCE_INCREMENT);
			HiloPesimista hilo = new HiloPesimista();
			hilo.start();
			out.println("PESSIMISTIC_FORCE_INCREMENT");
			try {
			  System.out.println("Durmiendo hilo principal(lectura 2)");
			  while(hilo.isAlive()){
				  System.out.println("Sigo durmiendo hilo 2...zzzz");
				  Thread.sleep(2000);
			  }
			  System.out.println("Despierta hilo principal(lectura2) ");
			} catch (InterruptedException ie) {
				out.println("Error en hilo padre");
				ie.printStackTrace();
			}
			pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_FORCE_INCREMENT);
			out.println("<h4>Version pesimista force increment antes commit: "+pais.getVersion()+"</h4>");
			utx.commit();
			out.println("<h4>Version pesimista force increment antes commit: "+pais.getVersion()+"</h4>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error de lectura: "+e.getMessage()+"</h4>");
		}
	}
	
	private void escritura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
		Query minPais = em.createQuery("Select min(p.countryId) from Country p");
	    int minIdpais= (Integer) minPais.getSingleResult();		   
		Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_WRITE);
		try {      	 	
			HiloPesimista hilo = new HiloPesimista();
			hilo.start();
			out.println("PESSIMISTIC_WRITE");
			try {
			  System.out.println("Durmiendo hilo principal(escritura) 5 segundos");
			  Thread.sleep(5000);
			  System.out.println("Despierta hilo principal(escritura) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
			}
			utx.begin();
			pais.setCountry("Cuba");
			em.merge(pais);
			em.flush();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error de escritura: "+e.getMessage());		
			try {
				utx.rollback();
				utx.commit();
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
			try {
				utx.begin();
				pais = em.find(Country.class, minIdpais);
				pais.setCountry("Cuba");
				em.merge(pais);
				em.flush();
				utx.commit();
				} catch (Exception e1) {
					out.println("<h4>Error de escritura: "+e1.getMessage()+"</h4>");
					e1.printStackTrace();
					return;
				}
			out.println("<h4>Actualizado correctamente!!!!!! ");	
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado función</title>");
        out.println("</head>");
        out.println("<body>");
        
		lectura(request,response,out);
		escritura(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}

}

