package es.ibermutuamur.curso.facades;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import es.ibermutuamur.curso.modelo.Country;
import es.ibermutuamur.curso.util.ExceptionRBTrue;



@Stateless(name="ExcepcionRBTrue")
public class FacadeRBTrue {
 
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
    @Resource
    UserTransaction utx;
 
 
 public void saltaError(){
	 Country pais = new Country();
     pais.setCountry("RBTrue");
     em.persist(pais);  
     throw new ExceptionRBTrue("Rollback true") {};
 }
 
}