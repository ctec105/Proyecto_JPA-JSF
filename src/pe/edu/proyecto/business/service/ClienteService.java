package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbCliente;

public interface ClienteService {

	// Métodos del Caso de Uso Gestionar Clientes
	public List<TbCliente> listadoCliente()throws Exception;// Metodo usado en el managedbean liquidacionbean
	public TbCliente obtenerCliente(TbCliente cliente)throws Exception;
	public void registrarCliente(TbCliente cliente)throws Exception;
	public void actualizarCliente(TbCliente cliente)throws Exception;
	public void eliminarCliente(TbCliente cliente)throws Exception;
	// Metodo usado en el managedbean liquidacionbean
	public List<TbCliente> buscarClienre(TbCliente cliente)throws Exception;

}
