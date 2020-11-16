package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_area_has_tb_menu database table.
 * 
 */
@Entity
@Table(name="tb_area_has_tb_menu")
public class TbAreaHasTbMenu implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbAreaHasTbMenuPK id;

	private String ver;

	//bi-directional many-to-one association to TbArea
//    @ManyToOne
//	@JoinColumn(name="tb_area_id_area")
//	private TbArea tbArea;
	
	

	//bi-directional many-to-one association to TbMenu
	@Id
    @ManyToOne
	@JoinColumn(name="tb_menu_id_menu")
	private TbMenu tbMenu;

    public TbAreaHasTbMenu() {
    }

//	public TbAreaHasTbMenuPK getId() {
//		return this.id;
//	}
//
//	public void setId(TbAreaHasTbMenuPK id) {
//		this.id = id;
//	}
	
	public String getVer() {
		return this.ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

//	public TbArea getTbArea() {
//		return this.tbArea;
//	}
//
//	public void setTbArea(TbArea tbArea) {
//		this.tbArea = tbArea;
//	}
	
	public TbMenu getTbMenu() {
		return this.tbMenu;
	}

	public void setTbMenu(TbMenu tbMenu) {
		this.tbMenu = tbMenu;
	}
	
	@Id
	@Column(name = "tb_area_id_area")
	private int idArea;

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	
}