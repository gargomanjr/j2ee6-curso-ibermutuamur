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

import es.ibermutuamur.curso.modelo.Category;
import es.ibermutuamur.curso.modelo.Film;
import es.ibermutuamur.curso.modelo.FilmCategory;
import es.ibermutuamur.curso.modelo.Language;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarPeliculaFail", urlPatterns="/InsertarPeliculaFail")
public class InsertarPeliculaFail extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPeliculaFail() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarFilm(request,response);
	}

	
	private void insertarFilm(HttpServletRequest request, HttpServletResponse response){
        try {      	
        	utx.begin();
        	
        	Film pelicula = new Film();
        	pelicula.setDescription("Gladiator");
        	pelicula.setTitle("Gladiator");
        	Language idioma = new Language();
        	idioma.setName("Español");
        	pelicula.setLanguage1(idioma);
        	FilmCategory genero = new FilmCategory();
        	Category category = new Category();
        	category.setCategoryId((byte)1);         // Evitamos tener que buscar en la tabla category
        	genero.setCategory(category);
        	//-----------------------
        	em.persist(pelicula);
        	//------------------------
	        utx.commit();
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=null;
			try {
				out = response.getWriter();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        	
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");

            out.println(e);

            out.println("</body>");
            out.println("</html>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarFilm(request,response);
	}

}
