package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_marca database table.
 * 
 */
@Entity
@Table(name="tb_marca")
public class TbMarca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_marca")
	private int idMarca;

	private String descripcion;

	//bi-directional many-to-one association to TbModelo
	@OneToMany(mappedBy="tbMarca")
	private Set<TbModelo> tbModelos;

    public TbMarca() {
    }

	public int getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TbModelo> getTbModelos() {
		return this.tbModelos;
	}

	public void setTbModelos(Set<TbModelo> tbModelos) {
		this.tbModelos = tbModelos;
	}
	
}