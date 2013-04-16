package es.ibermutuamur.curso.facades;

import javax.ejb.ApplicationException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.ibermutuamur.curso.modelo.Country;



@Stateless(name="CalculadoraFacade")
@ApplicationException(rollback=true)
@Interceptors(Interceptor.class)
public class CalculadoraFacade{
 
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
	
 @ExcludeClassInterceptors
 public float add(float x, float y){ 
  return x + y;
 }
 
 @ExcludeClassInterceptors
 public float subtract(float x, float y){
  return x - y;
 }
 
 @ExcludeDefaultInterceptors
  public float multiply(float x, float y){
  return x * y;
 }
 @ExcludeDefaultInterceptors
 public float division(float x, float y){
 return x / y;
 }
 
 @ExcludeClassInterceptors
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
 public void saltaError(){
	 try{
     Country pais = new Country();
     pais.setCountry("RBtrue");
     em.persist(pais);
	 throw new FinderException("lanzada excecion desde FacadeBean 2");
	} catch (FinderException e) {

		e.printStackTrace();
	}
}
}