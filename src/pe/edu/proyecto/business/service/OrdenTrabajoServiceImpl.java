package pe.edu.proyecto.business.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import pe.edu.proyecto.persistence.entity.TbCliente;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbOrdentrabajoHasTbTecnico;
import pe.edu.proyecto.persistence.entity.TbPaquete;
import pe.edu.proyecto.persistence.entity.TbPaquetesHasTbOrdentrabajo;
import pe.edu.proyecto.persistence.entity.TbTecnico;
import pe.edu.proyecto.persistence.jpa.OrdenTrabajoJPA;

public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

	OrdenTrabajoJPA ordenTrabajoJPA = new OrdenTrabajoJPA();
	private EntityManager em;
	
	public OrdenTrabajoServiceImpl(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}
	
	public void close(EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
	
	
	public List<TbOrdentrabajo> listarOrdenes() throws Exception {
		List<TbOrdentrabajo> lista = ordenTrabajoJPA.listarOrdenes(em);
		return lista;
	}
	
	@Override
	public TbOrdentrabajo obtenerOrden(TbOrdentrabajo orden) throws Exception {
		TbOrdentrabajo entidad = ordenTrabajoJPA.obtenerOrden(em, orden);
		return entidad;
	}
	
	public List<TbPaquetesHasTbOrdentrabajo> getDetallePaquetes(int idTrabajo)throws Exception {
		List<TbPaquetesHasTbOrdentrabajo> lista = ordenTrabajoJPA.getDetallePaquetes(em,idTrabajo);
		return lista;
	}
	
	@Override
	public List<TbOrdentrabajoHasTbTecnico> getDetalleTecnicos(int idTrabajo) throws Exception {
		List<TbOrdentrabajoHasTbTecnico> lista = ordenTrabajoJPA.getDetalleTecnicos(em,idTrabajo);
		return lista;
	}
	
	public int registrarOrden(TbOrdentrabajo trabajo, List<TbPaquete> paquetes,List<TbTecnico> tecnicos) throws Exception {
		return ordenTrabajoJPA.registrarOrden(em,trabajo, paquetes, tecnicos);
	}
	
	public int actualizarOrden(TbOrdentrabajo trabajo, List<TbPaquete> paquetes,List<TbTecnico> tecnicos) throws Exception {
		return ordenTrabajoJPA.actualizarOrden(em,trabajo, paquetes, tecnicos);
	}

	
	@Override
	public List<TbOrdentrabajo> obtenerOrdenesXCliente(TbCliente cliente)throws Exception {
		List<TbOrdentrabajo> listado = ordenTrabajoJPA.obtenerOrdenesXCliente(em,cliente);
		return listado;
	}
	
	public void actualizar(TbOrdentrabajo orden) throws Exception {
		ordenTrabajoJPA.actualizar(em,orden);
	}

	@Override
	public List<TbOrdentrabajo> obtenerOrdenXIdLiqui(int idLiquidacion) {
		 List<TbOrdentrabajo> listaEntidad = ordenTrabajoJPA.obtenerOrdenXIdLiqui(em,idLiquidacion);
		 return listaEntidad;
	}
	

	
	
	
//	@Override
//	public TbOrdentrabajo getTrabajo(int idOtrabajo) throws Exception{
//		TbOrdentrabajo entidad = ordenTrabajoJPA.getTrabajo(em,idOtrabajo);
//		return entidad;
//	}
//	
//	public int registrarOrdenTrabajo(TbOrdentrabajo trabajo, Hashtable<String, TbPaquete> paquetes,Hashtable<String, TbTecnico> tecnicos) throws Exception {
//		return ordenTrabajoJPA.registrarOrdenTrabajo(trabajo, paquetes, tecnicos);
//	}
//
//	public List buscarOrdenTrabajo(String estado) throws Exception {
//		return ordenTrabajoJPA.buscarOrdenTrabajo(estado);
//	}
//
//	public TbOrdentrabajo obtenerOrdenTrabajo(int id) throws Exception {
//		return ordenTrabajoJPA.obtenerOrdenTrabajo(id);
//	}
//


}
