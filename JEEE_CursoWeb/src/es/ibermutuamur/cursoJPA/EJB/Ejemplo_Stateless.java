package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.CalculadoraFacade;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Stateless", urlPatterns="/Stateless")
public class Ejemplo_Stateless extends HttpServlet {
	
	@EJB CalculadoraFacade calculadora; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Stateless() {
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
		
		ejemploCalculadora(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploCalculadora(PrintWriter out){
        try {      	
        	float numero1 = 25;
        	float numero2 = 25;
        	
        	out.println("<h4>Numero 1: "+numero1+"</h4>");
        	out.println("<h4>Numero 2: "+numero2+"</h4>");
        	
        	out.println("<h4>Suma: "+calculadora.add(numero1, numero2)+"</h4>");
        	out.println("<h4>Resta: "+calculadora.subtract(numero1, numero2)+"</h4>");
        	out.println("<h4>Multiplicación: "+calculadora.multiply(numero1, numero2)+"</h4>");
        	out.println("<h4>División: "+calculadora.division(numero1, numero2)+"</h4>");
        	
        	calculadora.add(1, 0);
        	calculadora.subtract(1, 0);
        	float mult = calculadora.multiply(1, 0);
        	float div = calculadora.division(1, 0);
        	System.out.println("Multiplicación: "+mult);
        	System.out.println("División: "+div);
        	
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
