package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;

public class MaterialJPA {

	public List<TbPaquetesmateriale> listarMateriales(EntityManager em) {
		String query = "select m from TbPaquetesmateriale m order by m.idMateriales";
		Query emquery = em.createQuery(query);
		List<TbPaquetesmateriale> lista = emquery.getResultList();
		return lista;
	}

	public TbPaquetesmateriale obtenerMaterial(EntityManager em, int id) {
		try {
			TbPaquetesmateriale entidad = (TbPaquetesmateriale) em.find(TbPaquetesmateriale.class, id);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbPaquetesmateriale> buscarMaterial(EntityManager em,TbPaquetesmateriale material) {
		String query = "select m from TbPaquetesmateriale m where m.descripcion like ?1 order by m.idMateriales";
		Query emquery = em.createQuery(query);
		emquery.setParameter(1,"%" + material.getDescripcion() + "%");
		List<TbPaquetesmateriale> lista = emquery.getResultList();
		return lista;
	}

}
