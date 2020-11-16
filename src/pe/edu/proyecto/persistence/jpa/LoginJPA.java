package pe.edu.proyecto.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbUsuario;
import pe.edu.proyecto.util.MD5Util;

public class LoginJPA {

	public Integer validar(TbUsuario u, EntityManager em) throws Exception {
		String query = "SELECT count(o) FROM TbUsuario o " +
				" WHERE o.login=:username and o.password=:password";
		Query emquery = em.createQuery(query);
		emquery.setParameter("username", u.getLogin());
		emquery.setParameter("password", MD5Util.md5Hex(u.getPassword()));
		Long c=(Long)emquery.getSingleResult();
		return c.intValue();
	}

}
