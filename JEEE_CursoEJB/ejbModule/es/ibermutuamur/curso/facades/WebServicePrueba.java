package es.ibermutuamur.curso.facades;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jws.WebService;
import javax.jms.MapMessage;
import javax.jms.Session;




/**
 * @generated DT_ID=none
 */
@WebService(endpointInterface = "es.ibermutuamur.curso.facades.WebServicePruebaI", serviceName="PruebaWSService", portName = "PruebaWSServicePort")
@Stateless
public class WebServicePrueba 
{
    @Resource(mappedName = "jms/Queue")
    Queue queue;
    @Resource(mappedName = "jms/Factory")
    ConnectionFactory factory;
    
    
    public void sendMensaje(int id,String descripcion)throws JMSException {
        Connection connection = null;
        Session session = null;
        //try {
            try {
				connection = factory.createConnection();
				connection.start();
	            // Create a Session
	            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	            // Create a MessageProducer from the Session to the Topic or Queue
	            MessageProducer producer = session.createProducer(queue);

	            MapMessage mensaje = session.createMapMessage();
	            
	            mensaje.setIntProperty("id", id);
	            mensaje.setStringProperty("descripcion", descripcion);

                producer.send(mensaje);
	            } finally {
		            if (session != null) {
		                try {
		                    session.close();
		                } catch (JMSException e) {
		                	System.out.println(">>>>>>>>>>>>>>>>> generarPDFsAsync Error en sendJMSMessageToTest");
		                }
		            }
		            if (connection != null) {
		                connection.close();
		            }
	            }
    	}
    

}
