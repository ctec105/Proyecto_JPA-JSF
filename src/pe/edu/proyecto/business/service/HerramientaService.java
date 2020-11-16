package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;

public interface HerramientaService {
	
	// Métodos de apoyo utilizados en la interfaz PaqueteService
	public List<TbPaquetesherramienta> listarHerramientas()throws Exception;
	public TbPaquetesherramienta obtenerHerramienta(int id)throws Exception;
	
	public List<TbPaquetesherramienta> buscarHerramienta(TbPaquetesherramienta herramienta)throws Exception;

}
