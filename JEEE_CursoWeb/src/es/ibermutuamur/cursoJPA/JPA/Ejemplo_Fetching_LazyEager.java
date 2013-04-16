package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.modelo.City;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Fetching", urlPatterns="/Fetching")
public class Ejemplo_Fetching_LazyEager extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Fetching_LazyEager() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fetching(request,response);
	}

	
	private void fetching(HttpServletRequest request, HttpServletResponse response){
        try {
        	response.setContentType("text/html;charset=UTF-8");
        	
        	Query queryMaxid = em.createQuery("select max(d.cityId) from City d");
        	int id = (Integer) queryMaxid.getSingleResult(); 
        	Query queryMinid = em.createQuery("select min(d.cityId) from City d");
        	int idmin = (Integer) queryMinid.getSingleResult(); 
           	
        	City ciudad1 = em.find(City.class, id);
        	
        	City ciudad2  = em.find(City.class, idmin);
        	ciudad2.getCountry();
        	
        	request.setAttribute("ciudad1", ciudad1);
        	request.setAttribute("ciudad2", ciudad2);
        	request.getRequestDispatcher("/fetch.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fetching(request,response);
	}

}
