package es.ibermutuamur.curso.facades;

import javax.ejb.Local;

@Local
public interface CalculadoraFacadeLocal {

	 public float add(float x, float y);
	 public float subtract(float x, float y);
	 public float multiply(float x, float y);
	 public float division(float x, float y);
}
