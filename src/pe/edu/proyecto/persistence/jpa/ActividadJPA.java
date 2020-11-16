package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;

public class ActividadJPA {

	public List<TbPaquetesactividade> listarActividades(EntityManager em) {
		String query = "select a from TbPaquetesactividade a order by a.idActividades";
		Query emquery = em.createQuery(query);
		List<TbPaquetesactividade> lista = emquery.getResultList();
		return lista;
	}

	public TbPaquetesactividade obtenerActividad(EntityManager em, int id) {
		try {
			TbPaquetesactividade entidad = (TbPaquetesactividade) em.find(TbPaquetesactividade.class, id);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbPaquetesactividade> buscarActividad(EntityManager em,TbPaquetesactividade actividad) {
		String query = "select a from TbPaquetesactividade a where a.descripcion like ?1 order by a.idActividades";
		Query emquery = em.createQuery(query);
		emquery.setParameter(1,"%" + actividad.getDescripcion() + "%");
		List<TbPaquetesactividade> lista = emquery.getResultList();
		return lista;
	}



}
