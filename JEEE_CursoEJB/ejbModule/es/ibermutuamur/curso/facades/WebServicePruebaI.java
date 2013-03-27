package es.ibermutuamur.curso.facades;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@Local
@WebService(name = "PruebaWS", targetNamespace = "http://curso.ibermutuamur.es/")
public interface WebServicePruebaI {

	@WebMethod
	@WebResult(targetNamespace = "")	
	@RequestWrapper(localName =  "WebServicePrueba",         targetNamespace = "http://curso.ibermutuamur.es/", className = "es.ibermutuamur.curso.facades.WebServicePrueba")
	@ResponseWrapper(localName = "WebServicePruebaResponse", targetNamespace = "http://curso.ibermutuamur.es/", className = "es.ibermutuamur.curso.facades.WebServicePruebaPruebaResponse")
	public void sendMensaje(
			@WebParam(name = "id", targetNamespace = "") int id,
			@WebParam(name = "descripcion", targetNamespace = "") String descripcion);
}
