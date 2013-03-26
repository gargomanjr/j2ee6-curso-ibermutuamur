package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
@WebServlet(name="/InsertarPeliculaOKJTA", urlPatterns="/InsertarPeliculaOKJTA")
public class InsertarFilm_JTAOK extends HttpServlet {
	
    EntityManager em;
    UserTransaction utx; 
    EntityManagerFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarFilm_JTAOK() {
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
        	

        	factory = Persistence.createEntityManagerFactory("JEEE_CursoEJB");
			em = factory.createEntityManager();
			utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        	        	
        	Film pelicula = new Film();
        	pelicula.setDescription("Gladiator");
        	pelicula.setTitle("Gladiator");
        	pelicula.setRentalRate(new BigDecimal(3.5));
        	pelicula.setRentalDuration((byte)3);
        	Date d = new Date();
        	pelicula.setLastUpdate(d);
        	pelicula.setReplacementCost(new BigDecimal(33.5));
        	Language idioma = new Language();
        	idioma.setName("Español");
        	pelicula.setLanguage1(idioma);
        	FilmCategory genero = new FilmCategory();
        	Category category = em.find(Category.class,(byte) 1);   
        	genero.setCategory(category);
        	
        	//----------------------
        	utx.begin();
        	em.persist(idioma);
        	em.flush();
        	utx.commit(); 
	        
        	utx.begin();  
        	pelicula.setLanguage1(idioma);
        	em.persist(pelicula);
        	em.flush();
        	utx.commit();
        	
        	utx.begin();
	        genero.setFilm(pelicula);
        	em.persist(genero);
        	em.flush();
        	utx.commit();
        	//------------------------
        	utx.commit();
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
