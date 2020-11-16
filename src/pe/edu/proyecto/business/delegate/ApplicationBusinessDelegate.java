package pe.edu.proyecto.business.delegate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pe.edu.proyecto.business.service.ActividadService;
import pe.edu.proyecto.business.service.ActividadServiceImpl;
import pe.edu.proyecto.business.service.ClienteService;
import pe.edu.proyecto.business.service.ClienteServiceImpl;
import pe.edu.proyecto.business.service.EquipoService;
import pe.edu.proyecto.business.service.EquipoServiceImpl;
import pe.edu.proyecto.business.service.HerramientaService;
import pe.edu.proyecto.business.service.HerramientaServiceImpl;
import pe.edu.proyecto.business.service.LiquidacionService;
import pe.edu.proyecto.business.service.LiquidacionServiceImpl;
import pe.edu.proyecto.business.service.LoginService;
import pe.edu.proyecto.business.service.LoginServiceImpl;
import pe.edu.proyecto.business.service.MaterialService;
import pe.edu.proyecto.business.service.MaterialServiceImpl;
import pe.edu.proyecto.business.service.OrdenTrabajoService;
import pe.edu.proyecto.business.service.OrdenTrabajoServiceImpl;
import pe.edu.proyecto.business.service.PaqueteService;
import pe.edu.proyecto.business.service.PaqueteServiceImpl;
import pe.edu.proyecto.business.service.PreFacturaService;
import pe.edu.proyecto.business.service.PreFacturaServiceImpl;
import pe.edu.proyecto.business.service.SolicitudService;
import pe.edu.proyecto.business.service.SolicitudServiceImpl;
import pe.edu.proyecto.business.service.TecnicoService;
import pe.edu.proyecto.business.service.TecnicoServiceImpl;
import pe.edu.proyecto.business.service.UsuarioService;
import pe.edu.proyecto.business.service.UsuarioServiceImpl;
import pe.edu.proyecto.business.service.UtilService;
import pe.edu.proyecto.business.service.UtilServiceImpl;


public class ApplicationBusinessDelegate {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MI_UNIDAD_DE_PERSISTENCIA");

	
	// Negocio
	public LoginService getLoginService() {
		return new LoginServiceImpl(emf);
	}
	
	public SolicitudService getSolicitudService() {
		return new SolicitudServiceImpl(emf);
	}
	
	public OrdenTrabajoService getOrdenTrabajoService() {
		return new OrdenTrabajoServiceImpl(emf);
	}
	
	public LiquidacionService getLiquidacionService() {
		return new LiquidacionServiceImpl(emf);
	}
	
	public PreFacturaService getPreFacturaService() {
		return new PreFacturaServiceImpl(emf);
	}
	
	
	// Tablas
	public UsuarioService getUsuarioService(){
		return new UsuarioServiceImpl(emf);
	}
	
	public TecnicoService getTecnicoService() {
		return new TecnicoServiceImpl(emf);
	}
	
	public ClienteService getClienteService() {
		return new ClienteServiceImpl(emf);
	}
	
	public EquipoService getEquipoService() {
		return new EquipoServiceImpl(emf);
	}
	
	public PaqueteService getPaqueteService() {
		return new PaqueteServiceImpl(emf);
	}
	
	public HerramientaService getHerramientaService(){
		return new HerramientaServiceImpl(emf);
	}
	
	public MaterialService getMaterialService(){
		return new MaterialServiceImpl(emf);
	}
	
	public ActividadService getActividadService(){
		return new ActividadServiceImpl(emf);
	}
	
	
	public UtilService getUtilService(){
		return new UtilServiceImpl(emf);
	}
	
}
