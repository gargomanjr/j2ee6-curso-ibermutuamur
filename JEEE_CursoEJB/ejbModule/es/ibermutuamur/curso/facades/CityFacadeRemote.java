package es.ibermutuamur.curso.facades;

import es.ibermutuamur.curso.modelo.City;
import javax.ejb.Remote;


/**
 * @generated DT_ID=none
 */
@Remote
public interface CityFacadeRemote
{

    /**
     * @generated DT_ID=none
     */
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    /**
     * @generated DT_ID=none
     */
    public City persistCity(City city);

    /**
     * @generated DT_ID=none
     */
    public City mergeCity(City city);

    /**
     * @generated DT_ID=none
     */
    public void removeCity(City city);

}
