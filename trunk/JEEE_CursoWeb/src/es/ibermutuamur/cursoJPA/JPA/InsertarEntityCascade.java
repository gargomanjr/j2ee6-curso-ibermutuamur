package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.City;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarEntityCascade", urlPatterns="/InsertarEntityCascade")
public class InsertarEntityCascade extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEntityCascade() {
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
		
        insertarPais(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void insertarPais(PrintWriter out){
        try {      	
        	utx.begin();
        	
	        Country pais = new Country();
	        pais.setCountry("Alemania");
	        
	        City ciudad = new City();
	        ciudad.setCountry(pais);
	        ciudad.setCity("Hamburgo");
	        em.persist(ciudad);
	        em.flush();
	        utx.commit();
	        
	        out.println("Insertada Ciudad Hamburgo con id " + ciudad.getCityId() + " y Pais Alemania con id "+pais.getCountryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
