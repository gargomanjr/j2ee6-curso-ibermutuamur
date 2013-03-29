
package es.ibermutuamur.cursoJPA.JPA;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Country;

public class HiloOptimista extends Thread {

	@PersistenceContext(unitName="JEEE_CursoWeb")
    EntityManager em;
    @Resource
    UserTransaction utx; 
    
	   public void run()
	   {
		   try{
			   System.out.println("Comienza ejecución Hilo");
			   if(em==null){
				   EntityManagerFactory factory = Persistence.createEntityManagerFactory("JEEE_CursoWeb");
				   em = factory.createEntityManager();
	       	   }
			   if(utx==null){
				   utx = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");  
			   }
			   Query minPais = em.createQuery("Select min(p.countryId) from Country p");
			   int minIdpais= (Integer) minPais.getSingleResult();
			   
			   Country pais = em.find(Country.class, minIdpais);
			   utx.begin();
			   int numAleatorio = (int) (Math.random()*1000+1);
			   pais.setCountry("UK "+ numAleatorio);
			   em.merge(pais);
			   em.flush();
			   utx.commit();	
			   System.out.println("Termina ejecución Hilo");
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   } 
}
