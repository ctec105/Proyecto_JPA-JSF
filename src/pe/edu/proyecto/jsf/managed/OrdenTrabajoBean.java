package pe.edu.proyecto.jsf.managed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.OrdenTrabajoService;
import pe.edu.proyecto.business.service.PaqueteService;
import pe.edu.proyecto.business.service.SolicitudService;
import pe.edu.proyecto.business.service.TecnicoService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbEspecialidade;
import pe.edu.proyecto.persistence.entity.TbEstado;
import pe.edu.proyecto.persistence.entity.TbMarca;
import pe.edu.proyecto.persistence.entity.TbModelo;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajoHasTbTecnico;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbSolicitud;
import pe.edu.proyecto.persistence.entity.TbTecnico;
import pe.edu.proyecto.primefaces.util.OrdenDataModel;
import pe.edu.proyecto.util.Mensajes;
import pe.edu.proyecto.util.UtilesVarios;

@ManagedBean(name = "formOrden")
@SessionScoped
public class OrdenTrabajoBean {

	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static SolicitudService solicitudService;
	private static OrdenTrabajoService ordenService;
	private static PaqueteService paqueteServive;
	private static TecnicoService tecnicoServive;
	private static UtilService utilService;

	// Instances
	private TbOrdentrabajo orden = new TbOrdentrabajo();
	private TbSolicitud solicitud = new TbSolicitud();
	private TbEstado estado = new TbEstado();
	private TbPaquete paquete = new TbPaquete();
	private TbMarca marca = new TbMarca();
	private TbModelo modelo = new TbModelo();
	private TbTecnico tecnico = new TbTecnico();

	// Lists
	private List<TbOrdentrabajo> ordenList;
	private List<TbSolicitud> solicitudList;
	private List<TbEstado> estadoList;
	private List<TbPaquete> paqueteList;
	private List<TbMarca> marcaList;
	private List<TbModelo> modeloList;
	private List<TbTecnico> tecnicoList;
	private List<TbEspecialidade> especialidadList;

	// Modal
	private List<TbPaquete> modalPaqueteList;
	private List<TbTecnico> modalTecnicoList;

	// Atributes
	private String buscSolic;
	private String buscPaq;
	private String busTec;
	private String repreSol;
	private String nomPaq;
	private String nomTec;
	private int estSol;
	private int espTec;
	private int marPaq;
	private int modPaq;
	private boolean mostrar;

	// Getters and setters
	public TbOrdentrabajo getOrden() {
		return orden;
	}

	public void setOrden(TbOrdentrabajo orden) {
		this.orden = orden;
	}

	public TbSolicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(TbSolicitud solicitud) {
		this.solicitud = solicitud;
	}

	public TbEstado getEstado() {
		return estado;
	}

	public void setEstado(TbEstado estado) {
		this.estado = estado;
	}

	public TbPaquete getPaquete() {
		return paquete;
	}

	public void setPaquete(TbPaquete paquete) {
		this.paquete = paquete;
	}

	public TbMarca getMarca() {
		return marca;
	}

	public void setMarca(TbMarca marca) {
		this.marca = marca;
	}

	public TbModelo getModelo() {
		return modelo;
	}

	public void setModelo(TbModelo modelo) {
		this.modelo = modelo;
	}

	public TbTecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(TbTecnico tecnico) {
		this.tecnico = tecnico;
	}

	public List<TbOrdentrabajo> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<TbOrdentrabajo> ordenList) {
		this.ordenList = ordenList;
	}

	public List<TbSolicitud> getSolicitudList() {
		return solicitudList;
	}

	public void setSolicitudList(List<TbSolicitud> solicitudList) {
		this.solicitudList = solicitudList;
	}

	public List<TbEstado> getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(List<TbEstado> estadoList) {
		this.estadoList = estadoList;
	}

	public List<TbPaquete> getPaqueteList() {
		return paqueteList;
	}

	public void setPaqueteList(List<TbPaquete> paqueteList) {
		this.paqueteList = paqueteList;
	}

	public List<TbMarca> getMarcaList() {
		return marcaList;
	}

	public void setMarcaList(List<TbMarca> marcaList) {
		this.marcaList = marcaList;
	}

	public List<TbModelo> getModeloList() {
		return modeloList;
	}

	public void setModeloList(List<TbModelo> modeloList) {
		this.modeloList = modeloList;
	}

	public List<TbTecnico> getTecnicoList() {
		return tecnicoList;
	}

	public void setTecnicoList(List<TbTecnico> tecnicoList) {
		this.tecnicoList = tecnicoList;
	}

	public List<TbEspecialidade> getEspecialidadList() {
		return especialidadList;
	}

	public void setEspecialidadList(List<TbEspecialidade> especialidadList) {
		this.especialidadList = especialidadList;
	}
	
	public List<TbPaquete> getModalPaqueteList() {
		return modalPaqueteList;
	}

	public void setModalPaqueteList(List<TbPaquete> modalPaqueteList) {
		this.modalPaqueteList = modalPaqueteList;
	}

	public List<TbTecnico> getModalTecnicoList() {
		return modalTecnicoList;
	}

	public void setModalTecnicoList(List<TbTecnico> modalTecnicoList) {
		this.modalTecnicoList = modalTecnicoList;
	}

	public String getBuscSolic() {
		return buscSolic;
	}

	public void setBuscSolic(String buscSolic) {
		this.buscSolic = buscSolic;
	}

	public String getBuscPaq() {
		return buscPaq;
	}

	public void setBuscPaq(String buscPaq) {
		this.buscPaq = buscPaq;
	}

	public String getBusTec() {
		return busTec;
	}

	public void setBusTec(String busTec) {
		this.busTec = busTec;
	}

	public String getRepreSol() {
		return repreSol;
	}

	public void setRepreSol(String repreSol) {
		this.repreSol = repreSol;
	}

	public String getNomPaq() {
		return nomPaq;
	}

	public void setNomPaq(String nomPaq) {
		this.nomPaq = nomPaq;
	}

	public String getNomTec() {
		return nomTec;
	}

	public void setNomTec(String nomTec) {
		this.nomTec = nomTec;
	}

	public int getEstSol() {
		return estSol;
	}

	public void setEstSol(int estSol) {
		this.estSol = estSol;
	}

	public int getEspTec() {
		return espTec;
	}

	public void setEspTec(int espTec) {
		this.espTec = espTec;
	}

	public int getMarPaq() {
		return marPaq;
	}

	public void setMarPaq(int marPaq) {
		this.marPaq = marPaq;
	}

	public int getModPaq() {
		return modPaq;
	}

	public void setModPaq(int modPaq) {
		this.modPaq = modPaq;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	
	// Actions methods
	public String listarOrdenes() throws Exception {
		System.out.println("Estoy en listar listarOrdenes() - Ini");

		ordenService = abd.getOrdenTrabajoService();
		ordenList = ordenService.listarOrdenes();

		// Modificado PrimeFaces para listado
		mediumOrdenesModel = new OrdenDataModel(ordenList);

		System.out.println("Estoy en listarOrdenes() - Fin");
		return "ordenTrabajo";
	}

	public String editar() throws Exception {
		System.out.println("Estoy dentro del método edit() - INI");
		
		buscSolic = "";
		buscPaq = "";
		busTec = "";
		repreSol = "";
		nomPaq = "";
		nomTec = "";
		
		utilService = abd.getUtilService();
		estadoList = utilService.listarEstados();
		marcaList = utilService.listarMarcas();
		// modeloList = utilService.listarModelos();
		modeloList = utilService.listarModelosxMarca("1");
		especialidadList = utilService.listarEspecialidades();
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");
			ordenService = abd.getOrdenTrabajoService();
			orden =  new TbOrdentrabajo();
			orden.setIdOtrabajo(Integer.parseInt(codigo));
			orden = ordenService.obtenerOrden(orden);

			System.out.println("Solicitud de Servicio: " + orden.getTbSolicitudIdSolicitud());
			solicitudService = abd.getSolicitudService();
			solicitud = solicitudService.obtenerSolicitud(orden.getTbSolicitudIdSolicitud());

			System.out.println("Listado de Paquetes de la orden" + codigo);
			paqueteServive = abd.getPaqueteService();
			paqueteList = obtenerDetallePaquetes(orden.getIdOtrabajo());

			System.out.println("Listado de Técnicos de la orden" + codigo);
			tecnicoServive = abd.getTecnicoService();
			tecnicoList = obtenerDetalleTecnicos(orden.getIdOtrabajo());

			mostrar = true;
		} else {
			System.out.println("Entrando a registrar");
			
			orden = new TbOrdentrabajo();
			String login = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
			orden.setUsuario(login);
			solicitud = new TbSolicitud();
			paqueteList = new ArrayList<TbPaquete>();
			tecnicoList = new ArrayList<TbTecnico>();
			
			mostrar = false;
		}
		
		System.out.println("Estoy dentro del método edit() - FIN");
		return "ordenTrabajoForm";
	}

	public String guardar() throws Exception {
		System.out.println("Estoy dentro del método insertarOActualizar() - INI");

		UtilesVarios ut = new UtilesVarios();
		ordenService = abd.getOrdenTrabajoService();

		try {

			if (paqueteList == null)
				paqueteList = new ArrayList<TbPaquete>();

			if (tecnicoList == null)
				tecnicoList = new ArrayList<TbTecnico>();

			if (orden.getFechaInicio() == null || orden.getFechaTermino() == null) {
				Mensajes.info("¡Debe ingresar los campos de fechas!", null);
				return null;
			}

			if (ut.fechasDiferenciaEnDias(orden.getFechaInicio(), orden.getFechaTermino()) < 1) {
				Mensajes.info("¡La fecha fin debe ser mayor a la de inicio!", null);
				return null;
			}

			if (orden.getTbSolicitudIdSolicitud() == 0) {
				Mensajes.info("Debe ingresar una solicitud de Servicio!", null);
				return null;
			}

			if (paqueteList.size() == 0) {
				Mensajes.info("Debe ingresar un paquete de Servicio!", null);
				return null;
			}
			if (tecnicoList.size() == 0) {
				Mensajes.info("Debe ingresar un Tecnico disponible!", null);
				return null;
			}

			if (orden.getIdOtrabajo() == 0) {
				if (ordenService.registrarOrden(orden, paqueteList, tecnicoList) == 1) {
					Mensajes.error("Error", "¡La Orden de Trabajo no se pudo registrar!");
					//return null;
				} else {
					//utilService = abd.getUtilService();
					//estado = utilService.getEstado(2);
					solicitudService = abd.getSolicitudService();
					solicitud = solicitudService.obtenerSolicitud(orden.getTbSolicitudIdSolicitud());
					//solicitud.setEstado(Integer.toString(estado.getIdtbEstado()));
					solicitud.setEstado("2");
					solicitudService.actualizarSolicitud(solicitud);
					Mensajes.info("Exitoso", "Orden de Trabajo registrada!");
				}
			} else {
				if (ordenService.actualizarOrden(orden, paqueteList, tecnicoList) == 1) {
					Mensajes.error("Error", "¡La Orden de Trabajo no se pudo actualizar!");
					//return null;
				} else
					Mensajes.info("Exitoso", "Orden de Trabajo actualizada!");
			}
		} catch (Exception e) {
			Mensajes.warn("Excepción, verifique sus datos", null);
			return null;
		}

		System.out.println("Estoy dentro del método insertarOActualizar() - INI");
		return listarOrdenes();
	}

	public String eliminar() throws Exception {
		return "ordenTrabajo";
	}

	public String obtenerSolicitud() throws Exception {
		System.out.println("Estoy dentro del método obtenerSolicitud() - INI");

		if (buscSolic.trim().length() <= 0) {
			Mensajes.info("¡Ingrese una solicitud!", null);
			return null;
		}
		
		solicitudService = abd.getSolicitudService();
		solicitud = solicitudService.obtenerSolicitud(Integer.parseInt(buscSolic));

		if (solicitud != null) {
			if (Integer.parseInt(solicitud.getEstado()) != 1) {
				Mensajes.info("¡La solicitud "+ buscSolic + " ya ha sido atendida", null);
				solicitud = null;
			} else {
				Mensajes.info("¡Se agregó la solicitud " + buscSolic + "!", null);
				orden.setTbSolicitudIdSolicitud(solicitud.getIdSolicitud());
				Calendar c1 = Calendar.getInstance();
				orden.setFechaInicio(c1.getTime());
				orden.setFechaTermino(c1.getTime());
				mostrar = true;
			}
		} else {
			Mensajes.info("¡La solicitud ingresada, " + buscSolic + " no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerSolicitud() - FIN");
		return "ordenTrabajoForm";
	}

	public String buscarSolicitud() throws Exception {
		System.out.println("Estoy dentro del métofo buscarSolicitud() - INI");

		solicitudService = abd.getSolicitudService();
		solicitudList = solicitudService.buscarSolicitud(repreSol, Integer.toString(estSol));

		System.out.println("Estoy dentro del métofo buscarSolicitud() - FIN");
		return "ordenTrabajoForm";
	}

	public String recuperarSolicitud() throws Exception {
		System.out.println("Estoy dentro del método recuperarSolicitud() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBuscSolic(codigo);

		System.out.println("Estoy dentro del método recuperarSolicitud() - FIN");
		return "ordenTrabajoForm";
	}

	public String obtenerPaquete() throws Exception {
		System.out.println("Estoy dentro del método obtenerPaquete() - INI");

		if (buscPaq.trim().length() <= 0) {
			Mensajes.info("Ingrese un paquete!", null);
			return null;
		}
		
		paqueteServive = abd.getPaqueteService();
		paquete = paqueteServive.obtenerPaquete(Integer.parseInt(buscPaq));

		if (paquete != null) {
			paqueteList.add(paquete);
			Mensajes.info("¡Se agregó el paquete " + buscPaq + "!", null);
			mostrar = true;
		} else {
			Mensajes.info("¡El paquete ingresado, " + buscPaq + " no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerPaquete() - FIN");
		return "ordenTrabajoForm";
	}

	public String listarPaquetes() throws Exception {
		System.out.println("Estoy en listar listarPaquetes() - Ini");

		paqueteServive = abd.getPaqueteService();
		modalPaqueteList = paqueteServive.listarPaquetes();

		System.out.println("Estoy en listarPaquetes() - Fin");

		return "ordenTrabajoForm"; // buscar_paquete
	}

	public String buscarPaquete() throws Exception {
		System.out.println("Estoy dentro del método buscarPaquete() - INI");

		paqueteServive = abd.getPaqueteService();
		paquete = new TbPaquete();
		paquete.setNombre(nomPaq);
		paquete.setMarca(Integer.toString(marPaq));
		paquete.setModelo(Integer.toString(modPaq));
		modalPaqueteList = paqueteServive.buscarPaquete(paquete);
		
		System.out.println("Estoy dentro del método buscarPaquete() - FIN");
		return "ordenTrabajoForm";
	}

	public String recuperarPaquete() throws Exception {
		System.out.println("Estoy dentro del método recuperarPaquete() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBuscPaq(codigo);

		System.out.println("Estoy dentro del método recuperarPaquete() - FIN");
		return "ordenTrabajoForm";
	}

	public String obtenerTecnico() throws Exception {
		System.out.println("Estoy dentro del método obtenerTecnico() - INI");

		if (busTec.trim().length() <= 0) {
			Mensajes.info("¡Ingrese un  técnico!", null);
			return null;
		}
		
		tecnicoServive = abd.getTecnicoService();
		tecnico = tecnicoServive.getTecnico(Integer.parseInt(busTec));

		if (tecnico != null) {
			if ((int) tecnico.getDisponible().charAt(0) == (int) 'N') {
				Mensajes.info("¡El ténico " + busTec + " no está disponible!", null);
				tecnico = null;
			} else {
				tecnicoList.add(tecnico);
				Mensajes.info("¡Se agregó el técnico " + busTec + "!", null);
				mostrar = true;
			}
		} else {
			Mensajes.info("¡El técnico ingresado, " + busTec + " no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerTecnico() - FIN");
		return "ordenTrabajoForm";
	}

	public String buscarTecnico() throws Exception {
		System.out.println("Estoy dentro del método buscarTecnico() - INI");

		tecnicoServive = abd.getTecnicoService();
		tecnico =  new TbTecnico();
		tecnico.setNombre(nomTec);
		tecnico.getTbEspecialidade().setIdEspecialidad(espTec);
		modalTecnicoList = tecnicoServive.popBuscarTecn(tecnico);

		System.out.println("Estoy dentro del método buscarTecnico() - FIN");
		return "ordenTrabajoForm";
	}

	public String recuperarTecnico() throws Exception {
		System.out.println("Estoy dentro del método recuperarTecnico() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBusTec(codigo);

		System.out.println("Estoy dentro del método recuperarTecnico() - FIN");
		return "ordenTrabajoForm";
	}

	public String obtenerModelosxMarca() throws Exception {
		System.out.println("Estoy dentro del método obtenerModelos() - INI");

		utilService = abd.getUtilService();
		if (paquete.getMarca() != null && !paquete.getMarca().equals(""))
			modeloList = utilService.listarModelosxMarca(paquete.getMarca());

		System.out.println("Estoy dentro del método obtenerModelos() - FIN");
		return "paqueteForm";
	}
	
	
	// Methods
	private List<TbPaquete> obtenerDetallePaquetes(int idTrabajo) {
		List<TbPaquete> lst = new ArrayList<TbPaquete>();
		try {
			Iterator<TbPaquetesHasTbOrdentrabajo> it = ordenService.getDetallePaquetes(idTrabajo).iterator();
			while (it.hasNext()) {
				TbPaquetesHasTbOrdentrabajo tr = new TbPaquetesHasTbOrdentrabajo();
				tr = it.next();
				TbPaquete pq = new TbPaquete();
				pq = paqueteServive.obtenerPaquete(tr.getPaquetes_paquetes()); // obtener paquetes
																				// tr.getId().getTbPaquetesIdPaquetes()
				lst.add(pq);
			}
			return lst;
		} catch (Exception e) {
			return null;
		}
	}

	private List<TbTecnico> obtenerDetalleTecnicos(int idTrabajo) {
		List<TbTecnico> lst = new ArrayList<TbTecnico>();
		try {
			ordenService = abd.getOrdenTrabajoService();
			Iterator<TbOrdentrabajoHasTbTecnico> it = ordenService.getDetalleTecnicos(idTrabajo).iterator();
			while (it.hasNext()) {
				TbOrdentrabajoHasTbTecnico ot = new TbOrdentrabajoHasTbTecnico();
				ot = it.next();
				// TbTecnico tec = new TbTecnico();
				tecnico = tecnicoServive.getTecnico(ot.getTecnicos_tecnicos());
				lst.add(tecnico);
			}
			return lst;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	// Extra PrimeFaces listado
	private OrdenDataModel mediumOrdenesModel;

	public OrdenDataModel getMediumOrdenesModel() {
		return mediumOrdenesModel;
	}

	public void onRowSelect(SelectEvent event) {
		Mensajes.info("Orden de Trabajo", ((TbOrdentrabajo) event.getObject()).getComentarios());
	}

	public void onRowUnselect(UnselectEvent event) {
		Mensajes.info("Orden de Trabajo", ((TbOrdentrabajo) event.getObject()).getComentarios());
	}

}
