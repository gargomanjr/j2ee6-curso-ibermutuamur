package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.*;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Funciones", urlPatterns="/Funciones")
public class Funciones_Procedimientos extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funciones_Procedimientos() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pruebaFuncion( request,  response);
	}

	
	private void pruebaFuncion(HttpServletRequest request, HttpServletResponse response){
        try {      	
        	
        	Query queryMaxid = em.createQuery("select max(d.countryId) from Country d");
        	int id = (Integer) queryMaxid.getSingleResult();
        	
        	Query queryMaxCity = em.createQuery("select max(d.cityId) from City d");
        	int idcity = (Integer) queryMaxCity.getSingleResult(); 
        	
            Query query = em.createNativeQuery("select numero_ciudades_pais (?pais) from dual");
            query.setParameter("pais", id);
            int numero_ciudades = (Integer) query.getSingleResult();
        	       	
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
        	
            DescripcionCiudad result=null;
            try{
            	Query query2 = em.createNamedQuery("descripcionCiudad");
            	query2.setParameter("idCiudad", idcity);
            	result = (DescripcionCiudad) query2.getSingleResult();
            	
            }
            catch(Exception e){
            	System.out.println(e.getMessage());
            	e.printStackTrace();
            }
            try{
            	Query query2 = em.createNamedQuery("descripcionCiudad2");
            	query2.setParameter("idCiudad", idcity);
            	result = (DescripcionCiudad) query2.getSingleResult();
            	
            }
            catch(Exception e){
            	System.out.println(e.getMessage());
            	e.printStackTrace();
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Número de ciudades: " + numero_ciudades + "</h1>");
            if(result!=null){
	            out.println("<h2>Descripción ciudad: " + result.getCiudad() + "</h2>");
	            out.println("<h2>Descripción pais: " + result.getPais() + "</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pruebaFuncion(request,response);
	}

}
