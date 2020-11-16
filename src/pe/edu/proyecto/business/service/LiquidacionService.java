package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;

public interface LiquidacionService {
	
	// Métodos del caso de uso Gestionar Liquidacion
	public List<TbOrdenliquidacion> listarLiquidaciones() throws Exception;
	public TbOrdenliquidacion obtenerLiquidacion(TbOrdenliquidacion liquidacion)throws Exception;
	public int registrarLiquidacion(TbOrdenliquidacion liquidacion)throws Exception;
	
	// Método utilizado en la interfaz PrefacturaService
	public int actualizarLiquidacion(TbOrdenliquidacion liquidacion);
	
}
