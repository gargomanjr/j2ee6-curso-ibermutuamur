package es.ibermutuamur.curso.facades;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@AccessTimeout(value=10, unit=TimeUnit.SECONDS) //A nivel de clase o de método
public class Ej_Singleton {
  private String status;

  @PostConstruct
  public void init() {
    status = "Ready";
  }
  
  @Lock(LockType.WRITE)
  public void setStatus(String newStatus) {
    status = newStatus;
  }

  @Lock(LockType.READ)
  public String getStatus() {
    return status;
  }
}