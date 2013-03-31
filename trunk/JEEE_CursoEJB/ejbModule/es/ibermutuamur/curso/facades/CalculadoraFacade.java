package es.ibermutuamur.curso.facades;

import javax.ejb.Stateless;



@Stateless(name="CalculadoraFacade")
public class CalculadoraFacade implements CalculadoraFacadeLocal{
  
 public float add(float x, float y){ 
  return x + y;
 }

 public float subtract(float x, float y){
  return x - y;
 }
  public float multiply(float x, float y){
  return x * y;
 }
  
 public float division(float x, float y){
 return x / y;
 }
}