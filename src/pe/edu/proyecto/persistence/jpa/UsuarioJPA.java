package pe.edu.proyecto.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbAreaHasTbMenu;
import pe.edu.proyecto.persistence.entity.TbUsuario;

public class UsuarioJPA {

	public List<TbUsuario> listarUsuario(EntityManager em) {
		String query = "select u from TbUsuario u";
		Query emquery = em.createQuery(query);
		List<TbUsuario> lista = emquery.getResultList();
		return lista;
	}
	
	public TbUsuario obtenerUsuario(EntityManager em, TbUsuario usuario) {
		TbUsuario entidad =  (TbUsuario)em.find(TbUsuario.class, usuario.getIdUsuario());
		em.detach(entidad);
		return entidad;
	}

	public void registrarUsuario(EntityManager em, TbUsuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.flush();
		em.getTransaction().commit();
	}

	public void actualizarUsuario(EntityManager em, TbUsuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.flush();
		em.getTransaction().commit();
	}

	public void eliminarUsuario(EntityManager em, TbUsuario usuario) {
		em.getTransaction().begin();
		TbUsuario entidad =  (TbUsuario)em.find(TbUsuario.class, usuario.getIdUsuario());
		em.remove(entidad);
		em.flush();
		em.getTransaction().commit();
	}
	
	public TbUsuario obtenerUsuarioXLogin(EntityManager em, TbUsuario usuario) {
		String query = "select u from TbUsuario u where u.login=:login";
		Query emquery = em.createQuery(query);
		emquery.setParameter("login", usuario.getLogin());
		TbUsuario entidad = (TbUsuario) emquery.getSingleResult();
		return entidad;
	}
	
	public TbUsuario logueo(EntityManager em, String login,String md5Hex) {
		Query q = em.createQuery("select u from TbUsuario u WHERE u.login = ?1 AND u.password = ?2");
		q.setParameter(1, login);
		q.setParameter(2, md5Hex);

		List<TbUsuario> results = q.getResultList();

		if (results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
	
	public List<TbAreaHasTbMenu> permisos(EntityManager em, int idArea) throws Exception {
		Query q = em.createQuery("select u from TbAreaHasTbMenu u WHERE u.idArea = ?1 AND  u.ver = ?2 ORDER BY u.tbMenu.nivel ASC ");
		q.setParameter(1, idArea);
		q.setParameter(2, "S");

		List<TbAreaHasTbMenu> results = q.getResultList();

		for (int i = 0; i < results.size(); i++) {
			TbAreaHasTbMenu l = results.get(i);
			System.out.println(l.getTbMenu().getTitulo());
		}

		System.out.println("Registros encontrados :" + results.size());

		return results;
	}





}
