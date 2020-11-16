package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbEquipo;
import pe.edu.proyecto.persistence.entity.TbTecnico;

public class TecnicoJPA {

	public TbTecnico getEquipo(EntityManager em, int id) {
		try {
			TbTecnico entidad = (TbTecnico) em.find(TbTecnico.class, id);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbTecnico> listarTecnicos(EntityManager em) {
		String query = "SELECT o FROM TbTecnico o ORDER BY o.idTecnicos";
		Query emquery = em.createQuery(query);
		List<TbTecnico> listaEntidad = emquery.getResultList();
		return listaEntidad;
	}
	
	public List<TbTecnico> popBuscarTecn(EntityManager em, TbTecnico tecnico) {
		try {
			String query = "SELECT m FROM TbTecnico m WHERE m.nombre like ?1 AND m.tbEspecialidade.idEspecialidad =?2";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, "%" + tecnico.getNombre() + "%");
			emquery.setParameter(2, tecnico.getTbEspecialidade().getIdEspecialidad());
			return emquery.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbTecnico> buscarTecnico(EntityManager em, String nombre) {
		// TODO Auto-generated method stub
		return null;
	}


}
