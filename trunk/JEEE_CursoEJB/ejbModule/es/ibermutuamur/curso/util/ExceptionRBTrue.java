package es.ibermutuamur.curso.util;
import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public abstract class ExceptionRBTrue extends RuntimeException {
	
	
    private static final long serialVersionUID = 1L;
    
    public ExceptionRBTrue() {
        super();
    }
 
    public ExceptionRBTrue(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
 
    public ExceptionRBTrue(String arg0) {
        super(arg0);
    }
 
    public ExceptionRBTrue(Throwable arg0) {
        super(arg0);
    } 
    
}