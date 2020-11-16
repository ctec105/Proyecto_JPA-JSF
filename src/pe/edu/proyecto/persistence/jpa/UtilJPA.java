package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbClaseequipo;
import pe.edu.proyecto.persistence.entity.TbEspecialidade;
import pe.edu.proyecto.persistence.entity.TbEstado;
import pe.edu.proyecto.persistence.entity.TbMarca;
import pe.edu.proyecto.persistence.entity.TbModelo;

public class UtilJPA {

	public List<TbClaseequipo> listarClasesEquipos(EntityManager em) {
		try {
			String query = "SELECT m FROM TbClaseequipo m";
			Query emquery = em.createQuery(query);
			List<TbClaseequipo> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	
	public List<TbEstado> listarEstados(EntityManager em) {
		try {
			String query = "SELECT m FROM TbEstado m";
			Query emquery = em.createQuery(query);
			List<TbEstado> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<TbMarca> listarMarcas(EntityManager em) {
		try {
			String query = "SELECT m FROM TbMarca m";
			Query emquery = em.createQuery(query);
			List<TbMarca> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	public List<TbModelo> listarModelos(EntityManager em) {
		try {
			String query = "SELECT m FROM TbModelo m";
			Query emquery = em.createQuery(query);
			List<TbModelo> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	public List<TbModelo> listarModelosxMarca(EntityManager em, String marca) {
		System.out.println("entre");
		try {
			String query = "SELECT m FROM TbModelo m WHERE m.tbMarca.idMarca = " + marca;
			Query emquery = em.createQuery(query);
			List<TbModelo> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<TbEspecialidade> listarEspecialidades(EntityManager em) {
		try {
			String query = "SELECT m FROM TbEspecialidade m";
			Query emquery = em.createQuery(query);
			List<TbEspecialidade> lista = emquery.getResultList();
			return lista;
		} catch (Exception e) {
			return null;
		}
	}


	public TbEstado getEstado(EntityManager em, int id) {
		try {
			return em.find(TbEstado.class, id);
		} catch (Exception e) {
			return null;
		}
	}


	public TbArea obtenerArea(EntityManager em, int codArea) {
		try {
			return em.find(TbArea.class, codArea);
		} catch (Exception e) {
			return null;
		}
	}


	public List<TbArea> listarAreas(EntityManager em) {
		String query = "select a from TbArea a order by a.idArea";
		Query emquery = em.createQuery(query);
		List<TbArea> lista = emquery.getResultList();
		return lista;
	}


	public List<TbAreaHasTbMenu> listarPermisosXArea(EntityManager em, int area)throws Exception {
		String query = "select a from TbAreaHasTbMenu a where a.idArea=?1 order by a.tbMenu.nivel ASC";
		Query emquery = em.createQuery(query);
		emquery.setParameter(1, area);
		List<TbAreaHasTbMenu> lista = emquery.getResultList();
		return lista;
	}

	public void modificarPermisos(EntityManager em, TbAreaHasTbMenu areamenu) {
		em.getTransaction().begin();
		em.merge(areamenu);
		em.flush();
		em.getTransaction().commit();
	}

}
