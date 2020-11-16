package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbSolicitud;

public class SolicitudJPA {

	public List<TbSolicitud> listarSolicitudes(EntityManager em) {
		String query = "SELECT o FROM TbSolicitud o ORDER BY o.idSolicitud";
		Query emquery = em.createQuery(query);
		List<TbSolicitud> listaEntidad = emquery.getResultList();
		return listaEntidad;
	}

	public TbSolicitud obtenerSolicitud(EntityManager em, TbSolicitud solicitud) {
		TbSolicitud entidad = (TbSolicitud) em.find(TbSolicitud.class, solicitud.getIdSolicitud());
		em.detach(entidad);
		return entidad;
	}

	public void registrarSolicitud(EntityManager em, TbSolicitud solicitud) {
		//System.out.println("Solicitud: " + solicitud.toString());
		em.getTransaction().begin();
		em.persist(solicitud);
		em.flush(); 
		em.getTransaction().commit();
	}

	public void actualizarSolicitud(EntityManager em, TbSolicitud solicitud) {
		em.getTransaction().begin();
		em.merge(solicitud);
		em.flush();
		em.getTransaction().commit();
	}

	public int eliminarSolicitud(EntityManager em, TbSolicitud solicitud) {
		try {
			em.getTransaction().begin();
			TbSolicitud entidad = (TbSolicitud) em.find(TbSolicitud.class, solicitud.getIdSolicitud());
			em.remove(entidad);
			em.flush();
			em.getTransaction().commit();
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
	
	public int generarIdSolicitud(EntityManager em) {
		String query = "select max(s.idSolicitud) + 1 from TbSolicitud s";
		Query emquery = em.createQuery(query);
		int resultado = (Integer)emquery.getSingleResult();
		System.out.println("-->id:" + resultado);
		return resultado;
	}
	
	
	public TbSolicitud obtenerSolicitud(EntityManager em, int nroSolicitud) {
		try {
			TbSolicitud entidad = (TbSolicitud) em.find(TbSolicitud.class, nroSolicitud);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	
	// listarSolicitudes()
	
	public List<TbSolicitud> buscarSolicitud(EntityManager em,String representante, String estado) {
		try {
			String query = "select s from TbSolicitud s WHERE s.estado = ?1 AND s.representante like ?2";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, estado);
			emquery.setParameter(2, "%" + representante + "%");
			return emquery.getResultList();
		} catch (Exception e) {
			return null;
		}

	}
	
}
