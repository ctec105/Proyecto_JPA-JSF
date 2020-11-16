package pe.edu.proyecto.persistence.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pe.edu.proyecto.persistence.entity.TbDetalleprefactura;
import pe.edu.proyecto.persistence.entity.TbPrefactura;

public class PrefacturaJPA {

	public List<TbPrefactura> listadoPrefacturas(EntityManager em) {
		String query = "select p from TbPrefactura p order by p.idPrefactura";
		Query emquery = em.createQuery(query);
		List<TbPrefactura> lista = emquery.getResultList();
		return lista;
	}

	public TbPrefactura obtenerPrefactura(EntityManager em,TbPrefactura prefactura) {
		 TbPrefactura entidad = (TbPrefactura) em.find(TbPrefactura.class, prefactura.getIdPrefactura());
		 em.detach(entidad);
		 return entidad;
	}
	
	public List<TbDetalleprefactura> getDetFactPaq(EntityManager em,TbPrefactura prefactura)throws Exception {
		try {
			Query q = em.createNamedQuery("findPaqDetFact");
			q.setParameter("idLiquidacion", prefactura.getTb_ordenLiquidacion_id_liquidacion());
			Iterator it = q.getResultList().iterator();
			int i = 1;
			List<TbDetalleprefactura> rst = new ArrayList<TbDetalleprefactura>();
			while (it.hasNext()) {
				Object[] par = (Object[]) it.next();
				System.out.println("ID Liquidación: " + par[0].toString() + " ID Paquete: " + par[1].toString() + " Nombre Paquete: " + par[2].toString() + " ID Paquete: " + par[3].toString() + " Precio UNitario: " + par[4].toString() + " Cantidad: " + par[5].toString());

				String idliq = par[0].toString();
				int item = ((Integer) par[1]).intValue();
				String desc = par[2].toString();
				Double pu = (Double) par[3];
				Double subttl = (Double) par[4];
				String cant = par[5].toString();
				int icant = Integer.parseInt(cant);

				TbDetalleprefactura df = new TbDetalleprefactura();
				// df.setId(i);
//				TbPrefactura fff = new TbPrefactura();
//				fff.setIdPrefactura(prefactura.getIdPrefactura());
//				df.setTbPrefactura(fff);//(prefactura.getIdPrefactura());
				
				//df.setIdPrefactura(prefactura.getIdPrefactura());
				df.setId(Integer.parseInt(idliq));
				df.setItem(item);
				df.setDescripcion(desc);
				df.setPrecioUnitario(pu);
				df.setSubtotal(subttl);
				df.setCantidad(icant);
				rst.add(df);
				i++;
			}
			System.out.println("Nro. de Resultados obtenidos: " + rst.size());
			return rst;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<TbDetalleprefactura> getDetFactTec(EntityManager em,TbPrefactura prefactura)throws Exception {
		try {
			Query q = em.createNamedQuery("findTecDetFact");
			q.setParameter("idLiquidacion", prefactura.getTb_ordenLiquidacion_id_liquidacion());
			Iterator it = q.getResultList().iterator();
			int i = 1;
			List<TbDetalleprefactura> rst = new ArrayList<TbDetalleprefactura>();
			while (it.hasNext()) {
				Object[] par = (Object[]) it.next();
				System.out.println(par[0].toString() + " : " + par[1].toString() + " : " + par[2].toString() + " : " + par[3].toString() + " : " + par[4].toString() + " : " + par[5].toString());

				int idliq = ((Integer) par[0]).intValue();
				int item = ((Integer) par[1]).intValue();
				String desc = par[2].toString();
				Double pu = (Double) par[3];
				String horas = par[4].toString();
				Double subttl = pu * (Double.parseDouble(horas));
				String cant = par[5].toString();
				int icant = Integer.parseInt(horas);

				TbDetalleprefactura df = new TbDetalleprefactura();
				// df.setId(i);
//				TbPrefactura fff = new TbPrefactura();
//				fff.setIdPrefactura(prefactura.getIdPrefactura());
//				df.setTbPrefactura(fff);
				//df.setIdPrefactura(prefactura.getIdPrefactura());
				df.setId(idliq);
				df.setItem(item);
				df.setDescripcion(desc);
				df.setPrecioUnitario(pu);
				df.setSubtotal(subttl);
				df.setCantidad(icant);
				rst.add(df);
				i++;
			}
			System.out.println("Nro. de resultados obtenidos: " + rst.size());
			return rst;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	
	public int registrarPrefactura(EntityManager em, TbPrefactura prefactura, List<TbDetalleprefactura> detalleprefacturaList)throws Exception {
		try {
			System.out.println("Registrando el objeto prefactura");
			em.getTransaction().begin();
			em.persist(prefactura);
			em.getTransaction().commit();
			
			System.out.println("Buscando prefactura ingresada por id");
			prefactura = buscarPFxNoID(em,prefactura);
			if (prefactura != null) {
				Iterator it = detalleprefacturaList.iterator();

				while (it.hasNext()) {
					TbDetalleprefactura dp = new TbDetalleprefactura();
					dp = (TbDetalleprefactura) it.next();
					dp.setIdPrefactura(prefactura.getIdPrefactura());
					dp.setPrecioTotal(0.0);
					em.getTransaction().begin();
					em.persist(dp);
					em.getTransaction().commit();
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}
	private TbPrefactura buscarPFxNoID(EntityManager em, TbPrefactura p) {
		try {
			Query q = em.createQuery("SELECT p FROM TbPrefactura p Where p.cliente =?1 AND p.fecha =?2 AND p.tb_ordenLiquidacion_id_liquidacion =?3 ORDER BY p.idPrefactura DESC ");
			q.setParameter(1, p.getCliente());
			q.setParameter(2, p.getFecha());
			q.setParameter(3, p.getTb_ordenLiquidacion_id_liquidacion());

			List<TbPrefactura> rst = q.getResultList();
			if (rst.size() > 0) {
				return rst.get(0);
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
