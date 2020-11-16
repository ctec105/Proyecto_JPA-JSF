package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_modelo database table.
 * 
 */
@Entity
@Table(name="tb_modelo")
public class TbModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_modelo")
	private int idModelo;

	private String descripcion;

	//bi-directional many-to-one association to TbEquipo
	@OneToMany(mappedBy="tbModelo")
	private Set<TbEquipo> tbEquipos;

	//bi-directional many-to-one association to TbMarca
    @ManyToOne
	@JoinColumn(name="tb_marca_id_marca")
	private TbMarca tbMarca;

    public TbModelo() {
    }

	public int getIdModelo() {
		return this.idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
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
	
	public TbMarca getTbMarca() {
		return this.tbMarca;
	}

	public void setTbMarca(TbMarca tbMarca) {
		this.tbMarca = tbMarca;
	}
	
}