package es.ibermutuamur.curso.facades;

import java.util.List;

import es.ibermutuamur.curso.modelo.City;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * @generated DT_ID=none
 */
@Stateless(name = "CityFacade", mappedName = "JEEE_Curso-JEEE_CursoEJB-CityFacade")
public class CityFacade{

    /**
     * @generated DT_ID=none
     */
    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;

    /**
     * @generated DT_ID=none
     */
    public CityFacade() {
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
    public City persistCity(City city) {
        em.persist(city);
        return city;
    }

    /**
     * @generated DT_ID=none
     */
    public City mergeCity(City city) {
        return em.merge(city);
    }

    /**
     * @generated DT_ID=none
     */
    public void removeCity(City city) {
        city = em.find(City.class, city.getCityId());
        em.remove(city);
    }

    @SuppressWarnings("unchecked")
	public List<City> listaCiudades (){
    	Query q  = em.createQuery("select c from City c ");
    	return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<City> listaCiudadesFetch (){
    	Query q  = em.createQuery("select c from City c");
    	List<City> cities =  q.getResultList();
    	for(int i =0; i<cities.size();i++){
    		cities.get(i).getCountry();
    	}
    	return cities;
    }
    
}
