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

//Mirar si los lock tienen que ir dentro de transacciones para que hagan su efecto.


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
		
		em.setProperty("javax.persistence.lock.timeout", 4000);
		
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado función</title>");
        out.println("</head>");
        out.println("<body>");
        
		lectura(request,response,out);
		lecturaForceIncrement(request,response,out);
		escritura(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}

	/***
	 * El hilo hijo se queda esperando coger la fila bloqueada (flush) 
	 * para sincronizar y cuando se libere commit grabará
	 */
	private void lectura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {      	 	
        	utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_READ);
			pais.getCountry();
			HiloPesimista hilo = new HiloPesimista(out);
			hilo.start();
			out.println("<h3>PESSIMISTIC_READ<h3>");
			try {
			  System.out.println("Durmiendo hilo principal(lectura 1) 6 segundos");
			  Thread.sleep(6000);
			  System.out.println("Despierta hilo principal(lectura 1) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
				ie.printStackTrace();
			}
			utx.commit();
			out.println("El padre libera el lock");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error de lectura: "+e.getMessage()+"</h4>");
		}
	}
	
	/***
	 * El hilo hijo se queda esperando coger la fila bloqueada (flush) 
	 * para sincronizar y cuando se libere fallara al tener distinta versión 
	 * por el force increment
	 */
	private void lecturaForceIncrement(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
        	utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_FORCE_INCREMENT);
			pais.getCountry();
			HiloPesimista hilo = new HiloPesimista(out);
			hilo.start();
			out.println("<h3>PESSIMISTIC_FORCE_INCREMENT</h3>");
			try {
			  System.out.println("Durmiendo hilo principal(lectura 2)");
			  Thread.sleep(6000);
			  System.out.println("Despierta hilo principal(lectura2) ");
			} catch (InterruptedException ie) {
				out.println("Error en hilo padre");
				ie.printStackTrace();
			}
			out.println("<h4>Version pesimista force increment antes commit: "+pais.getVersion()+"</h4>");	
			utx.commit();
			out.println("<h4>Version pesimista force increment despues commit: "+pais.getVersion()+"</h4>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error de lectura: "+e.getMessage()+"</h4>");
		}
	}
	
	/***
	 * El hilo hijo se queda esperando coger la fila bloqueada (flush) 
	 * para sincronizar y cuando se libere fallara al tener distinta versión 
	 * al grabar datos el padre.
	 */
	private void escritura(HttpServletRequest request, HttpServletResponse response,PrintWriter out){

		try {  
			utx.begin();
			Query minPais = em.createQuery("Select min(p.countryId) from Country p");
		    int minIdpais= (Integer) minPais.getSingleResult();		   
			Country pais = em.find(Country.class, minIdpais,LockModeType.PESSIMISTIC_WRITE);
			pais.setCountry("Cuba");
			em.merge(pais);
			em.flush();
			HiloPesimista hilo = new HiloPesimista(out);
			hilo.start();
			out.println("<h3>PESSIMISTIC_WRITE</h3>");
			try {
			  System.out.println("Durmiendo hilo principal(escritura) 5 segundos");
			  Thread.sleep(5000);
			  System.out.println("Despierta hilo principal(escritura) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
			}
			utx.commit();
			out.println("Actualizo hilo padre");
		   } catch (Exception e) {
			e.printStackTrace();
			out.println("Error de escritura: "+e.getMessage());	
			}	
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		em.setProperty("javax.persistence.lock.timeout", 4000);
		
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

