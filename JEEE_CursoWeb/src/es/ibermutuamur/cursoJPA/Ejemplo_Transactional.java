package es.ibermutuamur.cursoJPA;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Transactional", urlPatterns="/Transactional")
public class Ejemplo_Transactional extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Transactional() {
        super();      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarPais();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private void insertarPais(){
        try {      	
        	//EntityTransaction transacion = em.getTransaction();
        	//transacion.begin();
        	
	        Country pais = new Country();
	        pais.setCountry("Portugal");
	        em.persist(pais);
	        
	       // transacion.commit();

	        Country pais1 = new Country();
	        pais1.setCountry("Brasil");
	        em.persist(pais1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insertarPais();
	}

}
