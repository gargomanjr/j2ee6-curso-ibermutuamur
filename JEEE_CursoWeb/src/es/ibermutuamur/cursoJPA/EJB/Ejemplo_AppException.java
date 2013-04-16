package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.facades.ExcepcionRBFalse;
import es.ibermutuamur.curso.facades.ExcepcionRBTrue;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/AppException", urlPatterns="/AppException")
public class Ejemplo_AppException extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
    @EJB ExcepcionRBTrue facade;
    @EJB ExcepcionRBFalse facade2;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_AppException() {
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
		
        exception(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void exception(PrintWriter out){
        try {
        	
        	
        	try{
        		facade.saltaError();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			out.println("<h4>Error al insertar otra entidad de pais sin transacción "+e1.getMessage()+" <h4>");
    		}
        	
        	
        	try{
        		facade2.saltaError();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			out.println("<h4>Error al insertar otra entidad de pais sin transacción "+e1.getMessage()+" <h4>");
    		}
        	
        	

        	if(utx == null){
        		utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        	}
        	
        	utx.begin();

        	
        	try{
        		facade2.saltaError();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			out.println("<h4>Error al insertar otra entidad de pais sin transacción "+e1.getMessage()+" <h4>");
    		}
        	
        	
        	try{
        		facade.saltaError();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			out.println("<h4>Error al insertar otra entidad de pais sin transacción "+e1.getMessage()+" <h4>");
    		}

        	
	        Country pais = new Country();
	        pais.setCountry("Japón");
	        em.flush();
	        em.persist(pais);

	        utx.commit();
	        out.println("<h4>Insertada entidad de pais id "+pais.getCountryId()+" <h4>");
	        
	        
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
