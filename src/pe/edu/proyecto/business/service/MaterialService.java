package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;

public interface MaterialService {

	// Métodos de apoyo utilizados en la interfaz PaqueteService
	public List<TbPaquetesmateriale> listarMateriales()throws Exception;
	public TbPaquetesmateriale obtenerMaterial(int id)throws Exception;
	public List<TbPaquetesmateriale> buscarMaterial(TbPaquetesmateriale material)throws Exception;
		
}
