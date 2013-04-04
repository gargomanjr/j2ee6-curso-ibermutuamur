package es.ibermutuamur.curso.facades;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Interceptor{

	
	@AroundInvoke
	public Object validationCalculadora(InvocationContext ctx) throws Exception{
		Object objecto = null;
		System.out.println("Metodo  "+ ctx.getTarget().toString()+ " "+ ctx.getMethod().getName() + " "+ ctx.getParameters().length);
		float param = (Float)ctx.getParameters()[0];
		float param1 = (Float)ctx.getParameters()[1];
	    if (param == 0  || param1 == 0){
			ctx.setParameters(new Float[]{(float) 1,(float) 1});
			System.out.println("Metodo incorrecto seteamos parametros con (1,1)--> "+ ctx.getMethod().getName());
			}
	    else
	    	System.out.println("Metodo correcto --> "+ ctx.getMethod().getName());
		try{
			objecto = ctx.proceed(); // Devuelve el control
			} 
		catch(Exception e){
			e.printStackTrace();
		}
		return objecto;
	}
}