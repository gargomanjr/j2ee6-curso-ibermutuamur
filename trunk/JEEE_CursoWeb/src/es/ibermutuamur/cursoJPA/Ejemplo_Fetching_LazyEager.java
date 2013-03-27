package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.City;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Fetching", urlPatterns="/Fetching")
public class Ejemplo_Fetching_LazyEager extends HttpServlet {
	
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoWeb")
    EntityManagerFactory factory;
       
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
            PrintWriter out = response.getWriter();
        	em = factory.createEntityManager();
        	
        	Query queryMaxid = em.createQuery("select max(d.cityId) from City d");
        	int id = (Integer) queryMaxid.getSingleResult(); 
        	Query queryMinid = em.createQuery("select min(d.cityId) from City d");
        	int idmin = (Integer) queryMinid.getSingleResult(); 
        	
        	
        	Query query = em.createQuery("Select c from City c where c.cityId = :id");
        	query.setParameter("id", id);
        	
        	City ciudad1 = (City) query.getSingleResult(); 
        	
        	Query query2 = em.createQuery("Select c from City c where c.cityId = :id");
        	query2.setParameter("id", idmin);
        	
        	City ciudad2  = (City) query2.getSingleResult(); 
        	ciudad2.getCountry();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Número de ciudades: "+ "</h1>");

	        out.println("<h2>Ciudad 1 "+ciudad1.getCity()+" pais:</h2>");
	        if(ciudad1.getCountry()==null){
	        	out.println("<h2>Es nulo</h2>");
	        }
	        else{
	        	out.println("<h2>" + ciudad1.getCountry().getCountry()+"</h2>");
	        }
	        out.println("<h2>Ciudad 2 "+ciudad2.getCity()+" pais:</h2>");
	        if(ciudad2.getCountry()==null){
	        	out.println("<h2>Es nulo</h2>");
	        }
	        else{
	        	out.println("<h2>" + ciudad2.getCountry().getCountry()+"</h2>");
	        }

           // }
            out.println("</body>");
            out.println("</html>");

		} catch (Exception e) {
			e.printStackTrace();
		}
        Country pais1 = new Country();
        pais1.setCountry("Francia");
        em.persist(pais1);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fetching(request,response);
	}

}
