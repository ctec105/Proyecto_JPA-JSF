package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbTecnico;

public interface TecnicoService {
	
	public TbTecnico getTecnico(int id) throws Exception;
	public List<TbTecnico> listarTecnicos() throws Exception;
	public List<TbTecnico> popBuscarTecn(TbTecnico tecnico) throws Exception;
	
	
	// por las guevas
//	public List buscarTecnico(String nombre) throws Exception;
	
	
}
