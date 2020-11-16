package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;
import pe.edu.proyecto.persistence.jpa.ActividadJPA;

public class ActividadServiceImpl implements ActividadService {

	ActividadJPA actividadJPA = new ActividadJPA();
	private EntityManager em;
	
	public ActividadServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public List<TbPaquetesactividade> listarActividades() throws Exception {
		List<TbPaquetesactividade> listaEntidad = actividadJPA.listarActividades(em);
		return listaEntidad;
	}

	@Override
	public TbPaquetesactividade obtenerActividad(int id) throws Exception {
		TbPaquetesactividade entidad = actividadJPA.obtenerActividad(em,id);
		return entidad;
	}

	@Override
	public List<TbPaquetesactividade> buscarActividad(TbPaquetesactividade actividad) throws Exception {
		List<TbPaquetesactividade> listaEntidad = actividadJPA.buscarActividad(em,actividad);
		return listaEntidad;
	}

}
