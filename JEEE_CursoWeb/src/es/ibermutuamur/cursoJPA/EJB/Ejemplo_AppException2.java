package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.facades.FacadeRBTrue;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/AppException2", urlPatterns="/AppException2")
public class Ejemplo_AppException2 extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
    @EJB FacadeRBTrue facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_AppException2() {
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

        	try{
        		facade.saltaError();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			out.println("<h4>Error en el metodo "+e1.getMessage()+" <h4>");
    		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
