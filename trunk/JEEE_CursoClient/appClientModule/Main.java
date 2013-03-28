

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import modelo.Country;

public class Main {
	
    static EntityManager em;
    /* No funciona la inyección porque no es un servlet,ni JSP ni Session Bean ni EJB
     */
    @PersistenceUnit(unitName="JEEE_CursoClient") 
	static
    EntityManagerFactory factory;
    
	public static void main(String[] args) {
		if(factory==null){
			factory = Persistence.createEntityManagerFactory("JEEE_CursoClient");
		} 
    	em = factory.createEntityManager();
    	// Usamos EntityTransaction al ser Resource_Local al tratarse de una app JSE
    	EntityTransaction transacion = em.getTransaction(); 
    	transacion.begin();
    	///////////////////////////
        Country pais = new Country();
        pais.setCountry("Pais desde el Cliente");
        em.persist(pais);
        ///////////////////////////
        transacion.commit();
        em.close();
	}
	


	public Main() {
		super();
	}

}