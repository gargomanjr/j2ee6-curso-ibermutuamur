package es.ibermutuamur.curso.facades;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;


@Local
@Remote
@WebService(name = "PruebaWS", targetNamespace = "http://curso.ibermutuamur.es/")
public interface WebServicePruebaI {

	@WebMethod
	
	@WebResult(targetNamespace = "")	
	public void sendMensaje(
			@WebParam(name = "id", targetNamespace = "") int id,
			@WebParam(name = "descripcion", targetNamespace = "") String descripcion);
}
