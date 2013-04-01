package es.ibermutuamur.curso.facades;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import es.ibermutuamur.curso.modelo.Country;

/**
 * @generated DT_ID=none
 */
@Local
public interface CountryFacadeLocal{
    /**
     * @generated DT_ID=none
     */
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults);
    /**
     * @generated DT_ID=none
     */
    public Country persistCountry(Country country);

    /**
     * @generated DT_ID=none
     */
    public Country mergeCountry(Country country);

    /**
     * @generated DT_ID=none
     */
    public void removeCountry(Country country);

    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public Country persistCountryMANDATORY(Country country);
    
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Country persistCountryNEVER(Country country);
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Country persistCountryNOT_SUPPORTED(Country country);
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Country persistCountryREQUIRED(Country country) ;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Country persistCountryREQUIRES_NEW(Country country);
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Country persistCountrySUPPORTS(Country country);
    
}
