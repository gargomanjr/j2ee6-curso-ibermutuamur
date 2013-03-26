package es.ibermutuamur.cursoJPA;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
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
@WebServlet(name="/InsertarPeliculaOK", urlPatterns="/InsertarPeliculaOK")
public class InsertarFilm_ResourceLocalOK extends HttpServlet {
	
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoWeb")
    EntityManagerFactory factory;
       
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
        	em = factory.createEntityManager();
        	EntityTransaction transacion = em.getTransaction();
        	
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
        	transacion.begin();
        	em.persist(idioma);
        	em.flush();
        	em.refresh(idioma);
	        transacion.commit(); 
	        
        	transacion.begin();  
        	pelicula.setLanguage1(idioma);
        	em.persist(pelicula);
        	em.flush();
	        transacion.commit();
        	
	        transacion.begin();
	        genero.setFilm(pelicula);
        	em.persist(genero);
        	em.flush();
	        transacion.commit();
        	//------------------------
	        transacion.commit();
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
