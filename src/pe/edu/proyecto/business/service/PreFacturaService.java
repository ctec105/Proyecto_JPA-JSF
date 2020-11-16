package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbDetalleprefactura;
import pe.edu.proyecto.persistence.entity.TbPrefactura;

public interface PreFacturaService {
	
	// Métodos del Caso de Uso Gestionar Prefactura
	public List<TbPrefactura> listadoPrefacturas()throws Exception;
	public TbPrefactura obtenerPrefactura(TbPrefactura prefactura)throws Exception;
	public List<TbDetalleprefactura> getDetFactPaq(TbPrefactura prefactura)throws Exception;
	public List<TbDetalleprefactura> getDetFactTec(TbPrefactura prefactura)throws Exception;
	public int registrarPrefactura(TbPrefactura prefactura,List<TbDetalleprefactura> detalleprefacturaList)throws Exception;

	/*Métodos utilizados de la interfaz LiquidacionService*/
	//actualizarLiquidacion()
	
}
