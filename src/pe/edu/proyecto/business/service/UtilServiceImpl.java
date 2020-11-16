package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbArea;
import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbClaseequipo;
import pe.edu.proyecto.persistence.entity.TbEspecialidade;
import pe.edu.proyecto.persistence.entity.TbEstado;
import pe.edu.proyecto.persistence.entity.TbMarca;
import pe.edu.proyecto.persistence.entity.TbModelo;
import pe.edu.proyecto.persistence.jpa.UtilJPA;

public class UtilServiceImpl implements UtilService {

	UtilJPA utilJPA = new UtilJPA();
	private EntityManager em;
	
	public UtilServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}

	@Override
	public List<TbClaseequipo> listarClasesEquipos() throws Exception {
		List<TbClaseequipo> lista = utilJPA.listarClasesEquipos(em);
		return lista;
	}

	
	@Override
	public List<TbEstado> listarEstados() throws Exception {
		List<TbEstado> listaEntidad=  utilJPA.listarEstados(em);
		return listaEntidad;
	}
	
	@Override
	public List<TbMarca> listarMarcas() throws Exception {
		List<TbMarca> listaEntidad =  utilJPA.listarMarcas(em);
		return listaEntidad;
	}
	@Override
	public List<TbModelo> listarModelos() throws Exception {
		List<TbModelo> listaEntidad =  utilJPA.listarModelos(em);
		return listaEntidad;
	}
	@Override
	public List<TbModelo> listarModelosxMarca(String marca) throws Exception {
		List<TbModelo> listaEntidad =  utilJPA.listarModelosxMarca(em,marca);
		return listaEntidad;
	}
	
	@Override
	public List<TbEspecialidade> listarEspecialidades() throws Exception {
		List<TbEspecialidade> lista = utilJPA.listarEspecialidades(em);
		return lista;
	}

	
	@Override
	public TbEstado getEstado(int id) throws Exception {
		TbEstado entidad = utilJPA.getEstado(em,id);
		return entidad;
	}

	
	@Override
	public TbArea obtenerArea(int codArea) throws Exception {
		TbArea entidad = utilJPA.obtenerArea(em,codArea);
		return entidad;
	}

	
	@Override
	public List<TbArea> listarAreas() throws Exception {
		List<TbArea> listaEntidad = utilJPA.listarAreas(em);
		return listaEntidad;
	}

	@Override
	public List<TbAreaHasTbMenu> listarPermisosXArea(int area)throws Exception{
		List<TbAreaHasTbMenu> listaEntidad = utilJPA.listarPermisosXArea(em,area);
		return listaEntidad;
	}

	@Override
	public void modificarPermisos(TbAreaHasTbMenu areamenu) {
		utilJPA.modificarPermisos(em,areamenu);
	}

}
