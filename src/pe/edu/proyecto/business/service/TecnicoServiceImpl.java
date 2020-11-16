package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbTecnico;
import pe.edu.proyecto.persistence.jpa.TecnicoJPA;

public class TecnicoServiceImpl implements TecnicoService {

	TecnicoJPA tecnicoJPA = new TecnicoJPA();
	private EntityManager em;
	
	public TecnicoServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}

	@Override
	public TbTecnico getTecnico(int id) throws Exception {
		TbTecnico entidad = tecnicoJPA.getEquipo(em,id);
		return entidad;
	}
	

	@Override
	public List<TbTecnico> listarTecnicos() throws Exception {
		List<TbTecnico> lista = tecnicoJPA.listarTecnicos(em);
		return lista;
	}

	@Override
	public List<TbTecnico> popBuscarTecn(TbTecnico tecnico) throws Exception {
		List<TbTecnico> listaEntidad = tecnicoJPA.popBuscarTecn(em,tecnico);
		return listaEntidad;
	}
	
//	@Override
//	public List buscarTecnico(String nombre) throws Exception {
//		List<TbTecnico> listaEntidad =  tecnicoJPA.buscarTecnico(em,nombre);
//		return listaEntidad;
//		
//	}


}
