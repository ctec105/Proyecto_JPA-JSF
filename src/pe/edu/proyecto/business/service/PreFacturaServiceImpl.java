package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbDetalleprefactura;
import pe.edu.proyecto.persistence.entity.TbPrefactura;
import pe.edu.proyecto.persistence.jpa.PrefacturaJPA;

public class PreFacturaServiceImpl implements PreFacturaService {

	PrefacturaJPA prefacturaJPA = new PrefacturaJPA();
	private EntityManager em;
	
	public PreFacturaServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	@Override
	public List<TbPrefactura> listadoPrefacturas() throws Exception {
		List<TbPrefactura> lista = prefacturaJPA.listadoPrefacturas(em);
		return lista;
	}

	@Override
	public TbPrefactura obtenerPrefactura(TbPrefactura prefactura)throws Exception {
		TbPrefactura entidad = prefacturaJPA.obtenerPrefactura(em,prefactura);
		return entidad;
	}

	public List<TbDetalleprefactura> getDetFactPaq(TbPrefactura prefactura)throws Exception {
		return prefacturaJPA.getDetFactPaq(em,prefactura);
	}

	public List<TbDetalleprefactura> getDetFactTec(TbPrefactura prefactura)throws Exception {
		return prefacturaJPA.getDetFactTec(em,prefactura);
	}

	@Override
	public int registrarPrefactura(TbPrefactura prefactura,List<TbDetalleprefactura> detalleprefacturaList) throws Exception {
		int resultado = prefacturaJPA.registrarPrefactura(em, prefactura,detalleprefacturaList);
		return resultado;
	}
}
