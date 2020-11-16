package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import sun.swing.UIAction;

import java.util.Set;


/**
 * The persistent class for the tb_menu database table.
 * 
 */
@Entity
@Table(name="tb_menu")
public class TbMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_menu")
	private int idMenu;
	
	private String nivel;

	private String titulo;

	@Column(name="action2")
	private String action;
	
	private String icono;
	
	private String actionlistener;
	
//	//bi-directional many-to-one association to TbAreaHasTbMenu
//	@OneToMany(mappedBy="tbMenu")
//	private Set<TbAreaHasTbMenu> tbAreaHasTbMenus;

    public TbMenu() {
    }

	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getActionlistener() {
		return actionlistener;
	}

	public void setActionlistener(String actionlistener) {
		this.actionlistener = actionlistener;
	}
	
//	public Set<TbAreaHasTbMenu> getTbAreaHasTbMenus() {
//		return this.tbAreaHasTbMenus;
//	}
//
//	public void setTbAreaHasTbMenus(Set<TbAreaHasTbMenu> tbAreaHasTbMenus) {
//		this.tbAreaHasTbMenus = tbAreaHasTbMenus;
//	}
	
}