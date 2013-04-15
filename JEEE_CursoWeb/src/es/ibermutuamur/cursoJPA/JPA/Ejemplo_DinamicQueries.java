package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.modelo.Film;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/DQueries", urlPatterns="/DQueries")
public class Ejemplo_DinamicQueries extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_DinamicQueries() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dqueries( request,  response);
	}

	
	private void dqueries(HttpServletRequest request, HttpServletResponse response){
        try {      	        	
            CriteriaBuilder qb = em.getCriteriaBuilder();
            CriteriaQuery<Film> query = qb.createQuery(Film.class);
            Root<Film> film = query.from(Film.class);
            query.where(qb.like(film.<String> get("title"), "A%"));
            List<Film> result = em.createQuery(query).getResultList();
                 	      	
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h2> Referencia http://wiki.eclipse.org/EclipseLink/Examples/JPA/2.0/Criteria </h2>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            if(result!=null){
            	for(int i =0;i<result.size()&&i<10;i++){
            		out.println("<h4>Peli: "+i+" " + result.get(i).getTitle() + " " 
            				+ result.get(i).getDescription() + " "
            				+ result.get(i).getLanguage1().getName() + " "
            				+ result.get(i).getReleaseYear().toString() + " "
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
	}

}
