package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbEquipo;

public class EquipoJPA {
	
	public TbEquipo obtenerEquipo(EntityManager em, String nroTarjeta) {
		try {
			TbEquipo entidad = (TbEquipo) em.find(TbEquipo.class, nroTarjeta);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbEquipo> listarEquipos(EntityManager em) {
		String query = "SELECT o FROM TbEquipo o ORDER BY o.nrotarjeta";
		Query emquery = em.createQuery(query);
		List<TbEquipo> listaEntidad = emquery.getResultList();
		return listaEntidad;
	}
	
	public List<TbEquipo> buscarEquipo(EntityManager em, String nroTarjeta, int clase) {
		try {
			String query = "SELECT e FROM TbEquipo e WHERE e.nrotarjeta like ?1 AND e.tbClaseequipo.id_claseEquipo = ?2";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, "%" + nroTarjeta + "%");
			emquery.setParameter(2, clase);
			return emquery.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
