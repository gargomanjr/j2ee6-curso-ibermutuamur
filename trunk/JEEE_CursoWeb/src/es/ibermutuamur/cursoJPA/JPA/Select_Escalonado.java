package es.ibermutuamur.cursoJPA.JPA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Category;
import es.ibermutuamur.curso.modelo.Country;

/**
 * Servlet implementation class InsertarEntity
 */
@SuppressWarnings("serial")
@WebServlet(name="/Select_Escalonado", urlPatterns="/Select_Escalonado")
public class Select_Escalonado extends HttpServlet {
	
    @PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select_Escalonado() {
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
		
        select_Escalonado(out);
		
        out.println("</body>");
        out.println("</html>");
	}

	
	private void select_Escalonado(PrintWriter out){
        try {
        	Query countCategory = em.createQuery("Select count(c) from Category c");
        	Long count = (Long) countCategory.getSingleResult();
        	for(int i=0;i<count;i=i+5){
        		int limite = i+5;
        		out.println("<h4>Categorias desde la "+i+" hasta la "+limite+"</h4>");
        		Query seleccionarCategorias = em.createQuery("Select o from Category o");
        		seleccionarCategorias.setFirstResult(i);
        		seleccionarCategorias.setMaxResults(5);
				List<Category> categorias = seleccionarCategorias.getResultList();
        		String categoriasS="";
        		for(int j=0;j<categorias.size();j++){
        			categoriasS = categoriasS + categorias.get(j).getName() + " ";
        		}
        		out.println("<h4>Categorias: "+categoriasS+"</h4>");
        	}
        	out.println("<h4>Ejemplo Named Query: Empiezan por d</h4>");
        	Query namedq = em.createNamedQuery("Category.findByCategory");
        	namedq.setParameter("nombre", "D%");
        	List<Category> categorias = namedq.getResultList();
        	String categoriasS="";
    		for(int i=0;i<categorias.size();i++){
    			categoriasS = categoriasS + categorias.get(i).getName() + " ";
    		}
    		out.println("<h4>Categorias: "+categoriasS+"</h4>");
    		
    		utx.begin();
    		int numAleatorio = (int) (Math.random()*100+1);
    		Query drama = em.createQuery("Update Category c set c.name = :new_nombre where c.name LIKE :old_nombre");
    		drama.setParameter("old_nombre", "Dram%");
    		drama.setParameter("new_nombre", "Drama"+numAleatorio);
    		int numEntities_actualizadas = drama.executeUpdate();
    		if(numEntities_actualizadas>0){
    			out.println("<h4>Actualiazadas "+numEntities_actualizadas+" entidades</h4>");
    		}
    		utx.commit();
    		
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
