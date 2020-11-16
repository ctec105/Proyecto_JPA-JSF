package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_claseequipo database table.
 * 
 */
@Entity
@Table(name="tb_claseequipo")
public class TbClaseequipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_claseEquipo;

	private String descripcion;

	//bi-directional many-to-one association to TbEquipo
	@OneToMany(mappedBy="tbClaseequipo")
	private Set<TbEquipo> tbEquipos;

    public TbClaseequipo() {
    }

	public int getId_claseEquipo() {
		return this.id_claseEquipo;
	}

	public void setId_claseEquipo(int id_claseEquipo) {
		this.id_claseEquipo = id_claseEquipo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TbEquipo> getTbEquipos() {
		return this.tbEquipos;
	}

	public void setTbEquipos(Set<TbEquipo> tbEquipos) {
		this.tbEquipos = tbEquipos;
	}
	
}