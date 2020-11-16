package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;

public class HerramientaJPA {

	public List<TbPaquetesherramienta> listarHerramientas(EntityManager em) {
		String query = "select h from TbPaquetesherramienta h order by h.idHerramientas";
		Query emquery = em.createQuery(query);
		List<TbPaquetesherramienta> lista = emquery.getResultList();
		return lista;
	}
	
	public TbPaquetesherramienta obtenerHerramienta(EntityManager em, int id) {
		try {
			TbPaquetesherramienta entidad = (TbPaquetesherramienta) em.find(TbPaquetesherramienta.class, id);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbPaquetesherramienta> buscarHerramienta(EntityManager em,TbPaquetesherramienta herramienta) {
		String query = "select h from TbPaquetesherramienta h where h.descripcion like ?1 order by h.idHerramientas";
		Query emquery = em.createQuery(query);
		emquery.setParameter(1,"%" + herramienta.getDescripcion() + "%");
		List<TbPaquetesherramienta> lista = emquery.getResultList();
		return lista;
	}

}
