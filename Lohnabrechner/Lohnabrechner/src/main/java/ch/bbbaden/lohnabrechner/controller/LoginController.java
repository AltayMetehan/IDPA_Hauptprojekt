package ch.bbbaden.lohnabrechner.controller;

import ch.bbbaden.lohnabrechner.model.User;
import ch.bbbaden.lohnabrechner.model.UserDAO;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Metehan
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable{
    private String name;
    private String password;
    
    private User user;
    
    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    public LoginController() {
    }
    
    public String doLogin(){
        UserDAO udao = new UserDAO();
        this.user = udao.check(name, password);
        
        LOGGER.log(Level.INFO, "Login Versuch");
        
        if (this.user != null) {
            LOGGER.log(Level.WARNING, "Login Versuch gefailt");
            if (user.getIsAdminLogin() == true) {
                return "/admin.xhtml";
            }else{
                return "/Arbeitgeber.xhtml";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed!", null));
        return "/index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }
}
