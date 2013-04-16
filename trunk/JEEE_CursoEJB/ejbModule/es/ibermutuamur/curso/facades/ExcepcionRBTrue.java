package es.ibermutuamur.curso.facades;

import javax.ejb.ApplicationException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.ibermutuamur.curso.modelo.Country;



@Stateless(name="ExcepcionRBTrue")
@ApplicationException(rollback=true)
public class ExcepcionRBTrue {
 
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
 
 
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
 public void saltaError() throws FinderException{
	 Country pais = new Country();
     pais.setCountry("RBTrue");
     em.persist(pais);  
	 throw new FinderException("lanzada excecion desde ExcepcionRBTrue");
 }
 
}