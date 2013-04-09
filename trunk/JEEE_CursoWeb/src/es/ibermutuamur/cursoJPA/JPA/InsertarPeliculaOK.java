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
        out.println("<title>Resultado función</title>");
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
        	
        	//Opción 1
        	Language idioma = new Language();
        	idioma.setName("Español");   
        	idioma.setLastUpdate(d);
        	em.persist(idioma);
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
	        FilmCategoryPK pkfilmc = new FilmCategoryPK();
        	FilmCategory genero = new FilmCategory();
        	Category category = em.find(Category.class, 1);   
        	pkfilmc.setCategoryId(category.getCategoryId());
        	pkfilmc.setFilmId(pelicula.getFilmId());
        	genero.setId(pkfilmc);
        	em.persist(genero);
        	utx.commit();
        	out.println("<h4>Insertada entidad película " + pelicula.getTitle() +" con id " + pelicula.getFilmId()+"</h4>");
        	
		} catch (Exception e) {
			out.println("<h4>Error al insertar película Opcion 1 </h4>" + e.toString());
			e.printStackTrace();
		}	
        try{
        	Date d = new Date();    
        	//Opción 2	
        	utx.begin();     	
        	Language idioma1 = new Language();
        	idioma1.setName("Español1");   
        	idioma1.setLastUpdate(d);
        	em.persist(idioma1);
	        em.flush();
        	utx.commit(); 
	        
        	utx.begin();  
        	Film pelicula2 = new Film();
        	pelicula2.setDescription("Troya");
        	pelicula2.setTitle("Troya");
        	pelicula2.setRentalRate(new BigDecimal(3.5));
        	pelicula2.setRentalDuration((byte)3);
        	pelicula2.setLastUpdate(d);
        	pelicula2.setReplacementCost(new BigDecimal(33.5));
        	pelicula2.setLanguage1(idioma1);
        	pelicula2.setLanguage1(idioma1);
        	em.persist(pelicula2);
        	utx.commit(); 
        	
        	utx.begin();
        	em.flush();
	        FilmCategoryPK pkfilmc2 = new FilmCategoryPK();
        	FilmCategory genero2 = new FilmCategory();
        	Category category = em.find(Category.class, 1);   
        	pkfilmc2.setCategoryId(category.getCategoryId());
        	pkfilmc2.setFilmId(pelicula2.getFilmId());
        	genero2.setId(pkfilmc2);
        	em.persist(genero2);
        	em.flush();
        	utx.commit();
        	out.println("<h4>Insertada entidad película " + pelicula2.getTitle() +" con id " + pelicula2.getFilmId()+"</h4>");
  	
        	//------------------------
		} catch (Exception e) {
			out.println("<h4>Error al insertar película Opcion 2 </h4>" + e.toString());
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
