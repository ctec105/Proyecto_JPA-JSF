package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;
import pe.edu.proyecto.persistence.jpa.HerramientaJPA;

public class HerramientaServiceImpl implements HerramientaService {

	HerramientaJPA herramientaJPA = new HerramientaJPA();
	private EntityManager em;
	
	public HerramientaServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}

	@Override
	public List<TbPaquetesherramienta> listarHerramientas() throws Exception {
		List<TbPaquetesherramienta> listaEntidad = herramientaJPA.listarHerramientas(em);
		return listaEntidad;
	}
	
	@Override
	public TbPaquetesherramienta obtenerHerramienta(int id) throws Exception {
		TbPaquetesherramienta entidad = herramientaJPA.obtenerHerramienta(em,id);
		return entidad;
	}

	@Override
	public List<TbPaquetesherramienta> buscarHerramienta(TbPaquetesherramienta herramienta) throws Exception {
		List<TbPaquetesherramienta> listaEntidad = herramientaJPA.buscarHerramienta(em,herramienta);
		return listaEntidad;
	}

}
