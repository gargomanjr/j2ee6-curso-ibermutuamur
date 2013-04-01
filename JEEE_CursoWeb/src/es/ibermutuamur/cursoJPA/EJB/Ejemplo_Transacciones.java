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

import es.ibermutuamur.curso.facades.CountryFacade;
import es.ibermutuamur.curso.facades.CountryFacadeLocal;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Ejemplo_Transacciones", urlPatterns="/Ejemplo_Transacciones")
public class Ejemplo_Transacciones extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
	@EJB CountryFacadeLocal facade; 
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Transacciones() {
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
		
        ejemploTransacciones(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void ejemploTransacciones(PrintWriter out){
        try {   
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>REQUIRED</h4>");
        	Country required = new Country();
        	required.setCountry("REQUIRED");
        	try{
        		int id = facade.persistCountryREQUIRED(required).getCountryId();
        	out.println("<h4>Usando transaccción REQUIRED ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en REQUIRED "+ e.getMessage() +"</h4>");
        	}
        	///////////////////////////////////////////////////////////////////////
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>REQUIRED_NEW</h4>");
        	Country required_new = new Country();
        	required_new.setCountry("REQUIRED_NEW");
        	try{
        		utx.begin();
        		int id = facade.persistCountryREQUIRES_NEW(required_new).getCountryId();
        		utx.commit();
        	out.println("<h4>Usando transaccción REQUIRED_NEW ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en REQUIRED_NEW"+ e.getMessage() +"</h4>");
            	try{
        		utx.rollback();
            	}catch(Exception e1){
            		out.println("<h4>Error ROLLBACK en REQUIRED_NEW "+ e1.getMessage() +"</h4>");
            	}
        	}
        	///////////////////////////////////////////////////////////////////////
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>SUPPORTS</h4>");
        	Country supports = new Country();
        	supports.setCountry("SUPPORTS");
        	try{
        		utx.begin();
        		int id = facade.persistCountrySUPPORTS(supports).getCountryId();
        		utx.commit();
        	out.println("<h4>Usando transaccción SUPPORTS ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en SUPPORTS"+ e.getMessage() +"</h4>");
            	try{
        		utx.rollback();
            	}catch(Exception e1){
            		out.println("<h4>Error ROLLBACK en SUPPORTS "+ e1.getMessage() +"</h4>");
            	}
        	}
        	///////////////////////////////////////////////////////////////////////
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>MANDATORY</h4>");
        	Country mandatory = new Country();
        	mandatory.setCountry("MANDATORY");
        	try{
        		utx.begin();
        		int id = facade.persistCountryMANDATORY(mandatory).getCountryId();
        		utx.commit();
        	out.println("<h4>Usando transaccción MANDATORY ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en MANDATORY"+ e.getMessage() +"</h4>");
            	try{
        		utx.rollback();
            	}catch(Exception e1){
            		out.println("<h4>Error ROLLBACK en MANDATORY "+ e1.getMessage() +"</h4>");
            	}
        	}
        	///////////////////////////////////////////////////////////////////////
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>NOT SUPPORTED</h4>");
        	Country notsupported = new Country();
        	notsupported.setCountry("NOT SUPPORTED");
        	try{
        		int id = facade.persistCountryNOT_SUPPORTED(notsupported).getCountryId();
        	out.println("<h4>Usando transaccción NOT SUPPORTED ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en NOT SUPPORTED "+ e.getMessage() +"</h4>");
        	}
        	///////////////////////////////////////////////////////////////////////
        	//////////////////////////////////////////////////////////////////////
        	out.println("<h4>NEVER</h4>");
        	Country never = new Country();
        	never.setCountry("NEVER");
        	try{
        		int id = facade.persistCountryNEVER(never).getCountryId();
        	out.println("<h4>Usando transaccción NEVER ok id--> "+ id +"</h4>");
        	}catch(Exception e){
        		out.println("<h4>Error en NEVER "+ e.getMessage() +"</h4>");
        	}
        	///////////////////////////////////////////////////////////////////////
        	
        	


        	
        	
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
