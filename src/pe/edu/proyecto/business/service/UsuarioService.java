package pe.edu.proyecto.business.service;

import java.util.List;

import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbUsuario;

public interface UsuarioService {

	public List<TbUsuario> listarUsuario() throws Exception;
	public TbUsuario obtenerUsuario(TbUsuario usuario)throws Exception;
	public void registrarUsuario(TbUsuario usuario)throws Exception;
	public void actualizarUsuario(TbUsuario usuario)throws Exception;
	public void eliminarUsuario(TbUsuario usuario)throws Exception;
	
	
	public TbUsuario obtenerUsuarioXLogin(TbUsuario usuario)throws Exception;
	
	public TbUsuario logueo(String login, String md5Hex)throws Exception;
	public List<TbAreaHasTbMenu> permisos(int i) throws Exception;
	
}
