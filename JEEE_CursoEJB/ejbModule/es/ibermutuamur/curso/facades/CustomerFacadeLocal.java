package es.ibermutuamur.curso.facades;

import java.util.concurrent.Future;

import es.ibermutuamur.curso.modelo.Customer;

import javax.ejb.Asynchronous;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface CustomerFacadeLocal
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public Customer persistCustomer(Customer customer);

    /**
     * @generated DT_ID=none
     */
    public Customer mergeCustomer(Customer customer);

    /**
     * @generated DT_ID=none
     */
    public void removeCustomer(Customer customer);
    
    @Asynchronous
    public Future<Integer> fibonacci(int pos);

}
