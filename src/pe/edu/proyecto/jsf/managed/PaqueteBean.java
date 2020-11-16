package pe.edu.proyecto.jsf.managed;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.ActividadService;
import pe.edu.proyecto.business.service.HerramientaService;
import pe.edu.proyecto.business.service.MaterialService;
import pe.edu.proyecto.business.service.PaqueteService;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbMarca;
import pe.edu.proyecto.persistence.entity.TbModelo;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesmateriale;
import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;
import pe.edu.proyecto.primefaces.util.PaqueteDataModel;
import pe.edu.proyecto.util.Mensajes;

@ManagedBean(name = "formPaquete")
@SessionScoped
public class PaqueteBean {

	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static PaqueteService paqueteService;
	private static UtilService utilService;
	private static HerramientaService herramientaService;
	private static MaterialService materialService;
	private static ActividadService actividadService;

	// Instances
	private TbPaquete paquete = new TbPaquete();
	private TbPaquetesherramienta herramienta = new TbPaquetesherramienta();
	private TbPaquetesmateriale material = new TbPaquetesmateriale();
	private TbPaquetesactividade actividad = new TbPaquetesactividade();

	// Lists
	private List<TbPaquete> paqueteList;
	private List<TbMarca> marcaList;
	private List<TbModelo> modeloList;
	private List<TbPaquetesherramienta> herramientaList;
	private List<TbPaquetesmateriale> materialList;
	private List<TbPaquetesactividade> actividadList;

	// Modals
	private List<TbPaquetesherramienta> modalHerramientaList;
	private List<TbPaquetesmateriale> modalMaterialList;
	private List<TbPaquetesactividade> modalActividadList;

	// Extra PrimeFaces listing
	private PaqueteDataModel mediumPaquetesModel;

	// Attributes
	private String buscherra;
	private String buscmate;
	private String buscacti;

	private String nomHerra;
	private String nomMate;
	private String nomAct;

	// Getters and setters
	public TbPaquete getPaquete() {
		return paquete;
	}

	public void setPaquete(TbPaquete paquete) {
		this.paquete = paquete;
	}

	public TbPaquetesherramienta getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(TbPaquetesherramienta herramienta) {
		this.herramienta = herramienta;
	}

	public TbPaquetesmateriale getMaterial() {
		return material;
	}

	public void setMaterial(TbPaquetesmateriale material) {
		this.material = material;
	}

	public TbPaquetesactividade getActividad() {
		return actividad;
	}

	public void setActividad(TbPaquetesactividade actividad) {
		this.actividad = actividad;
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

	public List<TbPaquetesherramienta> getHerramientaList() {
		return herramientaList;
	}

	public void setHerramientaList(List<TbPaquetesherramienta> herramientaList) {
		this.herramientaList = herramientaList;
	}

	public List<TbPaquetesmateriale> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<TbPaquetesmateriale> materialList) {
		this.materialList = materialList;
	}

	public List<TbPaquetesactividade> getActividadList() {
		return actividadList;
	}

	public void setActividadList(List<TbPaquetesactividade> actividadList) {
		this.actividadList = actividadList;
	}

	public List<TbPaquetesherramienta> getModalHerramientaList() {
		return modalHerramientaList;
	}

	public void setModalHerramientaList(List<TbPaquetesherramienta> modalHerramientaList) {
		this.modalHerramientaList = modalHerramientaList;
	}

	public List<TbPaquetesmateriale> getModalMaterialList() {
		return modalMaterialList;
	}

	public void setModalMaterialList(List<TbPaquetesmateriale> modalMaterialList) {
		this.modalMaterialList = modalMaterialList;
	}

	public List<TbPaquetesactividade> getModalActividadList() {
		return modalActividadList;
	}

	public void setModalActividadList(List<TbPaquetesactividade> modalActividadList) {
		this.modalActividadList = modalActividadList;
	}

	public PaqueteDataModel getMediumPaquetesModel() {
		return mediumPaquetesModel;
	}

	public String getBuscherra() {
		return buscherra;
	}

	public void setBuscherra(String buscherra) {
		this.buscherra = buscherra;
	}

	public String getBuscmate() {
		return buscmate;
	}

	public void setBuscmate(String buscmate) {
		this.buscmate = buscmate;
	}

	public String getBuscacti() {
		return buscacti;
	}

	public void setBuscacti(String buscacti) {
		this.buscacti = buscacti;
	}

	public String getNomHerra() {
		return nomHerra;
	}

	public void setNomHerra(String nomHerra) {
		this.nomHerra = nomHerra;
	}

	public String getNomMate() {
		return nomMate;
	}

	public void setNomMate(String nomMate) {
		this.nomMate = nomMate;
	}

	public String getNomAct() {
		return nomAct;
	}

	public void setNomAct(String nomAct) {
		this.nomAct = nomAct;
	}

	// Actions method
	public String listarPaquetes() throws Exception {
		System.out.println("Estoy dentro del método listarPaquetes() - INI");

		paqueteService = abd.getPaqueteService();
		paqueteList = paqueteService.listarPaquetes();

		// Extra PrimeFaces listado
		mediumPaquetesModel = new PaqueteDataModel(paqueteList);

		System.out.println("Estoy dentro del método listarPaquetes() - FIN");
		return "paquete";
	}

	public String edit() throws NumberFormatException, Exception {
		System.out.println("Estoy dentro del método edit() - INI");

		buscherra = "";
		buscmate = "";
		buscacti = "";
		nomHerra = "";
		nomMate = "";
		nomAct = "";
		
		utilService = abd.getUtilService();
		marcaList = utilService.listarMarcas();
		// modeloList = utilService.listarModelos();
		modeloList = utilService.listarModelosxMarca("1");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");

		if (codigo != null && codigo.length() > 0) {
			System.out.println("Entramos a editar");

			System.out.println("ObtenerPaquete");
			paqueteService = abd.getPaqueteService();
			paquete = paqueteService.obtenerPaquete(Integer.parseInt(codigo));

			System.out.println("Listado de herramientas del paquete:" + codigo);
			herramientaService = abd.getHerramientaService();
			herramientaList = obtenerDetalleHerramienta(paquete.getIdPaquetes());

			System.out.println("Listado de materiales del paquete:" + codigo);
			materialService = abd.getMaterialService();
			materialList = obtenerDetalleMaterial(paquete.getIdPaquetes());

			System.out.println("Listado de Actividades del paquete:" + codigo);
			actividadService = abd.getActividadService();
			actividadList = obtenerDetalleActividad(paquete.getIdPaquetes());
		} else {
			System.out.println("Entramos a registrar");

			paquete = new TbPaquete();
			herramientaList = new ArrayList<TbPaquetesherramienta>();
			materialList = new ArrayList<TbPaquetesmateriale>();
			actividadList = new ArrayList<TbPaquetesactividade>();
		}

		System.out.println("Estoy dentro del método edit() - FIN");
		return "paqueteForm";
	}

	public String grabar() throws Exception {
		System.out.println("Estoy dentro del método registrarOrActualizar() - INI");

		if (herramientaList == null)
			herramientaList = new ArrayList<TbPaquetesherramienta>();

		if (materialList == null)
			materialList = new ArrayList<TbPaquetesmateriale>();

		if (actividadList == null)
			actividadList = new ArrayList<TbPaquetesactividade>();

		if (paquete.getNombre().length() == 0) {
			Mensajes.info("¡Debe ingresar el nombre!", null);
			return null;
		}

		if (paquete.getDuracion() == 0) {
			Mensajes.info("¡Debe ingresar la duración!", null);
			return null;
		}

		/*if (herramientaList.size() == 0) {
			Mensajes.info("¡Debe ingresar una herramienta!", null);
			return null;
		}

		if (materialList.size() == 0) {
			Mensajes.info("¡Debe ingresar un material!", null);
			return null;
		}

		if (actividadList.size() == 0) {
			Mensajes.info("¡Debe ingresar una actividad!", null);
			return null;
		}*/

		paqueteService = abd.getPaqueteService();
		if (paquete.getIdPaquetes() == 0) {
			if (paqueteService.registrarPaquete(paquete, herramientaList, materialList, actividadList) == 1) {
				Mensajes.error("Error", "¡El Paquete de Servicio no se pudo registrar!");
				//return null;
			} else
				Mensajes.info("Exitoso", "¡Paquete de Servicio registrado!");
		} else {
			if (paqueteService.actualizarPaquete(paquete, herramientaList, materialList, actividadList) == 1) {
				Mensajes.error("Error", "¡El Paquete de Servicio no se pudo actualizar!");
				//return null;
			} else
				Mensajes.info("Exitoso", "¡Paquete de Servicio actualizado!");
		}

		System.out.println("Estoy dentro del método registrarOrActualizar() - FIN");
		return listarPaquetes();
	}

	public String eliminar() throws Exception {
		System.out.println("Estoy dentro del método eliminar() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		paqueteService = abd.getPaqueteService();
		paquete = new TbPaquete();
		paquete.setIdPaquetes(Integer.parseInt(codigo));
		if (paqueteService.eliminarPaquete(paquete) == 1) {
			Mensajes.error("Error", "¡El Paquete no ha sido eliminado!");
			return null;
		}

		Mensajes.info("Exitoso", "¡Paquete eliminado!");

		System.out.println("Estoy dentro del método eliminar() - FIN");
		return listarPaquetes();
	}

	public String obtenerModelosxMarca() throws Exception {
		System.out.println("Estoy dentro del método obtenerModelos() - INI");

		utilService = abd.getUtilService();
		if (paquete.getMarca() != null && !paquete.getMarca().equals(""))
			modeloList = utilService.listarModelosxMarca(paquete.getMarca());

		System.out.println("Estoy dentro del método obtenerModelos() - FIN");
		return "paqueteForm";
	}

	public String obtenerHerramienta() throws Exception {
		System.out.println("Estoy dentro del método obtenerHerramienta() - INI");

		if (buscherra.length() <= 0) {
			Mensajes.info("¡Ingrese una herramienta!", null);
			return null;
		}

		herramientaService = abd.getHerramientaService();
		herramienta = herramientaService.obtenerHerramienta(Integer.parseInt(buscherra));

		if (herramienta != null) {
			herramientaList.add(herramienta);
			calcularPrecio();
			Mensajes.info("¡Se agregó la herramienta <font color=\"blue\">" + buscherra + "</font>!", null);
		} else {
			Mensajes.info("¡La herramienta ingresada, <font color=\"red\">" + buscherra + "</font> no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerHerramienta() - FIN");
		return "paqueteForm";
	}

	public String buscarHerramienta() throws Exception {
		System.out.println("Estoy dentro del método buscarHerramienta() - INI");

		herramientaService = abd.getHerramientaService();
		herramienta = new TbPaquetesherramienta();
		herramienta.setDescripcion(nomHerra);
		modalHerramientaList = herramientaService.buscarHerramienta(herramienta);

		System.out.println("Estoy dentro del método buscarHerramienta() - FIN");
		return "paqueteform";
	}

	public String recuperarHerramienta() throws Exception {
		System.out.println("Estoy dentro del método recuperarHerramienta() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBuscherra(codigo);

		System.out.println("Estoy dentro del método recuperarHerramienta() - FIN");
		return "paqueteForm";
	}

	public String obtenerMaterial() throws Exception {
		System.out.println("Estoy dentro del método obtenerMaterial() - INI");

		if (buscmate.length() <= 0) {
			Mensajes.info("¡Ingrese un material!", null);
			return null;
		}

		materialService = abd.getMaterialService();
		material = materialService.obtenerMaterial(Integer.parseInt(buscmate));

		if (material != null) {
			materialList.add(material);
			calcularPrecio();
			Mensajes.info("¡Se agregó el material <font color=\"blue\">" + buscmate + "</font>!", null);
		} else {
			Mensajes.info("¡El material ingresado, <font color=\"red\">" + buscmate + "</font> no existe!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerMaterial() - FIN");
		return "paqueteForm";
	}

	public String buscarMaterial() throws Exception {
		System.out.println("Estoy dentro del método buscarMaterial() - INI");

		materialService = abd.getMaterialService();
		material = new TbPaquetesmateriale();
		material.setDescripcion(nomMate);
		modalMaterialList = materialService.buscarMaterial(material);

		System.out.println("Estoy dentro del método buscarMaterial() - FIN");
		return "paqueteform";
	}

	public String recuperarMaterial() throws Exception {
		System.out.println("Estoy dentro del método recuperarMaterial() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBuscmate(codigo);

		System.out.println("Estoy dentro del método recuperarMaterial() - FIN");
		return "paqueteForm";
	}

	public String obtenerActividad() throws Exception {
		System.out.println("Estoy dentro del método obtenerActividad() - INI");

		if (buscacti.length() <= 0) {
			Mensajes.info("¡Ingrese una Actividad!", null);
			return null;
		}

		actividadService = abd.getActividadService();
		actividad = actividadService.obtenerActividad(Integer.parseInt(buscacti));

		if (actividad != null) {
			actividadList.add(actividad);
			calcularPrecio();
			Mensajes.info("¡Se agregó la Actividad <font color=\"blue\">" + buscacti + "</font>!", null);
		} else {
			Mensajes.info("¡La Actividad ingresada, <font color=\"red\">" + buscacti + "</font>!", null);
			return null;
		}

		System.out.println("Estoy dentro del método obtenerActividad() - FIN");
		return "paqueteForm";
	}

	public String buscarActividad() throws Exception {
		System.out.println("Estoy dentro del método buscarActividad() - INI");

		actividadService = abd.getActividadService();
		actividad = new TbPaquetesactividade();
		actividad.setDescripcion(nomAct);
		modalActividadList = actividadService.buscarActividad(actividad);

		System.out.println("Estoy dentro del método buscarActividad() - FIN");
		return "paqueteForm";
	}

	public String recuperarActividad() throws Exception{
		System.out.println("Estoy dentro del método recuperarActividad() - INI");

		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		setBuscacti(codigo);

		System.out.println("Estoy dentro del método recuperarActividad() - FIN");
		return "paqueteForm";
	}

	public String calcularPrecio() {
		Double monto = 0.00;
		List<TbPaquetesherramienta> lst1 = new ArrayList<TbPaquetesherramienta>();
		List<TbPaquetesmateriale> lst2 = new ArrayList<TbPaquetesmateriale>();
		List<TbPaquetesactividade> lst3 = new ArrayList<TbPaquetesactividade>();
		try {
			System.out.println("Entro a Calcular Precio ::: ");

			if (herramientaList != null)
				lst1 = herramientaList;
			if (materialList != null)
				lst2 = materialList;
			if (actividadList != null)
				lst3 = actividadList;

			monto = monto + calcula1(lst1);
			monto = monto + calcula2(lst2);
			monto = monto + calcula3(lst3);

			paquete.setPrecio(monto);
			System.out.println("Monto: " + monto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "paqueteForm";
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Paquete ", ((TbPaquete) event.getObject()).getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Paquete ", ((TbPaquete) event.getObject()).getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Methods
	private List<TbPaquetesherramienta> obtenerDetalleHerramienta(int idPaquetes) {
		List<TbPaquetesherramienta> lst = new ArrayList<TbPaquetesherramienta>();
		try {
			Iterator<TbPaquetesHasTbPaquetesherramienta> it = paqueteService.getDetalleHerramientas(idPaquetes)
					.iterator();
			while (it.hasNext()) {
				TbPaquetesHasTbPaquetesherramienta ot = new TbPaquetesHasTbPaquetesherramienta();
				ot = it.next();
				herramienta = herramientaService.obtenerHerramienta(ot.getPaquetesHerramientas_herramientas());
				lst.add(herramienta);
			}
			return lst;
		} catch (Exception e) {
			return null;
		}
	}

	private List<TbPaquetesmateriale> obtenerDetalleMaterial(int idPaquetes) {
		List<TbPaquetesmateriale> lst = new ArrayList<TbPaquetesmateriale>();
		try {
			Iterator<TbPaquetesHasTbPaquetesmateriale> it = paqueteService.getDetalleMateriales(idPaquetes).iterator();
			while (it.hasNext()) {
				TbPaquetesHasTbPaquetesmateriale ot = new TbPaquetesHasTbPaquetesmateriale();
				ot = it.next();
				material = materialService.obtenerMaterial(ot.getPaquetesMateriales_materiales());
				lst.add(material);
			}
			return lst;
		} catch (Exception e) {
			return null;
		}
	}

	private List<TbPaquetesactividade> obtenerDetalleActividad(int idPaquetes) {
		List<TbPaquetesactividade> lst = new ArrayList<TbPaquetesactividade>();
		try {
			Iterator<TbPaquetesHasTbPaquetesactividade> it = paqueteService.getDetalleActividades(idPaquetes)
					.iterator();
			while (it.hasNext()) {
				TbPaquetesHasTbPaquetesactividade ot = new TbPaquetesHasTbPaquetesactividade();
				ot = it.next();
				actividad = actividadService.obtenerActividad(ot.getPaquetesActividades_actividades());
				lst.add(actividad);
			}
			return lst;
		} catch (Exception e) {
			return null;
		}
	}

	private Double calcula1(List<TbPaquetesherramienta> lst) {
		double rst = 0.00;
		Iterator it = lst.iterator();
		while (it.hasNext()) {
			TbPaquetesherramienta pq = (TbPaquetesherramienta) it.next();
			rst = rst + pq.getPrecio(); // * paquete.getDuracion());
		}
		return rst;
	}

	private Double calcula2(List<TbPaquetesmateriale> lst) {
		double rst = 0.00;
		Iterator it = lst.iterator();
		while (it.hasNext()) {
			TbPaquetesmateriale pq = (TbPaquetesmateriale) it.next();
			rst = rst + pq.getPrecio(); // * paquete.getDuracion());
		}
		return rst;
	}

	private Double calcula3(List<TbPaquetesactividade> lst) {
		double rst = 0.00;
		Iterator it = lst.iterator();
		while (it.hasNext()) {
			TbPaquetesactividade pq = (TbPaquetesactividade) it.next();
			rst = rst + pq.getPrecio(); // * paquete.getDuracion());
		}
		return rst;
	}

}