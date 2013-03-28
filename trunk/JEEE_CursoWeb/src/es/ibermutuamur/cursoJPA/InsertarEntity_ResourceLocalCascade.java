package es.ibermutuamur.cursoJPA;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.City;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarEntityCascade", urlPatterns="/InsertarEntityCascade")
public class InsertarEntity_ResourceLocalCascade extends HttpServlet {
	
    //@PersistenceContext(unitName="JEEE_CursoWeb")
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEntity_ResourceLocalCascade() {
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
        	utx.begin();
        	
	        Country pais = new Country();
	        pais.setCountry("Alemania");
	        
	        City ciudad = new City();
	        ciudad.setCountry(pais);
	        ciudad.setCity("Hamburgo");
	        em.persist(ciudad);
	        
	        
	        utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
        Country pais1 = new Country();
        pais1.setCountry("Francia");
        em.persist(pais1);
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
