import java.io.Serializable;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named("menuBean")
@ViewScoped
public class MenuBean implements Serializable {
    
   private String path ; 

   public String getDepartamento() {
        return "consultaDepartamento.xhtml" ;
    }
 
    public String getEmpleado() {
       return "consultaEmpleados.xhtml" ; 
    }
   
   public String getMessage() {
      return "CRUD_JSF";
   }

}