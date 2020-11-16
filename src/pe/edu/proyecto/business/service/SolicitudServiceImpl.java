package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbSolicitud;
import pe.edu.proyecto.persistence.jpa.SolicitudJPA;


public class SolicitudServiceImpl implements SolicitudService {

	SolicitudJPA solicitudJPA = new SolicitudJPA();
	private EntityManager em;
	
	public SolicitudServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}

	@Override
	public List<TbSolicitud> listarSolicitudes() throws Exception {
		List<TbSolicitud> lista = solicitudJPA.listarSolicitudes(em);
		return lista;
	}

	@Override
	public TbSolicitud obtenerSolicitud(TbSolicitud solicitud) throws Exception {
		TbSolicitud entidad = solicitudJPA.obtenerSolicitud(em, solicitud);
		return entidad;
	}

	@Override
	public void registrarSolicitud(TbSolicitud solicitud) throws Exception {
		solicitudJPA.registrarSolicitud(em, solicitud);		
	}

	@Override
	public void actualizarSolicitud(TbSolicitud solicitud) throws Exception {
		solicitudJPA.actualizarSolicitud(em, solicitud);	
		
	}

	@Override
	public int eliminarSolicitud(TbSolicitud solicitud) throws Exception {
		return solicitudJPA.eliminarSolicitud(em, solicitud);	
	}
	
	@Override
	public int generarIdSolicitud() throws Exception {
		int resultado = solicitudJPA.generarIdSolicitud(em);
		return resultado;
	}
	
	
	@Override
	public TbSolicitud obtenerSolicitud(int nroSolicitud) throws Exception {
		TbSolicitud entidad = solicitudJPA.obtenerSolicitud(em,nroSolicitud);
		return entidad;
	}

	// listarSolicitudes()
	
	@Override
	public List<TbSolicitud> buscarSolicitud(String representante, String estado) {
		List<TbSolicitud> listaEntidad = solicitudJPA.buscarSolicitud(em,representante,estado);
		return listaEntidad;
	}

}
