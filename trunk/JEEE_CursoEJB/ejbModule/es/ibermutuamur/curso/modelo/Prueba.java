package es.ibermutuamur.curso.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprueba;

	private String prueba;

	public Prueba() {
	}

	public int getIdprueba() {
		return this.idprueba;
	}

	public void setIdprueba(int idprueba) {
		this.idprueba = idprueba;
	}

	public String getPrueba() {
		return this.prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}

}