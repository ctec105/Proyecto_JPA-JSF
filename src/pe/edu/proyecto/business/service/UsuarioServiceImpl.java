package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbUsuario;
import pe.edu.proyecto.persistence.jpa.UsuarioJPA;

public class UsuarioServiceImpl implements UsuarioService {

	UsuarioJPA usuarioJpa = new UsuarioJPA();
	private EntityManager em;
	
	public UsuarioServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public List<TbUsuario> listarUsuario() throws Exception {
		List<TbUsuario> listaEntidad = usuarioJpa.listarUsuario(em);
		return listaEntidad;
	}
	
	@Override
	public TbUsuario obtenerUsuario(TbUsuario usuario) throws Exception {
		 TbUsuario entidad = usuarioJpa.obtenerUsuario(em, usuario);
		 return entidad;
	}

	@Override
	public TbUsuario obtenerUsuarioXLogin(TbUsuario usuario) throws Exception {
		TbUsuario entidad = usuarioJpa.obtenerUsuarioXLogin(em,usuario);
		return entidad;
	}

	@Override
	public void registrarUsuario(TbUsuario usuario) throws Exception {
		usuarioJpa.registrarUsuario(em,usuario);
		
	}

	@Override
	public void actualizarUsuario(TbUsuario usuario) throws Exception {
		usuarioJpa.actualizarUsuario(em,usuario);
		
	}

	@Override
	public void eliminarUsuario(TbUsuario usuario) throws Exception {
		usuarioJpa.eliminarUsuario(em,usuario);
		
	}


	@Override
	public TbUsuario logueo(String login, String md5Hex) throws Exception{
		TbUsuario entidad = usuarioJpa.logueo(em,login,md5Hex);
		return entidad;
	}
	@Override
	public List<TbAreaHasTbMenu> permisos(int i) throws Exception {
		return usuarioJpa.permisos(em,i);
	}
	
}
