package es.ibermutuamur.curso.facades;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Named;

@Named
@Stateful(name="ShoppingCart",mappedName="ShoppingCart")
public class ShoppingCartBean implements ShoppingCart, Serializable
{

	private static final long serialVersionUID = 1L;
	private HashMap<String, Integer> cart = new HashMap<String, Integer>();

   public void buy(String product, int quantity)
   {
      if (cart.containsKey(product))
      {
         int currq = cart.get(product);
         currq += quantity;
         cart.put(product, currq);
      }
      else
      {
         cart.put(product, quantity);
      }
   }

   public HashMap<String, Integer> getCartContents()
   {
      return cart;
   }

   @PostConstruct
   public void postconstructor()
   {
      System.out.println("Despu�s de crear el Bean");
      
   }
   
   
   @Remove
   public void checkout()
   {
      System.out.println("To be implemented");
   }
}
