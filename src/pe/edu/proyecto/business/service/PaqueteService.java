package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesmateriale;
import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;

public interface PaqueteService {
	
	// Método del Csos de Uso Gestionar Paquetes de Servicio
	public List<TbPaquete> listarPaquetes()throws Exception;// Métodos que sirven de apoyo a la interfaz OrdenDeTrabajoService
	public TbPaquete obtenerPaquete(int id) throws Exception;// Métodos que sirven de apoyo a la interfaz OrdenDeTrabajoService
	public List<TbPaquetesHasTbPaquetesherramienta> getDetalleHerramientas(int idPaquetes); // Obtener Detalle Paquete
	public List<TbPaquetesHasTbPaquetesmateriale> getDetalleMateriales(int idPaquetes);     // Obtener Detalle Paquete
	public List<TbPaquetesHasTbPaquetesactividade> getDetalleActividades(int idPaquetes);   // Obtener Detalle Paquete

	public int registrarPaquete(TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList);

	public int actualizarPaquete(TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList);

	public int eliminarPaquete(TbPaquete paquete) throws Exception;
	
	// Métodos que sirven de apoyo a la interfaz OrdenDeTrabajoService
	public List<TbPaquete> buscarPaquete(TbPaquete paquete);
	
}
