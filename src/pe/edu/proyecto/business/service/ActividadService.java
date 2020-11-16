package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;

public interface ActividadService {
	
	// Métodos de apoyo utilizados en la interfaz PaqueteService
	public List<TbPaquetesactividade> listarActividades()throws Exception;
	public TbPaquetesactividade obtenerActividad(int id)throws Exception;
	public List<TbPaquetesactividade> buscarActividad(TbPaquetesactividade actividad)throws Exception;

}
