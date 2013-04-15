package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.config.QueryHints;

import es.ibermutuamur.curso.modelo.Film;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/FetchGroup", urlPatterns="/FetchGroup")
public class Ejemplo_FetchGroup extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_FetchGroup() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pruebaVista( request,  response);
	}

	
	@SuppressWarnings("unchecked")
	private void pruebaVista(HttpServletRequest request, HttpServletResponse response){
        try {      	        	
            Query query = em.createQuery("select f from Film f");
            query.setMaxResults(10);
            query.setHint(QueryHints.FETCH_GROUP_NAME, "names");
            List<Film> listaPelis = query.getResultList();
        	      	
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
        	
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2> Referencia http://wiki.eclipse.org/EclipseLink/Examples/JPA/AttributeGroup </h2>");
            if(listaPelis!=null){
            	for(int i =0;i<listaPelis.size()&&i<10;i++){
            		out.println("<h4>Peli: "+i+" " + listaPelis.get(i).getTitle() + " " 
            				+ listaPelis.get(i).getDescription() + " "
            				+ listaPelis.get(i).getLanguage1().getName() + " "
            				+ listaPelis.get(i).getReleaseYear().toString() + " "
            				+ "</h4>");
	            }
            }
            out.println("</body>");
            out.println("</html>");
        	
        	
        	//------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pruebaVista(request,response);
	}

}
