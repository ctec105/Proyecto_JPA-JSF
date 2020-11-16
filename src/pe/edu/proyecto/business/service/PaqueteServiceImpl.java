package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesmateriale;
import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;
import pe.edu.proyecto.persistence.jpa.PaqueteJPA;

public class PaqueteServiceImpl implements PaqueteService {

	PaqueteJPA paqueteJPA = new PaqueteJPA();
	private EntityManager em;
	
	public PaqueteServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
	
	@Override
	public List<TbPaquete> listarPaquetes() throws Exception {
		List<TbPaquete> listaEntidad = paqueteJPA.listarPaquetes(em);
		return listaEntidad;
	}

	@Override
	public TbPaquete obtenerPaquete(int id) throws Exception {
		TbPaquete entidad = paqueteJPA.obtenerPaquete(em,id);
		return entidad;
	}

	@Override
	public List<TbPaquetesHasTbPaquetesherramienta> getDetalleHerramientas(int idPaquetes) {
		List<TbPaquetesHasTbPaquetesherramienta> listaEntidad = paqueteJPA.getDetalleHerramientas(em,idPaquetes);
		return listaEntidad;
	}
	
	@Override
	public List<TbPaquetesHasTbPaquetesmateriale> getDetalleMateriales(int idPaquetes) {
		List<TbPaquetesHasTbPaquetesmateriale> listaEntidad = paqueteJPA.getDetalleMateriales(em,idPaquetes);
		return listaEntidad;
	}

	@Override
	public List<TbPaquetesHasTbPaquetesactividade> getDetalleActividades(int idPaquetes) {
		List<TbPaquetesHasTbPaquetesactividade> listaEntidad = paqueteJPA.getDetalleActividades(em,idPaquetes);
		return listaEntidad;
	}
	
	@Override
	public int registrarPaquete(TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList) {
		return paqueteJPA.registrarPaquete(em, paquete, herramientaList, materialList, actividadList);
	}

	@Override
	public int actualizarPaquete(TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList) {
		return paqueteJPA.actualizarPaquete(em, paquete, herramientaList, materialList, actividadList);
	}
	
	@Override
	public int eliminarPaquete(TbPaquete paquete) {
		return paqueteJPA.eliminarPaquete(em, paquete);
	}

	@Override
	public List<TbPaquete> buscarPaquete(TbPaquete paquete) {
		List<TbPaquete> listaEntidad = paqueteJPA.buscarPaquete(em,paquete);
		return listaEntidad;
	}

}
