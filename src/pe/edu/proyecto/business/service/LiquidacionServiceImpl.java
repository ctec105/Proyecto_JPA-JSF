package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;
import pe.edu.proyecto.persistence.jpa.LiquidacionJPA;

public class LiquidacionServiceImpl implements LiquidacionService {

	LiquidacionJPA liquidacionJPA = new LiquidacionJPA();
	private EntityManager em;
	
	public LiquidacionServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
	
	@Override
	public List<TbOrdenliquidacion> listarLiquidaciones() throws Exception {
		List<TbOrdenliquidacion> lista = liquidacionJPA.listarLiquidaciones(em);
		return lista;
	}

	@Override
	public TbOrdenliquidacion obtenerLiquidacion(TbOrdenliquidacion liquidacion) throws Exception {
		TbOrdenliquidacion entidad = liquidacionJPA.obtenerLiquidacion(em,liquidacion);
		return entidad;
	}

	@Override
	public int registrarLiquidacion(TbOrdenliquidacion liquidacion) throws Exception {
		int resultado = liquidacionJPA.registrarLiquidacion(em,liquidacion);
		return resultado;
	}

	@Override
	public int actualizarLiquidacion(TbOrdenliquidacion liquidacion) {
		int resultado = liquidacionJPA.actualizarLiquidacion(em,liquidacion);
		return resultado;
	}

}
