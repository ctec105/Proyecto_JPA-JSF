package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbEquipo;

public interface EquipoService {

	public TbEquipo obtenerEquipo(String nroTarjeta) throws Exception;
	public List<TbEquipo> listarEquipos() throws Exception;
	public List<TbEquipo> buscarEquipo(String nroTarjeta, int clase)throws Exception;
	
}
