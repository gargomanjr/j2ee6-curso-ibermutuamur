package es.ibermutuamur.curso.facades;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorDefault{

	
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception{
		Object objecto = null;
		System.out.println("Metodo  "+ ctx.getMethod().getName());
		try{
			objecto = ctx.proceed(); // Devuelve el control
			} 
		catch(Exception e){
			e.printStackTrace();
		}
		return objecto;
	}
}