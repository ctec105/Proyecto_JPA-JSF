package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_estado database table.
 * 
 */
@Entity
@Table(name="tb_estado")
public class TbEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtb_estado")
	private int idtbEstado;

	private String descripcion;

//	//bi-directional many-to-one association to TbSolicitudHasTbEstado
//	@OneToMany(mappedBy="tbEstado")
//	private Set<TbSolicitudHasTbEstado> tbSolicitudHasTbEstados;

    public TbEstado() {
    }

	public int getIdtbEstado() {
		return this.idtbEstado;
	}

	public void setIdtbEstado(int idtbEstado) {
		this.idtbEstado = idtbEstado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public Set<TbSolicitudHasTbEstado> getTbSolicitudHasTbEstados() {
//		return this.tbSolicitudHasTbEstados;
//	}
//
//	public void setTbSolicitudHasTbEstados(Set<TbSolicitudHasTbEstado> tbSolicitudHasTbEstados) {
//		this.tbSolicitudHasTbEstados = tbSolicitudHasTbEstados;
//	}
	
}