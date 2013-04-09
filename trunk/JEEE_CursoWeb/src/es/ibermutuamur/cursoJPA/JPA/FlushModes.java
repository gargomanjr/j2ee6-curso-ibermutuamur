package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
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
@WebServlet(name="/FlushModes", urlPatterns="/FlushModes")
public class FlushModes extends HttpServlet {
	
	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlushModes() {
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
		
        flushModes(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void flushModes(PrintWriter out){
        try {     
        	em.setFlushMode(FlushModeType.COMMIT);
        	utx.begin();	
        	em.setFlushMode(FlushModeType.COMMIT);
        	out.println("<h4>Flush Mode "+em.getFlushMode().toString()+"</h4>");
	        Country pais = new Country();   
	        pais.setCountry("FLUSH1");
	        em.persist(pais);
	        out.println("<h4>Inserta pais "+pais.getCountryId() +" con id "+pais.getCountryId()+"</h4>");
	        utx.commit();
	        out.println("<h4>Inserta pais "+pais.getCountryId() +" con id "+pais.getCountryId()+"</h4>");
	        
	        //Opción por defecto
        	em.setFlushMode(FlushModeType.AUTO);
        	utx.begin();	
        	out.println("<h4>Flush Mode "+em.getFlushMode().toString()+"</h4>");
	        Country pais2 = new Country();    
	        pais2.setCountry("FLUSH2");
	        em.persist(pais2);
	        out.println("<h4>Inserta pais2 "+pais2.getCountryId() +" con id "+pais2.getCountryId()+"</h4>");
	        utx.commit();
	        out.println("<h4>Inserta pais2 "+pais2.getCountryId() +" con id "+pais2.getCountryId()+"</h4>");
	        
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
