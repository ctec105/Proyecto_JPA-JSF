package pe.edu.proyecto.business.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbUsuario;
import pe.edu.proyecto.persistence.jpa.LoginJPA;

public class LoginServiceImpl implements LoginService{

	LoginJPA loginJPA=new LoginJPA();
	private EntityManager em ;

    public LoginServiceImpl(EntityManagerFactory emf) {
		em=emf.createEntityManager();
	}
    
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
    
	@Override
	public Boolean validar(TbUsuario u) throws Exception{
		int resultado=loginJPA.validar(u, em);
		if(resultado==0)
			return false;
		else 
			return true; 
	}
	
}
