import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named("menuBean")
@ViewScoped
public class MenuBean implements Serializable {
    
   private String path ; 

   public String getPath() {
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        this.path =context.getRealPath("JsfCrud.jpg") ;
        
      //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      //prop.load(externalContext.getResourceAsStream("/WEB-INF/file.properties"));
        
        System.out.println("RutaImagen: "+path) ;
        return path ;
    }
 
   public String getMessage() {
      return "Hello World from Fuertefentura";
   }

}