package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_paquetesmateriales database table.
 * 
 */
@Entity
@Table(name="tb_paquetesmateriales")
public class TbPaquetesmateriale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_materiales")
	private int idMateriales;

	private String descripcion;

	private double precio;

//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesmateriale
//	@OneToMany(mappedBy="tbPaquetesmateriale")
//	private Set<TbPaquetesHasTbPaquetesmateriale> tbPaquetesHasTbPaquetesmateriales;

    public TbPaquetesmateriale() {
    }

	public int getIdMateriales() {
		return this.idMateriales;
	}

	public void setIdMateriales(int idMateriales) {
		this.idMateriales = idMateriales;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

//	public Set<TbPaquetesHasTbPaquetesmateriale> getTbPaquetesHasTbPaquetesmateriales() {
//		return this.tbPaquetesHasTbPaquetesmateriales;
//	}
//
//	public void setTbPaquetesHasTbPaquetesmateriales(Set<TbPaquetesHasTbPaquetesmateriale> tbPaquetesHasTbPaquetesmateriales) {
//		this.tbPaquetesHasTbPaquetesmateriales = tbPaquetesHasTbPaquetesmateriales;
//	}
	
}