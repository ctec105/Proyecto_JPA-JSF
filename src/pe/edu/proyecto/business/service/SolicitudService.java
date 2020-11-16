package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbSolicitud;

public interface SolicitudService {
	
	// Métodos empleados para el Caso de Uso Gestionar Solicitud de Servicio
	public List<TbSolicitud> listarSolicitudes() throws Exception;
	public TbSolicitud obtenerSolicitud(TbSolicitud solicitud) throws Exception;
	public void	registrarSolicitud(TbSolicitud solicitud) throws Exception;
	public void	actualizarSolicitud(TbSolicitud solicitud) throws Exception;
	public int eliminarSolicitud(TbSolicitud solicitud) throws Exception;
	public int generarIdSolicitud() throws Exception;
	
	// Método de apoyo invocados de la interface EquipoService
	// obtenerEquipo
	// listarEquipos
	// buscarEquipo
	// Métodos útiles invocados de la interfaces UtilService
	// listarClasesEquipos
	
	// Métodos que sirven de apoyo a la interfaz OrdenDeTrabajoService
	public TbSolicitud obtenerSolicitud(int nroSolicitud) throws Exception;
	// listarSolicitudes()
	public List<TbSolicitud> buscarSolicitud(String representante, String estado);
	
}
