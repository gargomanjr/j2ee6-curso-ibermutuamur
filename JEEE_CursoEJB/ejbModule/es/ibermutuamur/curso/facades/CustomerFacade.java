package es.ibermutuamur.curso.facades;

import java.util.Date;
import java.util.concurrent.Future;

import es.ibermutuamur.curso.modelo.Country;
import es.ibermutuamur.curso.modelo.Customer;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 * @generated DT_ID=none
 */
@Stateless(name = "CustomerFacade", mappedName = "JEEE_Curso-JEEE_CursoEJB-CustomerFacade")
public class CustomerFacade
        implements CustomerFacadeLocal, CustomerFacadeRemote
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public CustomerFacade() {
    }

    /**
     * @generated DT_ID=none
     */
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }

    /**
     * @generated DT_ID=none
     */
    public Customer persistCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    /**
     * @generated DT_ID=none
     */
    public Customer mergeCustomer(Customer customer) {
        return em.merge(customer);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeCustomer(Customer customer) {
        customer = em.find(Customer.class, customer.getCustomerId());
        em.remove(customer);
    }

    //Mostraria la salidad de pantalla cada segundo.
    @Schedule(minute="*/5",hour="*",persistent=false)
    public void doWork(){
    	Date d = new Date();
        System.out.println("Hola desde el Bean Customer Facade :) son las  " +d.toString());
    }
    

    @Schedule( minute="*/20",hour="*")
    public void insertarPais(){
        Country c = new Country();
        c.setCountry("Pais Timer");
        em.flush();
        em.persist(c);
    }
    

    private int FiboRecursivo(int pos){
    	int resultado= 0;
    	if(pos==0 || pos==1){
    		resultado=pos;
    	}else{
    		resultado=FiboRecursivo(pos-2)+FiboRecursivo(pos-1);
    	}
		return resultado;
    }
    
    
    @Asynchronous
    public Future<Integer> fibonacci(int pos){
    	int resultado= 0;
    	resultado = FiboRecursivo(pos);
		return new AsyncResult<Integer>(resultado);
    }
}
