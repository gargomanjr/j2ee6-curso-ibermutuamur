package es.ibermutuamur.curso.facades;

import java.util.List;

import es.ibermutuamur.curso.modelo.City;
import javax.ejb.Local;


/**
 * @generated DT_ID=none
 */
@Local
public interface CityFacadeBeanLocal
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
    
	public List<City> listaCiudadesFetch ();
	
	public List<City> listaCiudades ();

}
