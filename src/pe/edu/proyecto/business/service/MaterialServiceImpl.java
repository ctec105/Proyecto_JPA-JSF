package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;
import pe.edu.proyecto.persistence.jpa.MaterialJPA;

public class MaterialServiceImpl implements MaterialService {

	MaterialJPA materialJPA = new MaterialJPA();
	private EntityManager em;
	
	public MaterialServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public List<TbPaquetesmateriale> listarMateriales() throws Exception {
		List<TbPaquetesmateriale> listaEntidad = materialJPA.listarMateriales(em);
		return listaEntidad;
	}

	@Override
	public TbPaquetesmateriale obtenerMaterial(int id) throws Exception {
		TbPaquetesmateriale entidad = materialJPA.obtenerMaterial(em,id);
		return entidad;
	}

	@Override
	public List<TbPaquetesmateriale> buscarMaterial(TbPaquetesmateriale material)throws Exception {
		 List<TbPaquetesmateriale> listaEntidad = materialJPA.buscarMaterial(em,material);
		 return listaEntidad;
	}

}
