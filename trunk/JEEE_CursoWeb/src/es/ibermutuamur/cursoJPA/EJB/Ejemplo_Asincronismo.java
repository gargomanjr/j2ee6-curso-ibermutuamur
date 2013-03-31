package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.CustomerFacadeLocal;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Asincronismo", urlPatterns="/Asincronismo")
public class Ejemplo_Asincronismo extends HttpServlet {
	
	@EJB CustomerFacadeLocal facade; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Asincronismo() {
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
		
		ejemploAsincronismo(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploAsincronismo(PrintWriter out){
        try {      	
        	
        	out.println("<h4>Comienza la ejecución</h4>");

        	Future<Integer> resultado = facade.fibonacci(40);
        	while(resultado.isDone()==false){
        		out.println("<h4>Seguimos ejecutando...</h4>");
        		System.out.println("<h4>Seguimos ejecutando...</h4>");
        		Thread.sleep(500);
        	}
        	out.println("<h4>Resultado final..."+resultado.get()+"</h4>");
        	resultado.get();
        	
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
