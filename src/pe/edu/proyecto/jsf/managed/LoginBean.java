package pe.edu.proyecto.jsf.managed;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.LoginService;
import pe.edu.proyecto.business.service.UsuarioService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbUsuario;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean {
	
	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static LoginService loginService;
	private static UsuarioService usuarioService;
	private static UtilService utilService;
	
	// Instances
	private TbUsuario usuario = new TbUsuario();
	private TbArea area = new TbArea();
	private List<TbAreaHasTbMenu> areamenuList;
	private MenuModel model;
	
	// Getters and setters
	public TbUsuario getUsuario() {
		return usuario;
	}
	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}
	public TbArea getArea() {
		return area;
	}
	public void setArea(TbArea area) {
		this.area = area;
	}
	public List<TbAreaHasTbMenu> getAreamenuList() {
		return areamenuList;
	}
	public void setAreamenuList(List<TbAreaHasTbMenu> areamenuList) {
		this.areamenuList = areamenuList;
	}
	public MenuModel getModel() {
		return model;
	}

	
	// Actions
	public String validar() throws Exception {
		loginService=abd.getLoginService();
		
		if(loginService.validar(usuario)){
			System.out.println("Obtemeos el objeto usuario");
			usuarioService = abd.getUsuarioService();
			usuario = usuarioService.obtenerUsuarioXLogin(usuario);
			
			System.out.println("Evaluando estado del usuario");
			if (usuario.getEstado() != 0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario <b>" + usuario.getLogin() + "</b> bloqueado.", null)); 
				return null;
			}else{
				utilService = abd.getUtilService();
				area = utilService.obtenerArea(usuario.getTbAreaIdArea());
				areamenuList = usuarioService.permisos(usuario.getTbAreaIdArea());
				crearMenu(); // Crea menú programáticamente con Primefaces
				return "menuBlanco";
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Su usuario y/o contraseña son incorrectos.", null));
			return null;
		}
	}
	
	public void crearMenu() {
		System.out.println("crearMenu");
		model = new DefaultMenuModel();
		Submenu submenu = null;
		for (TbAreaHasTbMenu tb : areamenuList) {
			if (tb.getTbMenu().getNivel().length() == 1) { // si el menu es independiente
				System.out.println("* " + tb.getTbMenu().getTitulo());
				//First submenu
			 	submenu = new Submenu();
			 	submenu.setLabel(tb.getTbMenu().getTitulo());
			} else {
				System.out.println("- " + tb.getTbMenu().getTitulo());
				MenuItem item = new MenuItem();
	 			item.setImmediate(true);
	 			item.setProcess("@this");
	 			item.setAjax(false); // ajax true para no refrescar la pagina (oculta la url)
	 		 	item.setValue(tb.getTbMenu().getTitulo());
	 		 	if (tb.getTbMenu().getAction().equals("menuBlanco"))
	 		 		item.setUrl("menuBlanco.xhtml");
	 		 	else
	 		 		item.setAction(createMethodBinding(tb.getTbMenu().getAction()));
	 		 	item.setActionListener(createMethodBinding(tb.getTbMenu().getActionlistener()));
	 		 	item.setIcon(tb.getTbMenu().getIcono());
	 		 	item.setUpdate("messages");
	 		 	submenu.getChildren().add(item);
			}
			model.addSubmenu(submenu);
		}
	}
	
	@SuppressWarnings("deprecation")
	private MethodBinding createMethodBinding(String action) {
		MethodBinding methodBinding = null;
		methodBinding = FacesContext.getCurrentInstance().getApplication().createMethodBinding(action, null);
		return methodBinding;
	}
	
	// No usado, pero igual funciona
	/*public String validar2()throws Exception {
		try {
			System.out.println("Entro al Action");
			usuarioService = abd.getUsuarioService();
			usuario = usuarioService.logueo(usuario.getLogin(),MD5Util.md5Hex(usuario.getPassword()));
			if (usuario != null) {
				if (usuario.getEstado() == 1) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error Usuario: " + usuario.getLogin() + " bloqueado!")); 
					System.out.println("Su cuenta se encuentra bloqueada");
					return null;
				} else {
					areamenuList = usuarioService.permisos(usuario.getTbAreaIdArea());

					System.out.println(MD5Util.md5Hex("Ricardo"));
					return "menuBlanco";
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Su login o contraseña son incorrectos..."));
				System.out.println("Su login o contraseña son incorrectos...");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		//return "menuBlanco";
	}*/

	public String CerrarSession() {
		System.out.println("Estoy dentro del método CerrarSession() - INI");
		Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session.remove("login");
		session.clear();
		System.out.println("Estoy dentro del método CerrarSession() - FIN");
		return "inicio";
	}
	
}
