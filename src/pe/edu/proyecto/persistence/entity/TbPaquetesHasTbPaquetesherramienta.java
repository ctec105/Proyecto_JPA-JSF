package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_paquetes_has_tb_paquetesherramientas database table.
 * 
 */
@Entity
@Table(name="tb_paquetes_has_tb_paquetesherramientas")
public class TbPaquetesHasTbPaquetesherramienta implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbPaquetesHasTbPaquetesherramientaPK id;

	private double precio;

//	//bi-directional many-to-one association to TbPaquete
//    @ManyToOne
//	@JoinColumn(name="tb_paquetes_id_paquetes")
//	private TbPaquete tbPaquete;
//
//	//bi-directional many-to-one association to TbPaquetesherramienta
//    @ManyToOne
//	@JoinColumn(name="tb_paquetesHerramientas_id_herramientas")
//	private TbPaquetesherramienta tbPaquetesherramienta;

    public TbPaquetesHasTbPaquetesherramienta() {
    }

//	public TbPaquetesHasTbPaquetesherramientaPK getId() {
//		return this.id;
//	}
//
//	public void setId(TbPaquetesHasTbPaquetesherramientaPK id) {
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
//	public TbPaquetesherramienta getTbPaquetesherramienta() {
//		return this.tbPaquetesherramienta;
//	}
//
//	public void setTbPaquetesherramienta(TbPaquetesherramienta tbPaquetesherramienta) {
//		this.tbPaquetesherramienta = tbPaquetesherramienta;
//	}
	
	@Id
	@Column(name = "tb_paquetes_id_paquetes")
	private int paquetes_paquetes;

	@Id
	@Column(name = "tb_paquetesHerramientas_id_herramientas")
	private int paquetesHerramientas_herramientas;

	public int getPaquetes_paquetes() {
		return paquetes_paquetes;
	}

	public void setPaquetes_paquetes(int paquetes_paquetes) {
		this.paquetes_paquetes = paquetes_paquetes;
	}

	public int getPaquetesHerramientas_herramientas() {
		return paquetesHerramientas_herramientas;
	}

	public void setPaquetesHerramientas_herramientas(
			int paquetesHerramientas_herramientas) {
		this.paquetesHerramientas_herramientas = paquetesHerramientas_herramientas;
	}
	
}