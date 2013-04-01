package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.PruebaWS;
import es.ibermutuamur.curso.facades.PruebaWSService;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/ejemploCallWS", urlPatterns="/ejemploCallWS")
public class Call_WS extends HttpServlet {
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Call_WS() {
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
		
        ejemploCallWS(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploCallWS(PrintWriter out){
        try {      	
        	
        	out.println("<h4>Comienza la ejecución</h4>");

            PruebaWSService service = new PruebaWSService();
            PruebaWS port = service.getPruebaWSServicePort();
            out.println("<h4>Llamamos al WS...</h4>");
            int numAleatorio = (int) (Math.random()*100+1);
            int id  = 1000+numAleatorio;
            port.sendMensaje(id, "WS-JMS");
            out.println("<h4>Termina la llamada al WS</h4>");        	
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
