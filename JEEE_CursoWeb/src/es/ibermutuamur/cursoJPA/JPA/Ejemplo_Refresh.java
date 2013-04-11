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
import es.ibermutuamur.curso.modelo.Language;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Refresh", urlPatterns="/Refresh")
public class Ejemplo_Refresh extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Refresh() {
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
        
		ejemplo_Refresh(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}
	
	
	private void ejemplo_Refresh(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
		try {  
			utx.begin();
			Query maxLenguage = em.createQuery("Select max(l.languageId) from Language l");
		    int maxLenguag= (Integer) maxLenguage.getSingleResult();		   
			Language lenguaje = em.find(Language.class, maxLenguag);
			out.println("<h4>" +lenguaje.getName()+" original </h4>");
			System.out.println("<h4>" +lenguaje.getName()+" original de la entidad </h4>");
			//////////////////////////////////////////////
			HiloRefresh hilo = new HiloRefresh();
			hilo.start();
			//////////////////////////////////////////////
			System.out.println("Continua hilo principal");
			try {
			  System.out.println("Durmiendo hilo principal(escritura) 5 segundos");
			  Thread.sleep(5000);
			  System.out.println("Despierta hilo principal(escritura) ");
			} catch (InterruptedException ie) {
				System.out.println("Error en hilo padre");
			}
			out.println("<h4>" +lenguaje.getName()+" despues de actualizar el hilo </h4>");
			lenguaje.setName("Prueba");						
			out.println("<h4>" +lenguaje.getName()+" seteamos nuesta entidad </h4>");
			em.refresh(lenguaje);
			out.println("<h4>" +lenguaje.getName()+" recuperamos el valor que puso el hilo con refresh (actual de BBDD) </h4>");
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
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

