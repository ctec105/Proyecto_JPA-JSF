package pe.edu.proyecto.persistence.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbPaquetesmateriale;
import pe.edu.proyecto.persistence.entity.TbPaquetesactividade;
import pe.edu.proyecto.persistence.entity.TbPaquetesherramienta;
import pe.edu.proyecto.persistence.entity.TbPaquetesmateriale;

public class PaqueteJPA {

	public List<TbPaquete> buscarPaquete(EntityManager em, TbPaquete paquete) {
		try {
			String query = "select u from TbPaquete u WHERE u.nombre like ?1  AND u.marca=?2 AND u.modelo=?3 ";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, "%" + paquete.getNombre() + "%");
			emquery.setParameter(2, paquete.getMarca());
			emquery.setParameter(3, paquete.getModelo());
			return emquery.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<TbPaquete> listarPaquetes(EntityManager em) {
		try {
			String query = "select u from TbPaquete u ";
			Query emquery = em.createQuery(query);
			return emquery.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<TbPaquetesHasTbPaquetesherramienta> getDetalleHerramientas(EntityManager em, int idPaquetes) {
		try {
			String query = "SELECT u FROM TbPaquetesHasTbPaquetesherramienta u WHERE u.paquetes_paquetes =?1";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, idPaquetes);
			return emquery.getResultList();
		} catch (Exception e) {
			System.out.println("error en el getDetalleHerramientas");
			return null;
		}
	}

	public List<TbPaquetesHasTbPaquetesmateriale> getDetalleMateriales(EntityManager em, int idPaquetes) {
		try {
			String query = "SELECT u FROM TbPaquetesHasTbPaquetesmateriale u WHERE u.paquetes_paquetes =?1";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, idPaquetes);
			return emquery.getResultList();
		} catch (Exception e) {
			System.out.println("error en el getDetalleMateriales");
			return null;
		}
	}

	public List<TbPaquetesHasTbPaquetesactividade> getDetalleActividades(EntityManager em, int idPaquetes) {
		try {
			String query = "SELECT u FROM TbPaquetesHasTbPaquetesactividade u WHERE u.paquetes_paquetes =?1";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, idPaquetes);
			return emquery.getResultList();
		} catch (Exception e) {
			System.out.println("error en el getDetalleActividades");
			return null;
		}
	}

	public int registrarPaquete(EntityManager em, TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList) {
		try {
			System.out.println("Registrando Paquete");
			em.getTransaction().begin();
			em.persist(paquete);
			em.getTransaction().commit();
			System.out.println("Registrando Detalle");
			paquete = buscarPaquetexNoID(em, paquete);
			InsertarDetalles(em, paquete, herramientaList, materialList, actividadList);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public int actualizarPaquete(EntityManager em, TbPaquete paquete, List<TbPaquetesherramienta> herramientaList,
			List<TbPaquetesmateriale> materialList, List<TbPaquetesactividade> actividadList) {
		try {
			System.out.println("Actualizando Paquete");
			em.getTransaction().begin();
			em.merge(paquete);
			em.getTransaction().commit();
			System.out.println("Actualizando Detalle");
			paquete = buscarPaquetexNoID(em, paquete);
			eliminarDetalles(em, paquete);
			InsertarDetalles(em, paquete, herramientaList, materialList, actividadList);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public int eliminarPaquete(EntityManager em, TbPaquete paquete) {
		try {
			TbPaquete entidad = (TbPaquete) em.find(TbPaquete.class, paquete.getIdPaquetes());
			eliminarDetalles(em, entidad);
			em.getTransaction().begin();
			em.remove(entidad);
			em.getTransaction().commit();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	public TbPaquete obtenerPaquete(EntityManager em, int id) {
		try {
			TbPaquete entidad = (TbPaquete) em.find(TbPaquete.class, id);
			em.detach(entidad);
			return entidad;
		} catch (Exception e) {
			return null;
		}
	}
	
	private TbPaquete buscarPaquetexNoID(EntityManager em, TbPaquete p) {
		Query q = em.createQuery(
				"select u from TbPaquete u WHERE u.nombre = ?1 AND u.duracion=?2 AND u.marca=?3 AND u.modelo=?4 AND u.precio=?5 ORDER BY u.idPaquetes DESC");
		q.setParameter(1, p.getNombre());
		q.setParameter(2, p.getDuracion());
		q.setParameter(3, p.getMarca());
		q.setParameter(4, p.getModelo());
		q.setParameter(5, p.getPrecio());
		List<TbPaquete> rst = q.getResultList();
		if (rst.size() > 0) {
			return rst.get(0);
		}
		return null;
	}

	private void InsertarDetalles(EntityManager em, TbPaquete paquete, List<TbPaquetesherramienta> lstHer,
			List<TbPaquetesmateriale> lstMat, List<TbPaquetesactividade> lstAct) {
		em.getTransaction().begin();
		System.out.println("Registrando Herramientas");
		Iterator itHer = lstHer.iterator();
		while (itHer.hasNext()) {
			TbPaquetesherramienta her = (TbPaquetesherramienta) itHer.next();
			TbPaquetesHasTbPaquetesherramienta detHer = new TbPaquetesHasTbPaquetesherramienta();
			detHer.setPaquetes_paquetes(paquete.getIdPaquetes());
			detHer.setPaquetesHerramientas_herramientas(her.getIdHerramientas());
			detHer.setPrecio(her.getPrecio());
			em.persist(detHer);
		}
		System.out.println("Registrando Materiales");
		Iterator itMat = lstMat.iterator();
		while (itMat.hasNext()) {
			TbPaquetesmateriale mat = (TbPaquetesmateriale) itMat.next();
			TbPaquetesHasTbPaquetesmateriale detMat = new TbPaquetesHasTbPaquetesmateriale();
			detMat.setPaquetes_paquetes(paquete.getIdPaquetes());
			detMat.setPaquetesMateriales_materiales(mat.getIdMateriales());
			detMat.setPrecio(mat.getPrecio());
			em.persist(detMat);
		}
		System.out.println("Registrando Actividades");
		Iterator itAct = lstAct.iterator();
		while (itAct.hasNext()) {
			TbPaquetesactividade act = (TbPaquetesactividade) itAct.next();
			TbPaquetesHasTbPaquetesactividade detAct = new TbPaquetesHasTbPaquetesactividade();
			detAct.setPaquetes_paquetes(paquete.getIdPaquetes());
			detAct.setPaquetesActividades_actividades(act.getIdActividades());
			detAct.setPrecio(act.getPrecio());
			em.persist(detAct);
		}
		em.getTransaction().commit();
	}

	private void eliminarDetalles(EntityManager em, TbPaquete paquete) {
		Query q = em.createQuery("SELECT p FROM TbPaquetesHasTbPaquetesherramienta p WHERE p.paquetes_paquetes =?1");
		q.setParameter(1, paquete.getIdPaquetes());
		Iterator it = q.getResultList().iterator();

		Query q1 = em.createQuery("SELECT p FROM TbPaquetesHasTbPaquetesactividade p WHERE p.paquetes_paquetes =?1");
		q1.setParameter(1, paquete.getIdPaquetes());
		Iterator it1 = q1.getResultList().iterator();

		Query q2 = em.createQuery("SELECT p FROM TbPaquetesHasTbPaquetesmateriale p WHERE p.paquetes_paquetes =?1");
		q2.setParameter(1, paquete.getIdPaquetes());
		Iterator it2 = q2.getResultList().iterator();

		em.getTransaction().begin();
		while (it.hasNext()) {
			TbPaquetesHasTbPaquetesherramienta h = (TbPaquetesHasTbPaquetesherramienta) it.next();
			em.remove(h);
		}
		while (it1.hasNext()) {
			TbPaquetesHasTbPaquetesactividade a = (TbPaquetesHasTbPaquetesactividade) it1.next();
			em.remove(a);
		}
		while (it2.hasNext()) {
			TbPaquetesHasTbPaquetesmateriale m = (TbPaquetesHasTbPaquetesmateriale) it2.next();
			em.remove(m);
		}
		em.getTransaction().commit();
	}

}
