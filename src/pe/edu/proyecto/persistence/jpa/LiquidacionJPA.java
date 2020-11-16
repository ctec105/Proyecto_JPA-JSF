package pe.edu.proyecto.persistence.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbOrdentrabajo;

public class LiquidacionJPA {

	public List<TbOrdenliquidacion> listarLiquidaciones(EntityManager em) {
		String query = "SELECT o FROM TbOrdenliquidacion o where o.idLiquidacion <> -1 ORDER BY o.idLiquidacion";
		Query emquery = em.createQuery(query);
		List<TbOrdenliquidacion> listaEntidad = emquery.getResultList();
		return listaEntidad;
	}

	public TbOrdenliquidacion obtenerLiquidacion(EntityManager em,TbOrdenliquidacion liquidacion) {
		 try {
			 TbOrdenliquidacion entidad = (TbOrdenliquidacion)em.find(TbOrdenliquidacion.class,liquidacion.getIdLiquidacion());
			 em.detach(entidad);
			 return entidad;
		} catch (Exception e) {
			return null;
		}
	}

	public int registrarLiquidacion(EntityManager em,TbOrdenliquidacion liquidacion) {
		try {
			em.getTransaction().begin();
			em.persist(liquidacion);
			em.getTransaction().commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}

	public int actualizarLiquidacion(EntityManager em,TbOrdenliquidacion liquidacion) {
		try {
			em.getTransaction().begin();
			em.merge(liquidacion);
			em.flush();
			em.getTransaction().commit();
			return 0;
		} catch (Exception e) {
			
			return 1;
		}
	}

}
