package es.ibermutuamur.curso.facades;

import javax.ejb.ApplicationException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.ibermutuamur.curso.modelo.Country;



@Stateless(name="CalculadoraFacade2")
@ApplicationException(rollback=false)
public class CalculadoraFacade2{
 
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
 
 
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
 public void saltaError(){
     try {
	 Country pais = new Country();
     pais.setCountry("RBFalse");
     em.persist(pais);  
	 throw new FinderException("lanzada excecion desde FacadeBean 2");
	} catch (FinderException e) {

		e.printStackTrace();
	}
 }
 
}