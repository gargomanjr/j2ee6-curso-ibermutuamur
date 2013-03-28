package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
@WebServlet(name="/InsertarPeliculaOK", urlPatterns="/InsertarPeliculaOK")
public class InsertarFilm_ResourceLocalOK extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarFilm_ResourceLocalOK() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarPais();
	}

	
	private void insertarPais(){
        try {      	
        	Date d = new Date();    	
        	utx.begin();  
        	
        	Language idioma = new Language();
        	idioma.setName("Español");   
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
        	//------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = 0;
		i++;
		insertarPais();
	}

}
