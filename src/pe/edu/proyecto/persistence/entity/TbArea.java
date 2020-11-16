package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_area database table.
 * 
 */
@Entity
@Table(name="tb_area")
public class TbArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_area")
	private int idArea;

	private String descripcion;

	//bi-directional many-to-one association to TbAreaHasTbMenu
//	@OneToMany(mappedBy="tbArea")
//	private Set<TbAreaHasTbMenu> tbAreaHasTbMenus;

    public TbArea() {
    }

	public int getIdArea() {
		return this.idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public Set<TbAreaHasTbMenu> getTbAreaHasTbMenus() {
//		return this.tbAreaHasTbMenus;
//	}
//
//	public void setTbAreaHasTbMenus(Set<TbAreaHasTbMenu> tbAreaHasTbMenus) {
//		this.tbAreaHasTbMenus = tbAreaHasTbMenus;
//	}
	
}