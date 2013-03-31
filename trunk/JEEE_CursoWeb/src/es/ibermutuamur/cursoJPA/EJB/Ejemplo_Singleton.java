package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.Ej_Singleton;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Singleton", urlPatterns="/Singleton")
public class Ejemplo_Singleton extends HttpServlet {
	
	@EJB Ej_Singleton singleton; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Singleton() {
        super();      
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
		
        ejemploSingleton(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploSingleton(PrintWriter out){
        try {      	

        	out.println("<h4>Estado inicial: "+singleton.getStatus()+"</h4>");
        	Date d = new Date();
        	singleton.setStatus("Nuevo Estado, fecha: " + d.toString());
        	out.println("<h4>Nuevo Estado inicial: "+singleton.getStatus()+"</h4>");    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
