package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_paquetes_has_tb_paquetesactividades database table.
 * 
 */
@Entity
@Table(name="tb_paquetes_has_tb_paquetesactividades")
public class TbPaquetesHasTbPaquetesactividade implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbPaquetesHasTbPaquetesactividadePK id;

	private double precio;

//	//bi-directional many-to-one association to TbPaquete
//    @ManyToOne
//	@JoinColumn(name="tb_paquetes_id_paquetes")
//	private TbPaquete tbPaquete;
//
//	//bi-directional many-to-one association to TbPaquetesactividade
//    @ManyToOne
//	@JoinColumn(name="tb_paquetesActividades_id_actividades")
//	private TbPaquetesactividade tbPaquetesactividade;

    public TbPaquetesHasTbPaquetesactividade() {
    }

//	public TbPaquetesHasTbPaquetesactividadePK getId() {
//		return this.id;
//	}
//
//	public void setId(TbPaquetesHasTbPaquetesactividadePK id) {
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
//	public TbPaquetesactividade getTbPaquetesactividade() {
//		return this.tbPaquetesactividade;
//	}
//
//	public void setTbPaquetesactividade(TbPaquetesactividade tbPaquetesactividade) {
//		this.tbPaquetesactividade = tbPaquetesactividade;
//	}
	
	@Id
	@Column(name = "tb_paquetes_id_paquetes")
	private int paquetes_paquetes;

	@Id
	@Column(name = "tb_paquetesActividades_id_actividades")
	private int paquetesActividades_actividades;

	public int getPaquetes_paquetes() {
		return paquetes_paquetes;
	}

	public void setPaquetes_paquetes(int paquetes_paquetes) {
		this.paquetes_paquetes = paquetes_paquetes;
	}

	public int getPaquetesActividades_actividades() {
		return paquetesActividades_actividades;
	}

	public void setPaquetesActividades_actividades(
			int paquetesActividades_actividades) {
		this.paquetesActividades_actividades = paquetesActividades_actividades;
	}

}