package es.ibermutuamur.curso.facades;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;

@Remote
@Local
public interface ShoppingCart
{
	  	
   void buy(String product, int quantity);

   HashMap<String, Integer> getCartContents();

   @Remove void checkout();
   
   @PostConstruct void postconstructor();
}