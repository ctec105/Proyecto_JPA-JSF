package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_paquetes_has_tb_paquetesmateriales database table.
 * 
 */
@Entity
@Table(name="tb_paquetes_has_tb_paquetesmateriales")
public class TbPaquetesHasTbPaquetesmateriale implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbPaquetesHasTbPaquetesmaterialePK id;

	private double precio;

//	//bi-directional many-to-one association to TbPaquete
//    @ManyToOne
//	@JoinColumn(name="tb_paquetes_id_paquetes")
//	private TbPaquete tbPaquete;
//
//	//bi-directional many-to-one association to TbPaquetesmateriale
//    @ManyToOne
//	@JoinColumn(name="tb_paquetesMateriales_id_materiales")
//	private TbPaquetesmateriale tbPaquetesmateriale;

    public TbPaquetesHasTbPaquetesmateriale() {
    }

//	public TbPaquetesHasTbPaquetesmaterialePK getId() {
//		return this.id;
//	}
//
//	public void setId(TbPaquetesHasTbPaquetesmaterialePK id) {
//		this.id = id;
//	}
	
	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

//	public TbPaquete getTbPaquete() {
//		return this.tbPaquete;
//	}
//
//	public void setTbPaquete(TbPaquete tbPaquete) {
//		this.tbPaquete = tbPaquete;
//	}
//	
//	public TbPaquetesmateriale getTbPaquetesmateriale() {
//		return this.tbPaquetesmateriale;
//	}
//
//	public void setTbPaquetesmateriale(TbPaquetesmateriale tbPaquetesmateriale) {
//		this.tbPaquetesmateriale = tbPaquetesmateriale;
//	}
	
	@Id
	@Column(name = "tb_paquetes_id_paquetes")
	private int paquetes_paquetes;

	@Id
	@Column(name = "tb_paquetesMateriales_id_materiales")
	private int paquetesMateriales_materiales;

	public int getPaquetes_paquetes() {
		return paquetes_paquetes;
	}

	public void setPaquetes_paquetes(int paquetes_paquetes) {
		this.paquetes_paquetes = paquetes_paquetes;
	}

	public int getPaquetesMateriales_materiales() {
		return paquetesMateriales_materiales;
	}

	public void setPaquetesMateriales_materiales(int paquetesMateriales_materiales) {
		this.paquetesMateriales_materiales = paquetesMateriales_materiales;
	}
	
}