package es.ibermutuamur.cursoJPA.EJB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.ShoppingCart;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Stateful", urlPatterns="/Stateful")
public class Ejemplo_Stateful extends HttpServlet {
	
	@EJB ShoppingCart cart; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Stateful() {
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
		
		ejemploShoppingCart(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploShoppingCart(PrintWriter out){   	
	        try
	        {
			 if(cart==null){
				 InitialContext ctx = new InitialContext();
				 cart = (ShoppingCart) ctx.lookup("ShoppingCartBean ");
			 }
        	 out.println("<h4>Buying 1 memory stick</h4>");
             cart.buy("Memory stick", 1);
             out.println("<h4>Buying another memory stick</h4>");
             cart.buy("Memory stick", 1);

             out.println("<h4>Buying a laptop</h4>");
             cart.buy("Laptop", 1);

             out.println("<h4>Print cart:</h4>");
             HashMap<String, Integer> fullCart = cart.getCartContents();
             for (String product : fullCart.keySet())
             {
                out.println("<h4>"+fullCart.get(product) + "     " + product+"</h4>");
             }

             out.println("<h4>Checkout</h4>");
             cart.checkout();

             out.println("<h4>Should throw an object not found exception by invoking on cart after @Remove method</h4>");
                cart.getCartContents();   	  	
			 } catch (Exception e) {
				e.printStackTrace();
				out.println("<h4>"+e.getMessage()+"</h4>");
			 }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
