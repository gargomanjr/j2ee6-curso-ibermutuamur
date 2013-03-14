package es.ibermutuamur.cursoJPA;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarEntity", urlPatterns="/InsertarEntity")
public class InsertarEntity extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEntity() {
        super();

        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = 0;
		i++;
		insertarPais();
	}

	
	private void insertarPais(){
        em.getTransaction().begin();
        Country pais = new Country();
        pais.setCountry("Españá");
        pais.setCountryId(1);
        em.flush();
        em.getTransaction().commit();
        
        Country pais1 = new Country();
        pais1.setCountry("Francia");
        em.flush();
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
