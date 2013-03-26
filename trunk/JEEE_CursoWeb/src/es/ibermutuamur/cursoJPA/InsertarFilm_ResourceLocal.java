package es.ibermutuamur.cursoJPA;

import java.io.IOException;

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
@WebServlet(name="/InsertarPelicula", urlPatterns="/InsertarPelicula")
public class InsertarFilm_ResourceLocal extends HttpServlet {
	
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoWeb")
    EntityManagerFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarFilm_ResourceLocal() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarFilm();
	}

	
	private void insertarFilm(){
        try {      	
        	em = factory.createEntityManager();
        	EntityTransaction transacion = em.getTransaction();
        	transacion.begin();
        	
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
	        transacion.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarFilm();
	}

}
