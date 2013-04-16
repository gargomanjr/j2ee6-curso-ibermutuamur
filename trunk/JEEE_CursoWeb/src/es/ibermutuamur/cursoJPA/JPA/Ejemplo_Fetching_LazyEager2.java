package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.ibermutuamur.curso.facades.CityFacadeBeanLocal;
import es.ibermutuamur.curso.modelo.City;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Fetching2", urlPatterns="/Fetching2")
public class Ejemplo_Fetching_LazyEager2 extends HttpServlet {
	
	@EJB CityFacadeBeanLocal facade; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Fetching_LazyEager2() {
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
        
        fetching2(request,response,out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void fetching2(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
        	out.println("<h2> Sin Fech <h2>");
        	List<City> ciudades1 = facade.listaCiudades();
        	imprime_ciudades(ciudades1,out);
        	out.println("<h2> Con Fech <h2>");
        	List<City> ciudades2 = facade.listaCiudadesFetch();
        	imprime_ciudades(ciudades2,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void imprime_ciudades (List<City> ciudades,PrintWriter out){
		try{
			City ciudad = new City();
			for(int i=0;i<ciudades.size() && i<10;i++){
				ciudad = ciudades.get(i);
				if(ciudad.getCountry()!=null){
					out.println("<h5>Ciudad: "+ciudades.get(i).getCity() + " Pais: " +ciudades.get(i).getCountry().getCountry()+"</h5>");
				}
				else{
					out.println("<h5>Ciudad: "+ciudades.get(i).getCity() + "Sin Pais" +"</h5>");
				}
				
			}
		}
		catch(Exception e){
			out.println("<h5> Error al imprimir " + e.getMessage()+" </h5>");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
