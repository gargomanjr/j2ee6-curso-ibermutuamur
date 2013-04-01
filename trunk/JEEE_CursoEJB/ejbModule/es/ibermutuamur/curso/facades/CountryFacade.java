package es.ibermutuamur.curso.facades;

import es.ibermutuamur.curso.modelo.Country;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * @generated DT_ID=none
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless(name = "CountryFacade", mappedName = "JEEE_Curso-JEEE_CursoEJB-CountryFacade")
public class CountryFacade implements CountryFacadeLocal
{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
    @Resource
    UserTransaction utx;


    /**
     * @generated DT_ID=none
     */
    public CountryFacade()  {
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
    public Country persistCountry(Country country) {
        em.persist(country);
        return country;
    }

    /**
     * @generated DT_ID=none
     */
    public Country mergeCountry(Country country) {
        return em.merge(country);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeCountry(Country country) {
        country = em.find(Country.class, country.getCountryId());
        em.remove(country);
    }

    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Country persistCountryMANDATORY(Country country) {
        em.persist(country);
        return country;
    }
    
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Country persistCountryNEVER(Country country) {
		try{	
	    	utx.begin();
	        em.persist(country);
	        em.flush();
	        utx.commit();
	        return country;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
	    }
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Country persistCountryNOT_SUPPORTED(Country country) {
    	try {
			utx.begin();
	        em.persist(country);
	        em.flush();
	        utx.commit();
	        return country;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Country persistCountryREQUIRED(Country country) {
        em.persist(country);
        return country;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Country persistCountryREQUIRES_NEW(Country country) {
        em.persist(country);
        return country;
    }
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Country persistCountrySUPPORTS(Country country) {
        em.persist(country);
        return country;
    }

    
    
}
