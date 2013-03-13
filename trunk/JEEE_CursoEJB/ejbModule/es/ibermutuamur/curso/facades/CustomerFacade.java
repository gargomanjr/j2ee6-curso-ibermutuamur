package es.ibermutuamur.curso.facades;

import es.ibermutuamur.curso.modelo.Customer;
import javax.ejb.Stateless;
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

}
