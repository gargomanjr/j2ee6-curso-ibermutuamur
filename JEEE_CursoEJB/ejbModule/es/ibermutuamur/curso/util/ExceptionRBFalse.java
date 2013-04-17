package es.ibermutuamur.curso.util;
import javax.ejb.ApplicationException;


@ApplicationException(rollback = false)
public abstract class ExceptionRBFalse extends Exception  {
	
    private static final long serialVersionUID = 1L;
    
    public ExceptionRBFalse() {
        super();
    }
 
    public ExceptionRBFalse(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
 
    public ExceptionRBFalse(String arg0) {
        super(arg0);
    }
 
    public ExceptionRBFalse(Throwable arg0) {
        super(arg0);
    } 
}