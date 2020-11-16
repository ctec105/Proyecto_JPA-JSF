package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbEquipo;
import pe.edu.proyecto.persistence.jpa.EquipoJPA;

public class EquipoServiceImpl implements EquipoService {

	EquipoJPA equipoJPA = new EquipoJPA();
	private EntityManager em;
	
	public EquipoServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
	
	@Override
	public TbEquipo obtenerEquipo(String nroTarjeta) throws Exception {
		TbEquipo entidad = equipoJPA.obtenerEquipo(em,nroTarjeta);
		return entidad;
	}
	
	@Override
	public List<TbEquipo> listarEquipos() throws Exception {
		List<TbEquipo> lista = equipoJPA.listarEquipos(em);
		return lista;
	}
	
	@Override
	public List<TbEquipo> buscarEquipo(String nroTarjeta, int clase)throws Exception {
		List<TbEquipo> lista = equipoJPA.buscarEquipo(em,nroTarjeta, clase);
		return lista;
	}

}
