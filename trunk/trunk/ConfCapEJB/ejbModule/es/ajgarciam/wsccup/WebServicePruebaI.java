package es.ajgarciam.wsccup;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;


@Local
@Remote
@WebService(name = "PruebaWS", targetNamespace = "http://wscccup.ajgarciam.es/")
public interface WebServicePruebaI {

	@WebMethod	
	@WebResult(targetNamespace = "")	
	public void sendMensaje(
			@WebParam(name = "id", targetNamespace = "") int id,
			@WebParam(name = "descripcion", targetNamespace = "") String descripcion);
	
	@WebMethod	
	@WebResult(targetNamespace = "")
	public List<String[]> enviarResultados(
			@WebParam(name = "grupo", targetNamespace = "") int grupo);
}
