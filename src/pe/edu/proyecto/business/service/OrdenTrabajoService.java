package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajoHasTbTecnico;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbTecnico;

public interface OrdenTrabajoService {
	
	// Métodos empleados para el Caso de Uso Gestionar Orden de Trabajo
	public List<TbOrdentrabajo> listarOrdenes() throws Exception;
	public TbOrdentrabajo obtenerOrden(TbOrdentrabajo orden) throws Exception;                 // Obtener orden
																							   // Obtener solicitud
	public List<TbPaquetesHasTbOrdentrabajo> getDetallePaquetes(int idTrabajo)throws Exception;// Obtener detalle paquetes
	public List<TbOrdentrabajoHasTbTecnico> getDetalleTecnicos(int idTrabajo)throws Exception; // Obtener detalle tecnicos
	public int registrarOrden(TbOrdentrabajo trabajo, List<TbPaquete> paquetes,List<TbTecnico> tecnicos) throws Exception;
	public int actualizarOrden(TbOrdentrabajo trabajo, List<TbPaquete> paquetes,List<TbTecnico> tecnicos) throws Exception;
	
	
	/* Método de apoyo invocados de la interface SolicitudService*/
	// obtenerSolicitud
	// listarSolicitudes
	// buscarSolicitud
	// Métodos de apoyo invocados de la interfaces UtilService
	// listarEstados()
	
	/* Método de apoyo invocados de la interface PaqueteService*/
	// obtenerPaquete
	// listarPaquetes
	// buscarPaquete
	// Métodos de apoyo invocados de la interfaces UtilService
	// listarMarcas
	// listarModelo
	// listarModelosxMarca
	// listarEspecialidades
	
	
	/* Método que sirven de apoyo a la interface liquidacionService*/
	public List<TbOrdentrabajo> obtenerOrdenesXCliente(TbCliente cliente)throws Exception;
	public void actualizar(TbOrdentrabajo orden) throws Exception;
	
	/* Método que sirve de apoyo en el PrefacturaBean*/
	public List<TbOrdentrabajo> obtenerOrdenXIdLiqui(int idLiquidacion);
	

	
//	public TbOrdentrabajo getTrabajo(int idOtrabajo)throws Exception;
	
//	public TbOrdentrabajo obtenerOrdenTrabajo(int id) throws Exception;
//	public List buscarOrdenTrabajo(String estado) throws Exception;
//	public int registrarOrdenTrabajo(TbOrdentrabajo trabajo, Hashtable<String, TbPaquete> paquetes,Hashtable<String, TbTecnico> tecnicos) throws Exception;
	
	
	
}
