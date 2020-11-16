package pe.edu.proyecto.jsf.managed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.ClienteService;
import pe.edu.proyecto.business.service.LiquidacionService;
import pe.edu.proyecto.business.service.OrdenTrabajoService;
import pe.edu.proyecto.business.service.PreFacturaService;
import pe.edu.proyecto.business.service.SolicitudService;
import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.entity.TbDetalleprefactura;
import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbPrefactura;
import pe.edu.proyecto.persistence.entity.TbSolicitud;
import pe.edu.proyecto.primefaces.util.PrefacturaDataModel;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name="formPrefactura")
@SessionScoped
public class PreFacturaBean {

	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static PreFacturaService prefacturaService;
	private static LiquidacionService liquidacionService;
	private static ClienteService clienteService;
	private static SolicitudService solicitudService;
	private static OrdenTrabajoService ordenService;
	
	private TbPrefactura prefactura= new TbPrefactura();
	private TbOrdenliquidacion liquidacion = new TbOrdenliquidacion();
	private TbSolicitud solicitud = new TbSolicitud();
	private TbOrdentrabajo orden = new TbOrdentrabajo();
	
	private List<TbPrefactura> prefacturaList;
	private List<TbDetalleprefactura> detalleprefacturaList;
	private List<TbOrdentrabajo> ordenList;
	private List<TbOrdenliquidacion> liquidacionList;
	
	private boolean mostrar;

	public TbPrefactura getPrefactura() {
		return prefactura;
	}
	public void setPrefactura(TbPrefactura prefactura) {
		this.prefactura = prefactura;
	}
	public TbOrdenliquidacion getLiquidacion() {
		return liquidacion;
	}
	public void setLiquidacion(TbOrdenliquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}
	public TbSolicitud getSolictud() {
		return solicitud;
	}
	public void setSolicitud(TbSolicitud solicitud) {
		this.solicitud = solicitud;
	}
	public TbOrdentrabajo getOrden() {
		return orden;
	}
	public void setOrden(TbOrdentrabajo orden) {
		this.orden = orden;
	}
	
	public List<TbPrefactura> getPrefacturaList() {
		return prefacturaList;
	}
	public void setPrefacturaList(List<TbPrefactura> prefacturaList) {
		this.prefacturaList = prefacturaList;
	}
	public List<TbDetalleprefactura> getDetalleprefacturaList() {
		return detalleprefacturaList;
	}
	public void setDetalleprefacturaList(
			List<TbDetalleprefactura> detalleprefacturaList) {
		this.detalleprefacturaList = detalleprefacturaList;
	}
	public List<TbOrdentrabajo> getOrdenList() {
		return ordenList;
	}
	public void setOrdenList(List<TbOrdentrabajo> ordenList) {
		this.ordenList = ordenList;
	}
	public List<TbOrdenliquidacion> getLiquidacionList() {
		return liquidacionList;
	}
	public void setLiquidacionList(List<TbOrdenliquidacion> liquidacionList) {
		this.liquidacionList = liquidacionList;
	}
	
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	
	public String listarPrefacturas()throws Exception{
		System.out.println("Estoy dentro del método listarPrefacturas() - INI ");
		
		prefacturaService = abd.getPreFacturaService();
		prefacturaList = prefacturaService.listadoPrefacturas();
		
		// Extra PrimeFaces listado
		mediumPrefacturasModel = new PrefacturaDataModel(prefacturaList);
		
		System.out.println("Estoy dentro del método listarPrefacturas() - INI ");
		return "prefactura";
	}
	
	public String edit() throws Exception{
		System.out.println("Estoy dentro del método edit() - INI");
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (codigo!=null && codigo.length()>0){
			System.out.println("Entramos a editar");
			
			prefacturaService = abd.getPreFacturaService();
			prefactura =  new TbPrefactura();
			prefactura.setIdPrefactura(Integer.parseInt(codigo));
			prefactura = prefacturaService.obtenerPrefactura(prefactura);
			
			detalleprefacturaList = new ArrayList<TbDetalleprefactura>();
			
			obtenerDetallePrefactura();
			mostrar = false;
		}else{
			System.out.println("Entramos a registrar");
			
			prefactura = new TbPrefactura();
			detalleprefacturaList = new ArrayList<TbDetalleprefactura>();
			String login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
			prefactura.setUsuario(login);
			Calendar c = Calendar.getInstance();
			prefactura.setFecha(c.getTime());
			mostrar = false;
		}
		
		System.out.println("Estoy dentro del método edit() - FIN");
		return "prefacturaForm";
	}

	public String registrarPrefactura() throws Exception {
		System.out.println("Estoy dentro del método registrarPrefactura() -INI");
		
		try {
			if (detalleprefacturaList == null){
				detalleprefacturaList = new ArrayList<TbDetalleprefactura>();
			}
			
			if (prefactura.getTb_ordenLiquidacion_id_liquidacion()==0){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe ingresar una orden de liquidación!"));
				return null;
			}
				
			if (this.detalleprefacturaList.size() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No se cargaron ordenes!"));
				return null;
			}
				
			prefacturaService = abd.getPreFacturaService();
			prefacturaService.registrarPrefactura(this.prefactura, this.detalleprefacturaList);
			
			// Actualizar estado de la solicitud a finalizado
			liquidacionService = abd.getLiquidacionService();
			liquidacion.setIdLiquidacion(prefactura.getTb_ordenLiquidacion_id_liquidacion());
			liquidacion = liquidacionService.obtenerLiquidacion(liquidacion);
			
			ordenService = abd.getOrdenTrabajoService();
			Iterator<TbOrdentrabajo> it = ordenService.obtenerOrdenXIdLiqui(liquidacion.getIdLiquidacion()).iterator();
			while (it.hasNext()){
				orden =  it.next();
				solicitudService = abd.getSolicitudService();
				solicitud = solicitudService.obtenerSolicitud(orden.getTbSolicitudIdSolicitud());
				
				solicitud.setEstado("3");
				solicitudService.actualizarSolicitud(solicitud);
				
//				utilService = abd.getUtilService();
//				estado = utilService.getEstado(2);
//				solicitud.setEstado(Integer.toString(estado.getIdtbEstado()));
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "Prefactura y Detalle registrada!"));
			
			// Actualizar estado de la liquidacion
			liquidacionService = abd.getLiquidacionService();
			liquidacion.setIdLiquidacion(prefactura.getTb_ordenLiquidacion_id_liquidacion());
			liquidacion = liquidacionService.obtenerLiquidacion(liquidacion);
			
			liquidacion.setEstado(1);
			liquidacionService.actualizarLiquidacion(liquidacion);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Excepción", "Verifique sus datos"));
			return null;
		}
		
		System.out.println("Estoy dentro del método registrarPrefactura() -INI");
		return listarPrefacturas();
	}
	
	
	// Util
	public String obtenerDetallePrefactura() throws Exception {
		System.out.println("Estoy dentro del método obtenerLiquidaciones() - INI");
		
		if (prefactura.getTb_ordenLiquidacion_id_liquidacion() == 0){
			Mensajes.info("¡Ingrese una orden de liquidación", null);
			return null;
		}
		
		liquidacionService = abd.getLiquidacionService();
		liquidacion = new TbOrdenliquidacion();
		liquidacion.setIdLiquidacion(prefactura.getTb_ordenLiquidacion_id_liquidacion());
		liquidacion = liquidacionService.obtenerLiquidacion(liquidacion);
		
		if (liquidacion != null) {
			clienteService = abd.getClienteService();
			TbCliente cliente = new TbCliente();
			cliente.setIdCliente(liquidacion.getIdCliente());
			cliente = clienteService.obtenerCliente(cliente);
			
//			if (liquidacion.getEstado() != 0) {
//				Mensajes.info("¡La orden de liquidación " + liquidacion.getIdLiquidacion() + " ya ha sido atendida!", null);
//				liquidacion = null;
//			}else{
				//Mensajes.info("¡Se agregó la Orden de Liquidación " + prefactura.getTb_ordenLiquidacion_id_liquidacion() + "!", null);
				prefactura.setRuc(liquidacion.getIdCliente());
				prefactura.setCliente(cliente.getRazonsocial());
				prefactura.setDireccion(cliente.getDireccion());
				Calendar c = Calendar.getInstance();
				prefactura.setFecha(c.getTime());
				
				prefacturaService = abd.getPreFacturaService();
				//detalleprefacturaList = prefacturaService.getDetFactPaq(prefactura);
				detalleprefacturaList = new ArrayList<TbDetalleprefactura>();
				detalleprefacturaList.addAll(prefacturaService.getDetFactPaq(prefactura));
				detalleprefacturaList.addAll(prefacturaService.getDetFactTec(prefactura));
				
				mostrar = true;
//			}
		} else {
			Mensajes.info("¡La orden de liquidación " + prefactura.getTb_ordenLiquidacion_id_liquidacion() + " no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerLiquidaciones() - FIN");
		return "prefacturaForm";
	}
	public String listarLiquidaciones() throws Exception {
		System.out.println("Estoy dentro del método listarLiquidaciones() - INI");
	
		liquidacionService = abd.getLiquidacionService();
		liquidacionList = liquidacionService.listarLiquidaciones();
	
		System.out.println("Estoy dentro del método listarLiquidaciones() - FIN");
		return "prefacturaForm";//buscar_liquidacion
	}
	public String recuperarLiquidacion() throws Exception{
		System.out.println("Estoy dentro del método recuperarLiquidacion() - INI");
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		prefactura.setTb_ordenLiquidacion_id_liquidacion(Integer.parseInt(codigo));
		
		System.out.println("Estoy dentro del método recuperarLiquidacion() - FIN");
		return "prefacturaForm";
	}
	
	// Extra PrimeFaces listado
	private PrefacturaDataModel mediumPrefacturasModel; 
    
	public PrefacturaDataModel getMediumPrefacturasModel() {
		return mediumPrefacturasModel;
	}
	
	public void setMediumPrefacturasModel(PrefacturaDataModel mediumPrefacturasModel) {
		this.mediumPrefacturasModel = mediumPrefacturasModel;
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Pre-Factura de", ((TbPrefactura) event.getObject()).getCliente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
    	FacesMessage msg = new FacesMessage("Prefactura de", ((TbPrefactura) event.getObject()).getCliente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

}
