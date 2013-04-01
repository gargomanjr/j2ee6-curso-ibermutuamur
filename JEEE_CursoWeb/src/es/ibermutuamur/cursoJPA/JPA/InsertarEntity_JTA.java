package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
@WebServlet(name="/InsertarEntityJTA", urlPatterns="/InsertarEntityJTA")
public class InsertarEntity_JTA extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @PersistenceUnit(unitName="JEEE_CursoWeb")
    EntityManagerFactory factory;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarEntity_JTA() {
        super();
        //insertarPais();
        
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
        	if(factory== null && em == null){
				factory = Persistence.createEntityManagerFactory("JEEE_CursoEJB");
				em = factory.createEntityManager();
        	}
        	if(utx == null){
        		utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        	}
        	
        	utx.begin();

	        Country pais = new Country();
	        pais.setCountry("Italia");
	        em.flush();
	        em.persist(pais);

	        utx.commit();
	        out.println("<h4>Insertada entidad de pais id "+pais.getCountryId()+" <h4>");
	        
	        Country pais1 = new Country();
	        pais1.setCountry("Francia");
	        em.persist(pais1);
	        
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h4>Error al insertar otra entidad de pais sin transacción "+e.getMessage()+" <h4>");
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
