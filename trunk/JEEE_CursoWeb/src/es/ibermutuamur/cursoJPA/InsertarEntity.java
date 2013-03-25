package es.ibermutuamur.cursoJPA;

import java.io.IOException;

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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/InsertarEntity", urlPatterns="/InsertarEntity")
public class InsertarEntity extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoEJB")
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoEJB")
    EntityManagerFactory factory;
    @Resource (name="UserTransaction")          private   UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEntity() {
        super();
        //insertarPais();
        
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
        try {
        	if(factory== null && em == null){
				factory = Persistence.createEntityManagerFactory("JEEE_CursoEJB");
				em = factory.createEntityManager();
        	}
        	if(factory== null && em == null){
				factory = Persistence.createEntityManagerFactory("JEEE_CursoWeb");
				em = factory.createEntityManager();
        	}
        	em = factory.createEntityManager();
        	if(utx == null){
        		UserTransaction utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        	}
        	utx.begin();
			//+
        	//EntityTransaction transacion = em.getTransaction();
	        Country pais = new Country();
	        pais.setCountry("Españá");
	        //pais.setCountryId(1);
	        //em.flush();
	        em.persist(pais);
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
