package pe.edu.proyecto.jsf.managed;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.UsuarioService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbUsuario;
import pe.edu.proyecto.primefaces.util.UsuarioDataModel;
import pe.edu.proyecto.util.MD5Util;

@ManagedBean (name="formUsuario")
@SessionScoped
public class UsuarioBean {
	
	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static UsuarioService usuarioService;
	private static UtilService utilService;
	private TbUsuario usuario = new TbUsuario();
	private List<TbUsuario> usuarioList;
	private List<TbArea> areaList;
	private String confPass;
	private String newPass;

	public TbUsuario getUsuario() {
		return usuario;
	}
	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}
	public List<TbUsuario> getUsuarioList() {
		return usuarioList;
	}
	public void setUsuarioList(List<TbUsuario> usuarioList) {
		this.usuarioList = usuarioList;
	}
	public List<TbArea> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<TbArea> areaList) {
		this.areaList = areaList;
	}
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	
	public String listarUsuarios() throws Exception {
		System.out.println("Estoy dentro del método listarUsuarios() - INI");
		usuarioService = abd.getUsuarioService();
		usuarioList = usuarioService.listarUsuario();
		mediumUsuariosModel = new UsuarioDataModel(usuarioList); // Extra PrimeFaces listado
		System.out.println("Estoy dentro del método listarUsuarios() - FIN");
		return "usuario";
	}
	
	public String hola() {
		return "usuario";
	}
	
	public String edit() throws Exception{
		System.out.println("Estoy dentro del método edit() - INI");
		
		usuario = new TbUsuario();
		
		utilService = abd.getUtilService();
		areaList = utilService.listarAreas();
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		System.out.println("*** Usuario: " + codigo);
		
		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");
			usuarioService = abd.getUsuarioService();
			usuario.setIdUsuario(Integer.parseInt(codigo));
			usuario = usuarioService.obtenerUsuario(usuario);
		} 

		System.out.println("Estoy dentro del método edit() - FIN");
		return "usuarioForm";
	}
	
	public String registrarOrActualizar() throws Exception{
		System.out.println("Estoy dentro del método registrarOrActualizar() - INI");
		
		usuarioService = abd.getUsuarioService();
		
		if (usuario.getFechaIngreso() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡Debe ingresar una fecha!"));
			return null;
		}
		
		if (usuario.getIdUsuario() == 0){
			usuario.setPassword(MD5Util.md5Hex(usuario.getPassword()));
			usuarioService.registrarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "¡Usuario Registrado!"));
		}else{
			usuario.setPassword(MD5Util.md5Hex(usuario.getPassword()));
			usuarioService.actualizarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso","¡Usuario Actualizado!"));
		}
		
		System.out.println("Estoy dentro del método registrarOrActualizar() - FIN");
		return listarUsuarios();
	}
	
	public String eliminar() throws Exception{
		System.out.println("Estoy dentro del método eliminar() - INI");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		usuarioService = abd.getUsuarioService();
		usuario = new TbUsuario();
		usuario.setIdUsuario(Integer.parseInt(codigo));
		usuario = usuarioService.obtenerUsuario(usuario);
		usuarioService.eliminarUsuario(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "¡Usuario eliminado!"));
		System.out.println("Estoy dentro del método eliminar() - FIN");
		return listarUsuarios();
	}
	
	public String bloquear() throws Exception{
		System.out.println("Estoy dentro del método bloquear() - INI");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		usuarioService = abd.getUsuarioService();
		usuario = new TbUsuario();
		usuario.setIdUsuario(Integer.parseInt(codigo));
		usuario = usuarioService.obtenerUsuario(usuario);
		usuario.setEstado(1);
		usuarioService.actualizarUsuario(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "¡Usuario bloqueado!"));
		System.out.println("Estoy dentro del método bloquear() - FIN");
		return listarUsuarios();
	}
	
	public String desbloquear() throws Exception{
		System.out.println("Estoy dentro del método desbloquear() - INI");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		usuarioService = abd.getUsuarioService();
		usuario = new TbUsuario();
		usuario.setIdUsuario(Integer.parseInt(codigo));
		usuario = usuarioService.obtenerUsuario(usuario);
		usuario.setEstado(0);
		usuarioService.actualizarUsuario(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "¡Usuario desbloqueado!"));
		System.out.println("Estoy dentro del método desbloquear() - FIN");
		return listarUsuarios();
	}
	
	/*public String irAcambiarContraseña() throws Exception{
		String login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
		usuario = new TbUsuario();
		usuario.setLogin(login);
		return "cambio";
	}*/
	
	public String irAcambiarContraseña() throws Exception{
		LoginBean login = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
		usuario = new TbUsuario();
		usuario.setLogin(login.getUsuario().getLogin());
		return "cambio";
	}
	
	public String cambiarContraseña() throws Exception {
		System.out.println("Estoy dentro del método cambiarContraseña() - INI");
		
		if (usuario.getPassword().length() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Por favor","¡Ingrese su contraseña!"));
			return null; // return null para que no lanze todos los mensajes a la vez
		}
		
		if (newPass.length() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Por favor","¡Ingrese su nueva contraseña!"));
			return null;
		}
		
		if (confPass.length() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Por favor","¡Repita su nueva contraseña!"));
			return null;
		}
		
		usuarioService = abd.getUsuarioService();
		TbUsuario user = null;
		user = usuarioService.logueo(usuario.getLogin(),MD5Util.md5Hex(usuario.getPassword()));

		if (user != null) {
			if (!confPass.equals(newPass)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","¡Su contraseña nueva no coincide!"));
			}else{
				user.setPassword(MD5Util.md5Hex(newPass));
				usuarioService.actualizarUsuario(user);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exitoso", "¡Contraseña Cambiada!"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "¡Su contraseña es incorrecta!"));
		}

		usuario.setPassword("");
		
		System.out.println("Estoy dentro del método cambiarContraseña() - FIN");
		return "cambio";
	}
	
	// Extra PrimeFaces listado
	private UsuarioDataModel mediumUsuariosModel; 
    
	public UsuarioDataModel getMediumUsuariosModel() {
		return mediumUsuariosModel;
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Usuario ", ((TbUsuario) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Usuario ", ((TbUsuario) event.getObject()).getApePat());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /*public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }*/

}
