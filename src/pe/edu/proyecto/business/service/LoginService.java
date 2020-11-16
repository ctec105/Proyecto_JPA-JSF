package pe.edu.proyecto.business.service;


import pe.edu.proyecto.persistence.entity.TbUsuario;


public interface LoginService {

	public Boolean validar(TbUsuario u) throws Exception;
}
