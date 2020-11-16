package pe.edu.proyecto.jsf.managed;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.primefaces.util.LiquidacionDataModel;
import pe.edu.proyecto.primefaces.util.OrdenDataModel;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name = "formLiquidacion")
@SessionScoped
public class LiquidacionBean {

	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static LiquidacionService liquidacionService;
	private static OrdenTrabajoService ordenService;
	private static ClienteService clienteService;

	// Instances
	private TbOrdenliquidacion liquidacion = new TbOrdenliquidacion();
	private TbOrdentrabajo orden = new TbOrdentrabajo();
	private TbCliente cliente = new TbCliente();

	// Lists
	private List<TbOrdenliquidacion> liquidacionList;
	private List<TbOrdentrabajo> ordenList;
	private List<TbCliente> clienteList;

	// Attributes
	private String rucCli;
	private String razCli;
	private boolean mostrar;

	// Getters and setters
	public TbOrdenliquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(TbOrdenliquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public TbOrdentrabajo getOrden() {
		return orden;
	}

	public void setOrden(TbOrdentrabajo orden) {
		this.orden = orden;
	}

	public TbCliente getCliente() {
		return cliente;
	}

	public void setCliente(TbCliente cliente) {
		this.cliente = cliente;
	}

	public List<TbOrdenliquidacion> getLiquidacionList() {
		return liquidacionList;
	}

	public void setLiquidacionList(List<TbOrdenliquidacion> liquidacionList) {
		this.liquidacionList = liquidacionList;
	}

	public List<TbOrdentrabajo> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<TbOrdentrabajo> ordenList) {
		this.ordenList = ordenList;
	}

	public List<TbCliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<TbCliente> clienteList) {
		this.clienteList = clienteList;
	}

	public String getRucCli() {
		return rucCli;
	}

	public void setRucCli(String rucCli) {
		this.rucCli = rucCli;
	}

	public String getRazCli() {
		return razCli;
	}

	public void setRazCli(String razCli) {
		this.razCli = razCli;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	// Actions methods
	public String listarLiquidaciones() throws Exception {
		System.out.println("Estoy dentro del método listarLiquidaciones() - INI");

		liquidacionService = abd.getLiquidacionService();
		liquidacionList = liquidacionService.listarLiquidaciones();

		// Modificado PrimeFaces para listado
		mediumLiquidacionesModel = new LiquidacionDataModel(liquidacionList);

		System.out.println("Estoy dentro del método listarLiquidaciones() - FIN");
		return "liquidacion";
	}

	public String editar() throws Exception {
		System.out.println("Estoy dentro del método edit() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");

			liquidacionService = abd.getLiquidacionService();
			liquidacion = new TbOrdenliquidacion();
			liquidacion.setIdLiquidacion(Integer.parseInt(codigo));
			liquidacion = liquidacionService.obtenerLiquidacion(liquidacion);

			mediumOrdenesModel = new OrdenDataModel();
			obtenerOrdenesLiquidadas();

			mostrar = false;
		} else {
			System.out.println("Entramos a registrar");

			liquidacion = new TbOrdenliquidacion();
			ordenList = new ArrayList<TbOrdentrabajo>();
			mediumOrdenesModel = new OrdenDataModel(ordenList);
			
			String login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
			String usuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usuario");
			liquidacion.setUsuario(login);
			liquidacion.setNombre(usuario);
			Calendar c1 = Calendar.getInstance();
			liquidacion.setFecha(c1.getTime());
			
			mostrar = false;
		}

		System.out.println("Estoy dentro del método edit() - INI");
		return "liquidacionForm";
	}

	public String guardar() throws Exception {
		System.out.println("Estoy dentro del método insertarOActualizar() - INI");
		try {
			List<TbOrdentrabajo> lista = new ArrayList<TbOrdentrabajo>();
			lista = Arrays.asList(selectedOrdenes);

			if (lista == null)
				lista = new ArrayList<TbOrdentrabajo>();

			if (lista.size() != 0) {
				System.out.println("Ordenes Seleccionadas: " + lista.size());

				liquidacionService = abd.getLiquidacionService();
				liquidacionService.registrarLiquidacion(liquidacion);
				Mensajes.info("Exitoso", "¡Orden de Liquidación registrada!");

				if (liquidacionService.registrarLiquidacion(liquidacion) == 0) {
					liquidacion = liquidacionService.obtenerLiquidacion(liquidacion);
					for (Method m : liquidacion.getClass().getMethods()) {
						if (m.getName().startsWith("get"))
							System.out.println(m.getName() + " : " + m.invoke(liquidacion));
					}

					Iterator<TbOrdentrabajo> it = lista.iterator();
					while (it.hasNext()) {
						orden = (TbOrdentrabajo) it.next();
						orden.setIdLiquidacion(liquidacion.getIdLiquidacion());
						ordenService.actualizar(orden);
					}
				}
			} else {
				Mensajes.info("¡Debe Selecionar las ordenes de trabajo a Liquidar!", null);
				return "null";
			}
		} catch (Exception e) {
			Mensajes.info("¡Error en el Ingreso, Verifique los datos Ingresados!", null);
			return "null";
		}
		
		System.out.println("Estoy dentro del método insertarOActualizar() - INI");
		return listarLiquidaciones();
	}

	public String eliminar() throws Exception {
		System.out.println("Estoy dentro del método eliminarLiquidacion() - INI");

		System.out.println("Estoy dentro del método eliminarLiquidacion() - Fin");
		return "liquidacionForm";
	}

	public String obtenerOrdenes() throws Exception {
		System.out.println("Estoy dentro del método obtenerOrdenes - INI");

		ordenService = abd.getOrdenTrabajoService();
		cliente.setIdCliente(liquidacion.getIdCliente());
		ordenList = ordenService.obtenerOrdenesXCliente(cliente);

		// Modificado PrimeFaces para listado
		mediumOrdenesModel = new OrdenDataModel(ordenList);

		if (liquidacion.getIdCliente().length() == 0) {
			Mensajes.info("¡Ingrese un cliente!", null);
			return null;
		}

		if (ordenList.size() != 0) {
			Mensajes.info("¡Se obtuvieron " + ordenList.size() + " resultados!", null);
			Calendar c1 = Calendar.getInstance();
			liquidacion.setFecha(c1.getTime());
			mostrar = true;
		} else {
			Mensajes.info("¡El cliente " + liquidacion.getIdCliente() + " no tiene ordenes!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerOrdenes - FIN");
		return "liquidacionForm";
	}

	public String obtenerOrdenesLiquidadas() throws Exception {
		System.out.println("Estoy dentro del método obtenerOrdenesLiquidadas() - INI ");

		ordenService = abd.getOrdenTrabajoService();
		ordenList = ordenService.obtenerOrdenXIdLiqui(liquidacion.getIdLiquidacion());

		// Modificado PrimeFaces para listado
		mediumOrdenesModel = new OrdenDataModel(ordenList);

		mostrar = true;

		System.out.println("Estoy dentro del método obtenerOrdenesLiquidadas() - FIN ");
		return "liquidacionForm";
	}

	public String buscarCliente() throws Exception {
		System.out.println("Estoy dentro de método buscarCliente() - INI");

		clienteService = abd.getClienteService();
		cliente =  new TbCliente();
		cliente.setIdCliente(rucCli);
		cliente.setRazonsocial(razCli);
		clienteList = clienteService.buscarClienre(cliente);

		System.out.println("Estoy dentro de método buscarCliente() - FIN");
		return "liquidacionForm";
	}

	public String recuperarCliente() throws Exception {
		System.out.println("Estoy dentro del método recuperarCliente - INI");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");

		liquidacion.setIdCliente(codigo);
		rucCli = "";
		razCli = "";

		System.out.println("Estoy dentro del método recuperarCliente - FIN");
		return "liquidacionForm";
	}

	// Extra PrimeFaces listado liquidacion
	private LiquidacionDataModel mediumLiquidacionesModel;

	public LiquidacionDataModel getMediumLiquidacionesModel() {
		return mediumLiquidacionesModel;
	}

	public void onRowSelect(SelectEvent event) {
		Mensajes.info("Liquidación del Cliente ", ((TbOrdenliquidacion) event.getObject()).getIdCliente());
	}

	public void onRowUnselect(UnselectEvent event) {
		Mensajes.info("Liquidación ", Integer.toString(((TbOrdenliquidacion) event.getObject()).getIdLiquidacion()));
	}

	// Extra PrimeFaces listado ordenes
	private OrdenDataModel mediumOrdenesModel;
	private TbOrdentrabajo[] selectedOrdenes;

	public TbOrdentrabajo[] getSelectedOrdenes() {
		return selectedOrdenes;
	}

	public void setSelectedOrdenes(TbOrdentrabajo[] selectedOrdenes) {
		this.selectedOrdenes = selectedOrdenes;
	}

	public OrdenDataModel getMediumOrdenesModel() {
		return mediumOrdenesModel;
	}

}