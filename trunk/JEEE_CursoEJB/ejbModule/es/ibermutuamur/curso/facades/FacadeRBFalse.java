package es.ibermutuamur.curso.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.ibermutuamur.curso.modelo.Country;
import es.ibermutuamur.curso.util.ExceptionRBFalse;


@Stateless(name="ExcepcionRBFalse")
public class FacadeRBFalse {
 
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;

 

 public void saltaError() throws ExceptionRBFalse {
		 Country pais = new Country();
	     pais.setCountry("RBFalse");
	     em.persist(pais);  
		 throw new ExceptionRBFalse("Fallo RB False") {};
 }
}