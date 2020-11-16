package pe.edu.proyecto.jsf.managed;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.EquipoService;
import pe.edu.proyecto.business.service.SolicitudService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbClaseequipo;
import pe.edu.proyecto.persistence.entity.TbEquipo;
import pe.edu.proyecto.persistence.entity.TbSolicitud;
import pe.edu.proyecto.primefaces.util.SolicitudDataModel;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name = "solicitud")
@SessionScoped
public class SolicitudBean {
	
	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static SolicitudService solicitudService;
	private static EquipoService equipoService;
	private static UtilService utilService;
	
	// Instancies
	private TbSolicitud item = new TbSolicitud();
	private TbEquipo equipo = new TbEquipo();
	private TbClaseequipo claseequipo = new TbClaseequipo();
	
	// Lists
	private List<TbSolicitud> solicitudList;
	private List<TbEquipo> equipoList;
	private List<TbClaseequipo> claseequipoList;
	
	// Atributtes 
	private String buscTarjeta;
	private String nroTarjeta;
	private boolean flag;
	
	// Getters and setters
	public TbSolicitud getItem() {
		return item;
	}
	public void setItem(TbSolicitud item) {
		this.item = item;
	}
	public TbEquipo getEquipo() {
		return equipo;
	}
	public void setEquipo(TbEquipo equipo) {
		this.equipo = equipo;
	}
	public TbClaseequipo getClaseequipo() {
		return claseequipo;
	}
	public void setClaseequipo(TbClaseequipo claseequipo) {
		this.claseequipo = claseequipo;
	}
	
	public List<TbSolicitud> getSolicitudList() {
		return solicitudList;
	}
	public void setSolicitudList(List<TbSolicitud> solicitudList) {
		this.solicitudList = solicitudList;
	}
	public List<TbEquipo> getEquipoList() {
		return equipoList;
	}
	public void setEquipoList(List<TbEquipo> equipoList) {
		this.equipoList = equipoList;
	}
	public List<TbClaseequipo> getClaseequipoList() {
		return claseequipoList;
	}
	public void setClaseequipoList(List<TbClaseequipo> claseequipoList) {
		this.claseequipoList = claseequipoList;
	}
	
	public String getBuscTarjeta() {
		return buscTarjeta;
	}
	public void setBuscTarjeta(String buscTarjeta) {
		this.buscTarjeta = buscTarjeta;
	}
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	public String listarSoliccitudes() throws Exception {
		System.out.println("Estoy dentro del método listarSoliccitudes() - INI");
		
		solicitudService = abd.getSolicitudService();
		solicitudList = solicitudService.listarSolicitudes();
		
		// Extra PrimeFaces listado
		mediumSolicitudesModel = new SolicitudDataModel(solicitudList);
		
		System.out.println("Estoy dentro del método listarSoliccitudes() - FIN");
		return "solicitudServicio";
	}
	
	public String edit() throws Exception{
		System.out.println("Estoy dentro del método edit() - INI");
		
		item = new TbSolicitud();
		item.setEstado("1");
		buscTarjeta = "";
		nroTarjeta = "";
		flag = false;
		
//		equipoService = abd.getEquipoService();
//		equipoList = equipoService.listarEquipos();

		utilService = abd.getUtilService();
		claseequipoList = utilService.listarClasesEquipos();
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");
			solicitudService = abd.getSolicitudService();
			item.setIdSolicitud(Integer.parseInt(codigo));
			item = solicitudService.obtenerSolicitud(item);
			System.out.println("equipo: " + item.getTbEquipo().getNrotarjeta());
			flag = true;
		} 

		System.out.println("Estoy dentro del método edit() - FIN");
		return "solicitudServicioForm";
	}
	
    public String insertarOActualizar() throws Exception{
    	System.out.println("Estoy dentro del método insertarOActualizar() - INI");
    	
    	if (item.getTbEquipo().getNrotarjeta() == null) {
    		Mensajes.info("¡Debe ingresar un equipo!", null);
			return null;
    	}
    	
    	if (item.getFecha() == null) {
			Mensajes.info("¡Debe ingresar una fecha!", null);
			return null;
		}
		
    	if (item.getHorometroActual() == 0) {
			Mensajes.info("¡Debe ingresar el horómetro!", null);
			return null;
		}
    	
    	if (item.getRepresentante().equals("")) {
			Mensajes.info("¡Debe ingresar el representante!", null);
			return null;
		}
    	
    	if (item.getTelefono().equals("")) {
			Mensajes.info("¡Debe ingresar un teléfono!", null);
			return null;
		}
    	
    	if (item.getDesProblema().equals("")) {
			Mensajes.info("¡Debe ingresar una descripción!", null);
			return null;
		}
    	
    	solicitudService = abd.getSolicitudService();
		if (item.getIdSolicitud() == null) {
			System.out.println("Insertando solicitud");
			// setear el equipo - modo 1
			/*TbEquipo tbEquipo =  new TbEquipo();
	    	tbEquipo.setNrotarjeta(buscTarjeta);
	    	item.setTbEquipo(tbEquipo);*/
			//item.getTbEquipo().setNrotarjeta(buscTarjeta); // setear el equipo modo 2
			solicitudService.registrarSolicitud(item);
			Mensajes.info("Exitoso", "¡Solicitud Registrada!");
		} else {
			System.out.println("Actualizando solicitud");
			// setear el equipo - modo 1
			/*if (buscTarjeta.length() > 0) {
				TbEquipo tbEquipo =  new TbEquipo();
		    	tbEquipo.setNrotarjeta(buscTarjeta);
		    	item.setTbEquipo(tbEquipo);
			}*/
			solicitudService.actualizarSolicitud(item);
			Mensajes.info("Exitoso", "¡Solicitud Actualizada!");
			flag = false;
		}
		
		System.out.println("Estoy dentro del método insertarOActualizar() - FIN");
    	return listarSoliccitudes();
    }

    public String eliminar() throws Exception{
    	System.out.println("Estoy dentro del método eliminar() - INI");
    	
    	String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
    	item = new TbSolicitud();
    	item.setIdSolicitud(Integer.parseInt(codigo));
    	solicitudService = abd.getSolicitudService();
    	if (solicitudService.eliminarSolicitud(item) == 1) {
    		Mensajes.error("Error", "¡La solicitud no se pudo eliminar!");
			return null;
    	}

    	Mensajes.info("Exitoso", "Solicitud Eliminada!");
    	
    	System.out.println("Estoy dentro del método eliminar() - FIN");
		return listarSoliccitudes();
	}
    
    public String ObtenerEquipo() throws Exception {
    	System.out.println("Estoy dentro del método ObtenerEquipo() - INI");
		
		if (buscTarjeta.trim().length() <= 0) {
			Mensajes.info("¡Ingrese un equipo!", null);
			return null;
		}
		
		equipoService = abd.getEquipoService();
		equipo = equipoService.obtenerEquipo(buscTarjeta);
		
		if (equipo != null) {
			item.setTbEquipo(equipo);
			Calendar c1 = Calendar.getInstance();
			item.setFecha(c1.getTime());
			Mensajes.info("¡Equipo consultado! ".concat(buscTarjeta), null);  
			flag = true;
		} else {
			Mensajes.info("¡El equipo ".concat(buscTarjeta).concat(" no esta registrado!"), null); 
			return null;
		}
		
		System.out.println("Estoy dentro del método ObtenerEquipo() - FIN");
		return "solicitudServicioForm";
	}
	
	public String buscarEquipos() throws Exception {
		System.out.println("Estoy dentro del método buscarEquipos() - INI");
		
		System.out.println("nroTarjeta: " + nroTarjeta);
		equipoService = abd.getEquipoService();
		equipoList = equipoService.buscarEquipo(nroTarjeta, equipo.getTbClaseequipo().getId_claseEquipo());// equipo.getNrotarjeta()

		System.out.println("Estoy dentro del método buscarEquipos() - FIN");
		return "solicitudServicioForm";
	}
	
	public String recuperarEquipo()throws Exception{
		System.out.println("Estoy dentro del método recuperarEquipo() - INI");
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (codigo != null && codigo.length()>0){
			setBuscTarjeta(codigo);
		}
		
		System.out.println("Estoy dentro del método recuperarEquipo() - FIN");
		return "solicitudServicioForm";
	}
	
	
	// Extra PrimeFaces listado
	private SolicitudDataModel mediumSolicitudesModel; 
	
    public SolicitudDataModel getMediumSolicitudesModel() {
		return mediumSolicitudesModel;
	}
    
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Solicitud de", ((TbSolicitud) event.getObject()).getRepresentante());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Quitando", ((TbSolicitud) event.getObject()).getRepresentante());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
}
