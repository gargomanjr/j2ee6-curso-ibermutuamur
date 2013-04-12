package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;

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
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Country;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/ConcurrenciaOptimista2", urlPatterns="/ConcurrenciaOptimista2")
public class CopyOfConcurrenciaOptimista extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopyOfConcurrenciaOptimista() {
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
        
		//lectura(request,response,out);
		escritura2(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void lectura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {      	 	
        	
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.OPTIMISTIC);
			HiloOptimista hilo = new HiloOptimista();
			hilo.start();
			System.out.println("Continua hilo principal");
			try {
			  System.out.println("Durmiendo hilo principal(lectura) 5 segundos");
			  Thread.sleep(5000);
			  System.out.println("Despierta hilo principal(lectura) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
				ie.printStackTrace();
			}
			out.println("<h4>OPTIMISTIC_FORCE_INCREMENT</h4>");
			utx.begin();
			pais = em.find(Country.class, minIdpais,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			out.println("<h4>Valor del pais antes de commit: "+pais.getVersion()+"</h4>");
			utx.commit();
			out.println("<h4>Version después del commit de lectura: "+pais.getVersion()+"</h4>");	
        	//------------------------
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error de lectura: "+e.getMessage()+"</h4>");
		}
	}
	
	
	private void escritura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
		Query minPais = em.createQuery("Select min(p.countryId) from Country p");
	    int minIdpais= (Integer) minPais.getSingleResult();		   
		Country pais = em.find(Country.class, minIdpais);
		try {      	 	
			HiloOptimista hilo = new HiloOptimista();
			hilo.start();
			System.out.println("Continua hilo principal");
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
	
	
	private void escritura2(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
		try {   
			utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais);
			out.println("Version "+ pais.getVersion());
			/////////////////////////////////////////////////
			HiloOptimista hilo = new HiloOptimista();
			hilo.start();
			System.out.println("Continua hilo principal");
			try {
			  System.out.println("Durmiendo hilo principal(escritura) 5 segundos");
			  Thread.sleep(5000);
			  System.out.println("Despierta hilo principal(escritura) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
			}
			//em.detach(pais);
			em.refresh(pais);
			out.println("Version "+ pais.getVersion());
			em.refresh(pais);
			out.println("Version "+ pais.getVersion());
			em.refresh(pais);
			out.println("Version "+ pais.getVersion());
			/*pais = em.merge(pais);
			out.println("Version "+ pais.getVersion());*/
			/*em.refresh(pais);
			//em.refresh(pais);
			pais.setCountry("Australia");
			em.merge(pais);*/
			utx.commit();
			
			utx.begin();
			em.refresh(pais);
			out.println("Version2 "+ pais.getVersion());
			utx.commit();
			out.println("<h4>Actualizado correctamente!!!!!! ");	
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error de escritura: "+e.getMessage());		
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

