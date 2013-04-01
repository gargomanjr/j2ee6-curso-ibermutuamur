package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Category;
import es.ibermutuamur.curso.modelo.Language;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Ejemplo_Cache", urlPatterns="/Ejemplo_Cache")
public class Ejemplo_Cache extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;

    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejemplo_Cache() {
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
        out.println("<title>Resultado Cache</title>");
        out.println("</head>");
        out.println("<body>");
		
        ejemplo_Cache(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	@SuppressWarnings("unchecked")
	private void ejemplo_Cache(PrintWriter out){
        try {
        	Query qminlanguageId = em.createQuery("Select min(l.languageId) from Language l");
        	int id_minLeng = (Integer) qminlanguageId.getSingleResult();
        	
    		out.println("<h4> 5 Primeros idiomas </h4>");
    		Query seleccionarLenguajes = em.createQuery("Select l from Language l");
    		seleccionarLenguajes.setFirstResult(0);
    		seleccionarLenguajes.setMaxResults(5);
			List<Language> lenguajes = seleccionarLenguajes.getResultList();
    		String lenguajesS="";
    		for(int j=0;j<lenguajes.size();j++){
    			lenguajesS = lenguajesS + lenguajes.get(j).getName() + " ";
    		}
    		
    		out.println("<h4>Lenguajes: "+lenguajesS+"</h4>");
        	
    		
        	
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JEEE_CursoEJB");
        	EntityManager em2 = factory.createEntityManager();

        	utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
        	
    		utx.begin();
    		Language idioma = em2.find(Language.class, id_minLeng);
    		int numAleatorio = (int) (Math.random()*99+1);
    		String cadena = idioma.getName().substring(0, 5);
    		idioma.setName(cadena+numAleatorio);
    		em2.merge(idioma);
    		em2.flush();
    		utx.commit();
    		
    		
    		Cache cache = em.getEntityManagerFactory().getCache();
    		if (cache.contains(Language.class, id_minLeng)) {
    			out.println("<h4> La entidad está cacheada </h4>");
    			//cache.evict(Language.class, id_minLeng); Esto sacaría de la cache la entity
    		} else {
    			out.println("<h4> La entidad no está cacheada</h4>");
    		}
    		
    		
    		out.println("<h4> 5 Primeros idiomas cacheando</h4>");
    		seleccionarLenguajes.setFirstResult(0);
    		seleccionarLenguajes.setMaxResults(5);
			lenguajes = seleccionarLenguajes.getResultList();
    		lenguajesS="";
    		for(int j=0;j<lenguajes.size();j++){
    			lenguajesS = lenguajesS + lenguajes.get(j).getName() + " ";
    		}
    		
    		out.println("<h4>Lenguajes: "+lenguajesS+"</h4>");
    		
    		out.println("<h4> 5 Primeros idiomas sin cachear</h4>");
    		seleccionarLenguajes.setFirstResult(0);
    		seleccionarLenguajes.setMaxResults(5);
    		seleccionarLenguajes.setHint("javax.persistence.cache.storeMode", "REFRESH"); 
			lenguajes = seleccionarLenguajes.getResultList();
    		lenguajesS="";
    		for(int j=0;j<lenguajes.size();j++){
    			lenguajesS = lenguajesS + lenguajes.get(j).getName() + " ";
    		}
    		
    		out.println("<h4>Lenguajes: "+lenguajesS+"</h4>");
    		
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
