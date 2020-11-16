package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_detalleprefactura database table.
 * 
 */
@Entity
@Table(name="tb_detalleprefactura")
@NamedQueries({
@NamedQuery(name = "findPaqDetFact", query = "SELECT l.idLiquidacion, p.paquetes_paquetes, pq.nombre, p.precio, "
		+ "SUM(p.precio), COUNT(pq)"
		+ "FROM TbOrdenliquidacion l, TbOrdentrabajo o, "
		+ "TbPaquetesHasTbOrdentrabajo p, TbPaquete pq  "
		+ "WHERE l.idLiquidacion = :idLiquidacion "
		+ "AND l.idLiquidacion = o.idLiquidacion "
		+ "AND o.idOtrabajo = p.ordenTrabajo_otrabajo "
		+ "AND p.paquetes_paquetes = pq.idPaquetes "
		+ "GROUP BY l.idLiquidacion, p.paquetes_paquetes, pq.nombre, p.precio"),

@NamedQuery(name = "findTecDetFact", query = "SELECT l.idLiquidacion, p.tecnicos_tecnicos, pq.nombre, e.costoDia, "
		+ "SUM(p.dias), COUNT(pq) "
		+ "FROM TbOrdenliquidacion l, TbOrdentrabajo o, "
		+ "TbOrdentrabajoHasTbTecnico p, TbTecnico pq, TbEspecialidade e "
		
		+ "WHERE l.idLiquidacion = :idLiquidacion "
		+ "AND l.idLiquidacion = o.idLiquidacion "
		+ "AND o.idOtrabajo = p.ordenTrabajo_otrabajo "
		+ "AND p.tecnicos_tecnicos = pq.idTecnicos "
		+ "AND pq.tbEspecialidade.idEspecialidad = e.idEspecialidad "
		+ "GROUP BY l.idLiquidacion, p.tecnicos_tecnicos, pq.nombre, e.costoDia") })
public class TbDetalleprefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int cantidad;

	private String descripcion;

	private int item;

	@Column(name="precio_total")
	private double precioTotal;

	@Column(name="precio_unitario")
	private double precioUnitario;

	private double subtotal;

	//bi-directional many-to-one association to TbPrefactura
//    @ManyToOne
//	@JoinColumn(name="tb_prefactura_id_prefactura")
//	private TbPrefactura tbPrefactura;
    
    @Column(name = "tb_prefactura_id_prefactura")
	private int idPrefactura;

    public TbDetalleprefactura() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getItem() {
		return this.item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public double getPrecioTotal() {
		return this.precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public double getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

//	public TbPrefactura getTbPrefactura() {
//		return this.tbPrefactura;
//	}
//
//	public void setTbPrefactura(TbPrefactura tbPrefactura) {
//		this.tbPrefactura = tbPrefactura;
//	}
//	
	
	public int getIdPrefactura() {
		return idPrefactura;
	}

	public void setIdPrefactura(int idPrefactura) {
		this.idPrefactura = idPrefactura;
	}

}