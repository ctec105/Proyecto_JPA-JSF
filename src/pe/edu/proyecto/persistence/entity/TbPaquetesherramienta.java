package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_paquetesherramientas database table.
 * 
 */
@Entity
@Table(name="tb_paquetesherramientas")
public class TbPaquetesherramienta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_herramientas")
	private int idHerramientas;

	private String descripcion;

	private double precio;

//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesherramienta
//	@OneToMany(mappedBy="tbPaquetesherramienta")
//	private Set<TbPaquetesHasTbPaquetesherramienta> tbPaquetesHasTbPaquetesherramientas;

    public TbPaquetesherramienta() {
    }

	public int getIdHerramientas() {
		return this.idHerramientas;
	}

	public void setIdHerramientas(int idHerramientas) {
		this.idHerramientas = idHerramientas;
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

//	public Set<TbPaquetesHasTbPaquetesherramienta> getTbPaquetesHasTbPaquetesherramientas() {
//		return this.tbPaquetesHasTbPaquetesherramientas;
//	}
//
//	public void setTbPaquetesHasTbPaquetesherramientas(Set<TbPaquetesHasTbPaquetesherramienta> tbPaquetesHasTbPaquetesherramientas) {
//		this.tbPaquetesHasTbPaquetesherramientas = tbPaquetesHasTbPaquetesherramientas;
//	}
//	
}