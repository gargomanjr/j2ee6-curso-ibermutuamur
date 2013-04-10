package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.City;
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
		//update(out);
        out.println("</body>");
        out.println("</html>");
	}

	
	private void flushModes(PrintWriter out){
        try {     
        	
			Query maxPais = em.createQuery("Select max(p.countryId) from Country p");
			int maxIdpais= (Integer) maxPais.getSingleResult();
        	

        	utx.begin();	
        	em.setFlushMode(FlushModeType.COMMIT);
        	out.println("<h3>Flush Mode "+em.getFlushMode().toString()+"</h3>");
        	Country entityCountry = em.find(Country.class, maxIdpais);
	        City ciudad = new City();   
	        ciudad.setCity("CFLUSH1");
	        ciudad.setCountry(entityCountry);
	        em.persist(ciudad);
	        entityCountry = em.find(Country.class, maxIdpais);
	        imprimerCiudades(out, maxIdpais);
	        utx.commit();
	        entityCountry = em.find(Country.class, maxIdpais);
	        imprimerCiudades(out, maxIdpais);
	        
	        
        	utx.begin();	
        	em.setFlushMode(FlushModeType.COMMIT);
        	out.println("<h3>Flush Mode "+em.getFlushMode().toString()+"con flush antes del commit </h3>");
        	entityCountry = em.find(Country.class, maxIdpais);
	        ciudad = new City();   
	        ciudad.setCity("CFLUSH1");
	        ciudad.setCountry(entityCountry);
	        em.persist(ciudad);
	        em.flush();
	        imprimerCiudades(out, maxIdpais);
	        utx.commit();
	        entityCountry = em.find(Country.class, maxIdpais);
	        imprimerCiudades(out, maxIdpais);
	        
	        
	        //Opción por defecto
        	utx.begin();	
        	em.setFlushMode(FlushModeType.AUTO);
        	out.println("<h3>Flush Mode "+em.getFlushMode().toString()+"</h3>");
        	entityCountry = em.find(Country.class, maxIdpais);
	        ciudad = new City();   
	        ciudad.setCity("CFLUSH2");
	        ciudad.setCountry(entityCountry);
	        em.persist(ciudad);
	        entityCountry = em.find(Country.class, maxIdpais);
	        imprimerCiudades(out, maxIdpais);
	        utx.commit();
	        entityCountry = em.find(Country.class, maxIdpais);
	        imprimerCiudades(out, maxIdpais);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
	@SuppressWarnings("unchecked")
	private void imprimerCiudades(PrintWriter out,int  idpais){
		Query q = em.createQuery("Select c from City c where c.country.countryId = :country");
		q.setParameter("country", idpais);
		List<City> lista_ciudades = q.getResultList();
		String ciudadesd = "";
		if(lista_ciudades!=null && lista_ciudades.size()>0){
			for(int i=0;i<lista_ciudades.size();i++){
				ciudadesd = lista_ciudades.get(i).getCity()+  " " + ciudadesd;
			}
			out.println("<h4> El Pais " +" tiene las siguientes ciudades asociadas "+ciudadesd +"</h4>");
		}
		else{
			out.println("<h4> El Pais " +" no tiene ciudades asociadas </h4>");
		}
		
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
