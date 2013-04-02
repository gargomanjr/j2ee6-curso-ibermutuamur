package es.ibermutuamur.curso.facades;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;



@Stateless(name="CalculadoraFacade")
@Interceptors(Interceptor.class)
public class CalculadoraFacade{
 
	
 @ExcludeClassInterceptors
 public float add(float x, float y){ 
  return x + y;
 }
 
 @ExcludeClassInterceptors
 public float subtract(float x, float y){
  return x - y;
 }
 
 @ExcludeDefaultInterceptors
  public float multiply(float x, float y){
  return x * y;
 }
 @ExcludeDefaultInterceptors
 public float division(float x, float y){
 return x / y;
 }
}