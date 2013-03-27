package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.modelo.*;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Vista", urlPatterns="/Vista")
public class Ejemplo_Vista extends HttpServlet {
	
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoWeb")
    EntityManagerFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Vista() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pruebaVista( request,  response);
	}

	
	private void pruebaVista(HttpServletRequest request, HttpServletResponse response){
        try {      	
        	em = factory.createEntityManager();
        	
            Query query = em.createQuery("select f from FilmList f");
            List<FilmList> listaPelis = query.getResultList();
        	       	
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
        	
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            if(listaPelis!=null){
            	for(int i =0;i<listaPelis.size()&&i<10;i++){
            		out.println("<h2>Peli: "+i+" " + listaPelis.get(i).getTitle() + "</h2>");
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
