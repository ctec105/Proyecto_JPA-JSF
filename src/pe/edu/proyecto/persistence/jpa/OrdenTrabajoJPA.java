package pe.edu.proyecto.persistence.jpa;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.entity.TbOrdenliquidacion;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajoHasTbTecnico;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbTecnico;
import pe.edu.proyecto.util.UtilesVarios;

public class OrdenTrabajoJPA {

	UtilesVarios ut = new UtilesVarios();
	
	public List<TbOrdentrabajo> listarOrdenes(EntityManager em) {
		String query = "SELECT u FROM TbOrdentrabajo u where u.idLiquidacion=-1 ORDER BY u.idOtrabajo";
		Query emquery = em.createQuery(query);
		List<TbOrdentrabajo> listaEntidad = emquery.getResultList();
		System.out.println("Registros encontrados :" + listaEntidad.size());
		return listaEntidad;
	}
	
	public TbOrdentrabajo obtenerOrden(EntityManager em, TbOrdentrabajo orden) {
		TbOrdentrabajo entidad = (TbOrdentrabajo) em.find(TbOrdentrabajo.class, orden.getIdOtrabajo());
		em.detach(entidad);
		return entidad;
	}
	
	public List<TbPaquetesHasTbOrdentrabajo> getDetallePaquetes(EntityManager em, int idTrabajo) {
		try {
			String query = "SELECT u FROM TbPaquetesHasTbOrdentrabajo u WHERE u.ordenTrabajo_otrabajo =?1";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, idTrabajo);
			return emquery.getResultList();
		} catch (Exception e) {
			System.out.println("error en el getPaquetes");
			return null;
		}
	}
	
	public List<TbOrdentrabajoHasTbTecnico> getDetalleTecnicos(EntityManager em, int idTrabajo) {
		try {
			String query = "SELECT u FROM TbOrdentrabajoHasTbTecnico u WHERE u.ordenTrabajo_otrabajo =?1";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, idTrabajo);
			return emquery.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error en el getTecnicos");
			return null;
		}
	}
	
	public int registrarOrden(EntityManager em, TbOrdentrabajo trabajo, List<TbPaquete> paquetes, List<TbTecnico> tecnicos) {
		try {
			for (Method m : trabajo.getClass().getMethods()) {
				if (m.getName().startsWith("get"))
					System.out.println(m.getName() + " : " + m.invoke(trabajo));
			}
			// Insertando la Orden de Trabajo
			System.out.println("Metodo registrarOrdenTrabajo() ...");
			
//			TbOrdenliquidacion liquidacion = new TbOrdenliquidacion();
//			liquidacion.setIdLiquidacion(-1);
//			trabajo.setTbOrdenliquidacion(liquidacion);
			
			trabajo.setIdLiquidacion(-1);
			
			em.getTransaction().begin();
			em.persist(trabajo);
			em.getTransaction().commit();
			
			// obtener el bean trabajo con todos sus datos( ID )
			trabajo = buscarTrabajoxNoID(em,trabajo);
			System.out.println("Nuevo codigo de trabajo:" + trabajo.getIdOtrabajo());
			em.getTransaction().begin();
			Iterator paq = paquetes.iterator();
			while (paq.hasNext()) {
				TbPaquete p = new TbPaquete();
				p = (TbPaquete) paq.next();
				TbPaquetesHasTbOrdentrabajo po = new TbPaquetesHasTbOrdentrabajo();
				po.setOrdenTrabajo_otrabajo(trabajo.getIdOtrabajo());
				po.setPaquetes_paquetes(p.getIdPaquetes());
				
//				TbPaquetesHasTbOrdentrabajoPK pp = new TbPaquetesHasTbOrdentrabajoPK();
//				pp.setTb_ordenTrabajo_id_otrabajo(trabajo.getIdOtrabajo());
//				pp.setTbPaquetesIdPaquetes(p.getIdPaquetes());
//				po.setId(pp);
				
				po.setPrecio(p.getPrecio());
				em.persist(po);
				System.out.println("Registrarndo en TbPaquetesHasTbOrdentrabajo");
			}
			Iterator tec = tecnicos.iterator();
			while (tec.hasNext()) {
				TbTecnico t = new TbTecnico();
				t = (TbTecnico) tec.next();
				TbOrdentrabajoHasTbTecnico ot = new TbOrdentrabajoHasTbTecnico();
				ot.setOrdenTrabajo_otrabajo(trabajo.getIdOtrabajo());
				ot.setTecnicos_tecnicos(t.getIdTecnicos());
				int dias;
				dias = ut.fechasDiferenciaEnDias(trabajo.getFechaInicio(),trabajo.getFechaTermino());
				ot.setDias(dias);
				em.persist(ot);
				System.out.println("Registrarndo en TbOrdentrabajoHasTbTecnico");
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
	public TbOrdentrabajo buscarTrabajoxNoID(EntityManager em,TbOrdentrabajo tra) throws Exception {
		try {
			
			String query = "SELECT u FROM TbOrdentrabajo u WHERE u.tbSolicitudIdSolicitud = ?1 AND u.fechaInicio =?2 AND u.usuario =?3";
			Query emquery = em.createQuery(query);
			emquery.setParameter(1, tra.getTbSolicitudIdSolicitud());
			emquery.setParameter(2, tra.getFechaInicio());
			emquery.setParameter(3, tra.getUsuario());

			return (TbOrdentrabajo) emquery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error en el buscarxNoID");
			return null;
		}

	}
	
	public int actualizarOrden(EntityManager em, TbOrdentrabajo trabajo,List<TbPaquete> paquetes, List<TbTecnico> tecnicos) {
		try {
			em.getTransaction().begin();
			em.merge(trabajo);
			em.getTransaction().commit();
			deleteDetPaquetes(em,trabajo.getIdOtrabajo());
			deleteDetTecnicos(em,trabajo.getIdOtrabajo());
			// Actualizando los detalles
			em.getTransaction().begin();
			Iterator paq = paquetes.iterator();
			while (paq.hasNext()) {
				TbPaquete p = new TbPaquete();
				p = (TbPaquete) paq.next();
				TbPaquetesHasTbOrdentrabajo po = new TbPaquetesHasTbOrdentrabajo();
				po.setOrdenTrabajo_otrabajo(trabajo.getIdOtrabajo());
				po.setPaquetes_paquetes(p.getIdPaquetes());
				po.setPrecio(p.getPrecio());
				em.persist(po);
			}
			Iterator tec = tecnicos.iterator();
			while (tec.hasNext()) {
				TbTecnico t = new TbTecnico();
				t = (TbTecnico) tec.next();
				TbOrdentrabajoHasTbTecnico ot = new TbOrdentrabajoHasTbTecnico();
				ot.setOrdenTrabajo_otrabajo(trabajo.getIdOtrabajo());
				ot.setTecnicos_tecnicos(t.getIdTecnicos());
				int dias;
				dias = ut.fechasDiferenciaEnDias(trabajo.getFechaInicio(),trabajo.getFechaTermino());
				ot.setDias(dias);
				em.persist(ot);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}
	private void deleteDetPaquetes(EntityManager em, int id) {
		Query q = em
				.createQuery("SELECT u FROM TbPaquetesHasTbOrdentrabajo u WHERE u.ordenTrabajo_otrabajo =?1");
		q.setParameter(1, id);
		List<TbPaquetesHasTbOrdentrabajo> rst = q.getResultList();
		Iterator it = rst.iterator();
		em.getTransaction().begin();
		while (it.hasNext()) {
			TbPaquetesHasTbOrdentrabajo t = new TbPaquetesHasTbOrdentrabajo();
			t = (TbPaquetesHasTbOrdentrabajo) it.next();
			em.remove(t);
		}
		em.getTransaction().commit();
	}
	private void deleteDetTecnicos(EntityManager em, int id) {
		Query q = em
				.createQuery("SELECT u FROM TbOrdentrabajoHasTbTecnico u WHERE u.ordenTrabajo_otrabajo =?1");
		q.setParameter(1, id);
		List<TbOrdentrabajoHasTbTecnico> rst = q.getResultList();
		Iterator it = rst.iterator();
		em.getTransaction().begin();
		while (it.hasNext()) {
			TbOrdentrabajoHasTbTecnico t = new TbOrdentrabajoHasTbTecnico();
			t = (TbOrdentrabajoHasTbTecnico) it.next();
			em.remove(t);
		}
		em.getTransaction().commit();
	}
	

	public List<TbOrdentrabajo> obtenerOrdenesXCliente(EntityManager em,TbCliente cliente) {
		try {
			Query emquery = em.createNamedQuery("obtenerOrdenesXCliente");
			emquery.setParameter("cliente", cliente.getIdCliente());
			List<TbOrdentrabajo> lista = emquery.getResultList(); 
			return lista;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void actualizar(EntityManager em, TbOrdentrabajo orden) {
		try {
			em.getTransaction().begin();
			em.merge(orden);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<TbOrdentrabajo> obtenerOrdenXIdLiqui(EntityManager em,int idLiquidacion) {
		String query = "select o from TbOrdentrabajo o where o.idLiquidacion=:liquidacion";
		Query emquery = em.createQuery(query);
		emquery.setParameter("liquidacion", idLiquidacion);
		List<TbOrdentrabajo> lista = emquery.getResultList();
		return lista;
	}
	
	
	
	
	
	

	
	
//	public int registrarOrdenTrabajo(TbOrdentrabajo trabajo,
//			Hashtable<String, TbPaquete> paquetes,
//			Hashtable<String, TbTecnico> tecnicos)throws Exception {
//				return 0;
//	
//	}
//	
//	public List buscarOrdenTrabajo(String estado) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public TbOrdentrabajo obtenerOrdenTrabajo(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//

//	
//	public TbOrdentrabajo getTrabajo(EntityManager em, int idOtrabajo) {
//		try {
//			String query = "SELECT u FROM TbOrdentrabajo u WHERE u.idOtrabajo =?1";
//			Query emquery = em.createQuery(query);
//			emquery.setParameter(1, idOtrabajo);
//			return (TbOrdentrabajo) emquery.getSingleResult();
//
//		} catch (Exception e) {
//			return null;
//		}
//	}

}
