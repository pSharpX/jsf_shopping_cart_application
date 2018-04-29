/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import pe.edu.cibertec.service.UsuarioService;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Java-LM
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean {

    private String username;
    private String password;

    @Inject
    @Named("usuarioService")
    private UsuarioService usuarioService;

    public static final String HOME_PAGE_REDIRECT = "home";
    public static final String LOGOUT_PAGE_REDIRECT = "login";

    public String autenticar() {
        try{
            ResourceBundle rb = ResourceBundle.getBundle("pe.edu.cibertec.recursos.mensajes",
                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
            if (usuarioService.existe(username, password)) {
                return HOME_PAGE_REDIRECT;
            }

            password = null;
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    rb.getString("validacion_login_incorrecto"),
                    rb.getString("validacion_login_incorrecto_detail"));
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return LOGOUT_PAGE_REDIRECT;
    }

    public boolean isLoggedIn() {
        return username != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isUserNameInputValid (){
      FacesContext context = FacesContext.getCurrentInstance();      
      UIInput input = (UIInput)context.getViewRoot().findComponent("login:inputUsername");
      return input.isValid();
   }
    
    public boolean isPasswordInputValid (){
      FacesContext context = FacesContext.getCurrentInstance();      
      UIInput input = (UIInput)context.getViewRoot().findComponent("login:inputPassword");
      return input.isValid();
   }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
