package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_paquetes database table.
 * 
 */
@Entity
@Table(name="tb_paquetes")
public class TbPaquete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_paquetes")
	private int idPaquetes;

	private int duracion;

	private String marca;

	private String modelo;

	private String nombre;

	private double precio;

//	//bi-directional many-to-one association to TbPaquetesHasTbOrdentrabajo
//	@OneToMany(mappedBy="tbPaquete")
//	private Set<TbPaquetesHasTbOrdentrabajo> tbPaquetesHasTbOrdentrabajos;
//
//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesactividade
//	@OneToMany(mappedBy="tbPaquete")
//	private Set<TbPaquetesHasTbPaquetesactividade> tbPaquetesHasTbPaquetesactividades;
//
//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesherramienta
//	@OneToMany(mappedBy="tbPaquete")
//	private Set<TbPaquetesHasTbPaquetesherramienta> tbPaquetesHasTbPaquetesherramientas;
//
//	//bi-directional many-to-one association to TbPaquetesHasTbPaquetesmateriale
//	@OneToMany(mappedBy="tbPaquete")
//	private Set<TbPaquetesHasTbPaquetesmateriale> tbPaquetesHasTbPaquetesmateriales;

    public TbPaquete() {
    }

	public int getIdPaquetes() {
		return this.idPaquetes;
	}

	public void setIdPaquetes(int idPaquetes) {
		this.idPaquetes = idPaquetes;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

//	public Set<TbPaquetesHasTbOrdentrabajo> getTbPaquetesHasTbOrdentrabajos() {
//		return this.tbPaquetesHasTbOrdentrabajos;
//	}
//
//	public void setTbPaquetesHasTbOrdentrabajos(Set<TbPaquetesHasTbOrdentrabajo> tbPaquetesHasTbOrdentrabajos) {
//		this.tbPaquetesHasTbOrdentrabajos = tbPaquetesHasTbOrdentrabajos;
//	}
//	
//	public Set<TbPaquetesHasTbPaquetesactividade> getTbPaquetesHasTbPaquetesactividades() {
//		return this.tbPaquetesHasTbPaquetesactividades;
//	}
//
//	public void setTbPaquetesHasTbPaquetesactividades(Set<TbPaquetesHasTbPaquetesactividade> tbPaquetesHasTbPaquetesactividades) {
//		this.tbPaquetesHasTbPaquetesactividades = tbPaquetesHasTbPaquetesactividades;
//	}
//	
//	public Set<TbPaquetesHasTbPaquetesherramienta> getTbPaquetesHasTbPaquetesherramientas() {
//		return this.tbPaquetesHasTbPaquetesherramientas;
//	}
//
//	public void setTbPaquetesHasTbPaquetesherramientas(Set<TbPaquetesHasTbPaquetesherramienta> tbPaquetesHasTbPaquetesherramientas) {
//		this.tbPaquetesHasTbPaquetesherramientas = tbPaquetesHasTbPaquetesherramientas;
//	}
//	
//	public Set<TbPaquetesHasTbPaquetesmateriale> getTbPaquetesHasTbPaquetesmateriales() {
//		return this.tbPaquetesHasTbPaquetesmateriales;
//	}
//
//	public void setTbPaquetesHasTbPaquetesmateriales(Set<TbPaquetesHasTbPaquetesmateriale> tbPaquetesHasTbPaquetesmateriales) {
//		this.tbPaquetesHasTbPaquetesmateriales = tbPaquetesHasTbPaquetesmateriales;
//	}
	
}