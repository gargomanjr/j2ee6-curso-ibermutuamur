package es.ajgarciam.wsccup;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;




/**
 * @generated DT_ID=none
 */
@WebService(endpointInterface = "es.ajgarciam.wsccup.WebServicePruebaI", serviceName="PruebaWSService", portName = "PruebaWSServicePort")
@Stateless
public class WebServicePrueba 
{

    public void sendMensaje(int id,String descripcion){}

    public List<String[]> enviarResultados(int grupo){
    	List<String[]> resultado = new ArrayList<String[]>();
    	String[] campeon = {"España","3","2","1","0","13","0","7"};
    	String[] subcampeon = {"Tahiti","3","2","1","0","13","0","7"};
    	String[] tercero = {"Uruguay","3","2","1","0","13","0","7"};
    	String[] cuarto = {"nigeria","3","2","1","0","13","0","7"};
    	
    	resultado.add(campeon);
    	resultado.add(subcampeon);
    	resultado.add(tercero);
    	resultado.add(cuarto);
    	
    	return resultado;
    }
}
