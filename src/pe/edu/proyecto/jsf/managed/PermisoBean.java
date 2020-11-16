package pe.edu.proyecto.jsf.managed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pe.edu.proyecto.business.delegate.ApplicationBusinessDelegate;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.primefaces.util.PermisoDataModel;

@ManagedBean(name="formPermiso")
@SessionScoped
public class PermisoBean {

	// Business Layer
	private static ApplicationBusinessDelegate abd = new ApplicationBusinessDelegate();
	private static UtilService utilService;
	
	private TbArea area = new TbArea();
	private TbAreaHasTbMenu areamenu = new TbAreaHasTbMenu();
	
	private List<TbArea> areaList;
	private List<TbAreaHasTbMenu> areamenuList;
	
	private boolean mostrar;

	public TbArea getArea() {
		return area;
	}
	public void setArea(TbArea area) {
		this.area = area;
	}
	public TbAreaHasTbMenu getAreamenu() {
		return areamenu;
	}
	public void setAreamenu(TbAreaHasTbMenu areamenu) {
		this.areamenu = areamenu;
	}
	
	public List<TbArea> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<TbArea> areaList) {
		this.areaList = areaList;
	}
	public List<TbAreaHasTbMenu> getAreamenuList() {
		return areamenuList;
	}
	public void setAreamenuList(List<TbAreaHasTbMenu> areamenuList) {
		this.areamenuList = areamenuList;
	}
	
	public boolean isMostrar() {
		return mostrar;
	}
	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	
	public String inicializar() throws Exception{
		System.out.println("Estoy dentro del método inicializar() INI ");
		
		utilService = abd.getUtilService();
		areaList = utilService.listarAreas();
		
		areamenuList = new ArrayList<TbAreaHasTbMenu>();
		
		mostrar = false;
		
		System.out.println("Estoy dentro del método inicializar() FIN ");
		return "permiso";
	}
	
	public String obtenerPermisos() throws Exception{
		System.out.println("Estoy dentro del método obtenerPermisos() - INI");
		
		if (area == null){
			utilService = abd.getUtilService();
			areaList = utilService.listarAreas();
		}
		
		if (areaList.size() == 0){
			utilService = abd.getUtilService();
			areaList = utilService.listarAreas();
		}
		
		
		utilService = abd.getUtilService();
		areamenuList = utilService.listarPermisosXArea(area.getIdArea());
		
		// Modificado PrimeFaces para listado
		mediumPermisosModel = new PermisoDataModel(areamenuList);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "Se obtuvieron " + areamenuList.size() + " resultados!"));
		
		mostrar = true;
		
		System.out.println("Estoy dentro del método obtenerPermisos() - FIN");
		return "permiso";
	}
	
	public String modificarPermisos() throws Exception{
		System.out.println("Estoy dentro del método modificarPermisos() - INI");
		
		List<TbAreaHasTbMenu> lista = new ArrayList<TbAreaHasTbMenu>();
	    lista = Arrays.asList(selectedPermisos);
	    
	    if (lista == null)
	    	lista = new ArrayList<TbAreaHasTbMenu>();
	    
	    if (lista.size() != 0){
	    	System.out.println("Resultados obtenidos " + lista.size());
	    	for (int i = 0; i < lista.size(); i++) {
				TbAreaHasTbMenu l = lista.get(i);
				System.out.println(l.getTbMenu().getTitulo());
			}
	    	
	    	Iterator<TbAreaHasTbMenu> it = lista.iterator();
	    	while(it.hasNext()){
	    		areamenu = (TbAreaHasTbMenu) it.next();
	    		utilService = abd.getUtilService();
	    		if (areamenu.getVer() == "S"){
	    			areamenu.setVer("N");
		    		utilService.modificarPermisos(areamenu);
	    		}else{
	    			areamenu.setVer("S");
		    		utilService.modificarPermisos(areamenu);
	    		}
	    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exitoso", "Permisos Actualizadados!"));
	    	}
	    }else{
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe Selecionar para modificar!"));
	    	return null;
	    }
	    	
		System.out.println("Estoy dentro del método modificarPermisos() - FIN");
		return "permiso";
	}
	
	
	// Extra PrimeFaces listado ordenes
	private PermisoDataModel mediumPermisosModel;
	private TbAreaHasTbMenu[] selectedPermisos;

	public TbAreaHasTbMenu[] getSelectedPermisos() {
		return selectedPermisos;
	}
	
	public void setSelectedPermisos(TbAreaHasTbMenu[] selectedPermisos) {
		this.selectedPermisos = selectedPermisos;
	}
	
    public PermisoDataModel getMediumPermisosModel() {
		return mediumPermisosModel;
	}
	public void setMediumPermisosModel(PermisoDataModel mediumPermisosModel) {
		this.mediumPermisosModel = mediumPermisosModel;
	}
	public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
}
