package es.ibermutuamur.es.curso.jms;

import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.annotation.Resource;
import javax.ejb.*;

import es.ibermutuamur.curso.modelo.Country;


@MessageDriven(mappedName = "jms/Queue", activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})

	@TransactionAttribute(TransactionAttributeType.NEVER)
public class Consumidor implements MessageListener  {

    @PersistenceContext(unitName="JEEE_CursoEJB")
    private EntityManager em;
    @Resource
    UserTransaction utx; 
    
	public void onMessage(Message message) {

		MapMessage msgMap = null;
		msgMap = (MapMessage) message;
		
		try {
		Integer id = (Integer) msgMap.getObjectProperty("id");
		String descripcion = (String) msgMap.getObjectProperty("descripcion");
		
		Country paisnuevo = new Country();
		paisnuevo.setCountryId(id);
		paisnuevo.setCountry(descripcion);
		
		utx.begin();
		em.persist(paisnuevo);
		utx.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
}
