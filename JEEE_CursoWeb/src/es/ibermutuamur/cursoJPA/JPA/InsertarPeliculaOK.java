package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

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
import es.ibermutuamur.curso.modelo.FilmCategoryPK;
import es.ibermutuamur.curso.modelo.Language;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarPeliculaOK", urlPatterns="/InsertarPeliculaOK")
public class InsertarPeliculaOK extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPeliculaOK() {
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
        out.println("<title>Resultado funci�n</title>");
        out.println("</head>");
        out.println("<body>");
		
        insertarPais(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void insertarPais(PrintWriter out){
        try {      	
        	Date d = new Date();    	
        	utx.begin();  
        	
        	Language idioma = new Language();
        	idioma.setName("Espa�ol");   
        	idioma.setLastUpdate(d);
        	em.persist(idioma);
        	utx.commit(); 
	        
        	utx.begin();  
        	Film pelicula = new Film();
        	pelicula.setDescription("Gladiator");
        	pelicula.setTitle("Gladiator");
        	pelicula.setRentalRate(new BigDecimal(3.5));
        	pelicula.setRentalDuration((byte)3);
        	pelicula.setLastUpdate(d);
        	pelicula.setReplacementCost(new BigDecimal(33.5));
        	pelicula.setLanguage1(idioma);
        	pelicula.setLanguage1(idioma);
        	em.persist(pelicula);
        	utx.commit();
        	
        	utx.begin();
	        FilmCategoryPK pkfilmc = new FilmCategoryPK();
        	FilmCategory genero = new FilmCategory();
        	Category category = em.find(Category.class, 1);   
        	pkfilmc.setCategoryId(category.getCategoryId());
        	pkfilmc.setFilmId(pelicula.getFilmId());
        	genero.setId(pkfilmc);
        	em.persist(genero);
        	em.flush();
        	utx.commit();
        	out.println("Insertada entidad pel�cula con id " + pelicula.getFilmId());
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
