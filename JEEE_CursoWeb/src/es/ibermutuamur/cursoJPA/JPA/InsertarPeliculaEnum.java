package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import es.ibermutuamur.curso.modelo.Category;
import es.ibermutuamur.curso.modelo.Film;
import es.ibermutuamur.curso.modelo.Film.Features;
import es.ibermutuamur.curso.modelo.FilmCategory;
import es.ibermutuamur.curso.modelo.FilmCategoryPK;
import es.ibermutuamur.curso.modelo.Language;



/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarPeliculaEnum", urlPatterns="/InsertarPeliculaEnum")
public class InsertarPeliculaEnum extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarPeliculaEnum() {
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
		
		enumerados(request,response);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void enumerados(HttpServletRequest request, HttpServletResponse response){
        try {      	
        	 
        	
        	Date aux = new Date();
        	
        	Date d = new Date(aux.getTime()-10000); 
        	
        	Query q = em.createQuery("Select max(l.languageId) from Language l");
        	int l_id = (Integer) q.getSingleResult();
        	
        	Language idioma = em.find(Language.class, l_id);
        	
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
        	
        	List<Features> array= new ArrayList<Features>();
        	array.add(Features.Trailers);
        	array.add(Features.Commentaries);
			pelicula.setSpecialFeatures(Features.Deleted);
        	
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
        	
        	Query q1 = em.createQuery("Select min(l.filmId) from Film l");
        	int filmId = (Integer) q1.getSingleResult();
        	Film peli = em.find(Film.class,filmId);
        	
        	response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado función</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dias Transcurridos: " + peli.getDiasDesdeUltAct() + "</h1>");
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
		enumerados(request,response);
	}

}
