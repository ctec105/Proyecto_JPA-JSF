package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_paquetesactividades database table.
 * 
 */
@Entity
@Table(name="tb_paquetesactividades")
public class TbPaquetesactividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_actividades")
	private int idActividades;

	private String descripcion;

	private double precio;

//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesactividade
//	@OneToMany(mappedBy="tbPaquetesactividade")
//	private Set<TbPaquetesHasTbPaquetesactividade> tbPaquetesHasTbPaquetesactividades;

    public TbPaquetesactividade() {
    }

	public int getIdActividades() {
		return this.idActividades;
	}

	public void setIdActividades(int idActividades) {
		this.idActividades = idActividades;
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

//	public Set<TbPaquetesHasTbPaquetesactividade> getTbPaquetesHasTbPaquetesactividades() {
//		return this.tbPaquetesHasTbPaquetesactividades;
//	}
//
//	public void setTbPaquetesHasTbPaquetesactividades(Set<TbPaquetesHasTbPaquetesactividade> tbPaquetesHasTbPaquetesactividades) {
//		this.tbPaquetesHasTbPaquetesactividades = tbPaquetesHasTbPaquetesactividades;
//	}
	
}