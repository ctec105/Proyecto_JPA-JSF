package pe.edu.proyecto.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensajes {
	
	public static void info(String summary, String detail) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void error(String summary, String detail) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void warn(String summary, String detail) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public static void fatal(String summary, String detail) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

}
