package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_cliente database table.
 * 
 */
@Entity
@Table(name="tb_cliente")
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private String idCliente;

	private String direccion;

	private String razonsocial;

	private String telefono;

	//bi-directional many-to-one association to TbEquipo
	@OneToMany(mappedBy="tbCliente")
	private Set<TbEquipo> tbEquipos;

    public TbCliente() {
    }

	public String getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<TbEquipo> getTbEquipos() {
		return this.tbEquipos;
	}

	public void setTbEquipos(Set<TbEquipo> tbEquipos) {
		this.tbEquipos = tbEquipos;
	}
	
}