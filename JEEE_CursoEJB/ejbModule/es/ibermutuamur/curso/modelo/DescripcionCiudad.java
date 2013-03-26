package es.ibermutuamur.curso.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;


@SuppressWarnings("serial")
@NamedStoredProcedureQuery(name="descripcionCiudad", procedureName="descripcionCiudad",multipleResultSets=false, resultClass=DescripcionCiudad.class, 
parameters={
	      @StoredProcedureParameter(direction=Direction.IN,  name="idCiudad", queryParameter="idCiudad", type=Integer.class),
	      @StoredProcedureParameter(direction=Direction.OUT, name="ciudad",   queryParameter="ciudad", type=String.class),
	      @StoredProcedureParameter(direction=Direction.OUT, name="pais",     queryParameter="pais", type=String.class)
	      }
)
@Entity
public class DescripcionCiudad implements java.io.Serializable {

	// Fields
	@Id
	private String ciudad;
	private String pais;
	
	// Constructors

	/** default constructor */
	public DescripcionCiudad() {
		setCiudad("");
		setPais("");
	}

	public DescripcionCiudad(Object ciudad, Object pais){
		this.setCiudad((String) ciudad);
		this.setPais((String) pais);
	}


	/** full constructor */
	public DescripcionCiudad(String ciudad,String pais) {
		this.setCiudad(ciudad);
		this.setPais(pais);
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}