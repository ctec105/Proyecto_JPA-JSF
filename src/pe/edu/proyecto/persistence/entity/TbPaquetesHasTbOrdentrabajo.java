package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_paquetes_has_tb_ordentrabajo database table.
 * 
 */
@Entity
@Table(name="tb_paquetes_has_tb_ordentrabajo")
public class TbPaquetesHasTbOrdentrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbPaquetesHasTbOrdentrabajoPK id;

	private double precio;

//	//bi-directional many-to-one association to TbOrdentrabajo
//    @ManyToOne
//	@JoinColumn(name="tb_ordenTrabajo_id_otrabajo")
//	private TbOrdentrabajo tbOrdentrabajo;
//
//	//bi-directional many-to-one association to TbPaquete
//    @ManyToOne
//	@JoinColumn(name="tb_paquetes_id_paquetes")
//	private TbPaquete tbPaquete;

//    public TbPaquetesHasTbOrdentrabajo() {
//    	tbPaquete = new TbPaquete();
//    }
//
//	public TbPaquetesHasTbOrdentrabajoPK getId() {
//		return this.id;
//	}
//
//	public void setId(TbPaquetesHasTbOrdentrabajoPK id) {
//		this.id = id;
//	}
	
	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

//	public TbOrdentrabajo getTbOrdentrabajo() {
//		return this.tbOrdentrabajo;
//	}
//
//	public void setTbOrdentrabajo(TbOrdentrabajo tbOrdentrabajo) {
//		this.tbOrdentrabajo = tbOrdentrabajo;
//	}
//	
//	public TbPaquete getTbPaquete() {
//		return this.tbPaquete;
//	}
//
//	public void setTbPaquete(TbPaquete tbPaquete) {
//		this.tbPaquete = tbPaquete;
//	}
	
	
	@Id
	@Column(name = "tb_paquetes_id_paquetes")
	private int paquetes_paquetes;

	@Id
	@Column(name = "tb_ordenTrabajo_id_otrabajo")
	private int ordenTrabajo_otrabajo;

	public int getPaquetes_paquetes() {
		return paquetes_paquetes;
	}

	public void setPaquetes_paquetes(int paquetes_paquetes) {
		this.paquetes_paquetes = paquetes_paquetes;
	}

	public int getOrdenTrabajo_otrabajo() {
		return ordenTrabajo_otrabajo;
	}

	public void setOrdenTrabajo_otrabajo(int ordenTrabajo_otrabajo) {
		this.ordenTrabajo_otrabajo = ordenTrabajo_otrabajo;
	}
	
}