package controller;

import comptoirs.model.dao.ClientFacade;
import comptoirs.model.dao.CommandeFacade;
import comptoirs.model.entity.Client;
import comptoirs.model.entity.Commande;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Controller
@Path("Login")
@View("Login.jsp")
public class LoginController {

    @Inject // Le DAO (auto-généré) qui gère les entités "Client"
    ClientFacade facade;
    @Inject
    CommandeFacade dao;
    @Inject
    Models models; // Pour transmettre les infos à la vue

    @GET
    public void show() {

    }

    @POST
    @ValidateOnExecution(type = ExecutableType.ALL)
    public String LoginClient(
            @FormParam("code") String codeClient, @FormParam("contact") String contactClient) {        
        Client c = facade.find(codeClient);
        if ((c != null) && (c.getContact().equals(contactClient))) {
 /*
                Commande nouvelle = new Commande();
                nouvelle.setClient(c);
                nouvelle.setNumero(Integer.SIZE);
                //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                nouvelle.setSaisieLe(date);
                nouvelle.setRemise(BigDecimal.ZERO);
                dao.create(nouvelle);
                models.put("felicitations!", c);
*/
                return "redirect:/categories";
        } else{
               models.put("databaseErrorMessage", "Le mot de passe ou l'identifiant du client" + codeClient + "ne correspondent pas");
                   return "redirect:/Login";
            }


    
       
            

}
}
