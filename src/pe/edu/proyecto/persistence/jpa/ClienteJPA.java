package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbCliente;

public class ClienteJPA {

	public List<TbCliente> listadoCliente(EntityManager em) {
		String query = "select c from TbCliente c order by c.idCliente";
		Query emquery = em.createQuery(query);
		List<TbCliente> lista = emquery.getResultList();
		return lista;
	}

	public TbCliente obtenerCliente(EntityManager em, TbCliente cliente) {
		TbCliente entidad = (TbCliente) em.find(TbCliente.class, cliente.getIdCliente());
		em.detach(entidad);
		return entidad;
	}
	
	public void registrarCliente(EntityManager em, TbCliente cliente) {
		em.getTransaction().begin();
		em.persist(cliente);
		em.flush();
		em.getTransaction().commit();
	}

	public void actualizarCliente(EntityManager em, TbCliente cliente) {
		em.getTransaction().begin();
		em.merge(cliente);
		em.flush();
		em.getTransaction().commit();
	}

	public void eliminarCliente(EntityManager em, TbCliente cliente) {
		em.getTransaction().begin();
		TbCliente entidad = (TbCliente)em.find(TbCliente.class, cliente.getIdCliente());
		em.remove(entidad);
		em.flush();
		em.getTransaction().commit();
	}

	public List<TbCliente> buscarClienre(EntityManager em, TbCliente cliente) {
		String query = "select c from TbCliente c where c.idCliente <> '9999999999' and c.idCliente like ?1 and c.razonsocial like ?2 order by c.idCliente";
		Query emquery = em.createQuery(query);
		emquery.setParameter("1", "%" + cliente.getIdCliente() + "%");
		emquery.setParameter("2", "%" + cliente.getRazonsocial() + "%");
		List<TbCliente> lista = emquery.getResultList();
		return lista;
	}

//	public TbCliente getCliente(EntityManager em, String idCliente) {
//		try {
//			TbCliente entidad = (TbCliente) em.find(TbCliente.class, idCliente);
//			em.detach(entidad);
//			return entidad;
//		} catch (Exception e) {
//			return null;
//		}
//	}

}
