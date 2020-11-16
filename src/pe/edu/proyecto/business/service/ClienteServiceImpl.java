package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.jpa.ClienteJPA;

public class ClienteServiceImpl implements ClienteService {

	ClienteJPA clienteJPA = new ClienteJPA();
	private EntityManager em;
	
	public ClienteServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public List<TbCliente> listadoCliente() throws Exception {
		List<TbCliente> lista = clienteJPA.listadoCliente(em);
		return lista;
	}

	@Override
	public TbCliente obtenerCliente(TbCliente cliente) throws Exception {
		TbCliente entidad = clienteJPA.obtenerCliente(em,cliente);
		return entidad;
	}
	
	@Override
	public void registrarCliente(TbCliente cliente) throws Exception {
		clienteJPA.registrarCliente(em,cliente);
	}

	@Override
	public void actualizarCliente(TbCliente cliente) throws Exception {
		clienteJPA.actualizarCliente(em,cliente);
	}

	@Override
	public void eliminarCliente(TbCliente cliente) throws Exception {
		clienteJPA.eliminarCliente(em,cliente);
	}

	@Override
	public List<TbCliente> buscarClienre(TbCliente cliente) throws Exception {
		List<TbCliente> lista = clienteJPA.buscarClienre(em,cliente);
		return lista;
	}
	
//	@Override
//	public TbCliente getCliente(String idCliente) throws Exception {
//		return clienteJPA.getCliente(em,idCliente);
//	}

}
