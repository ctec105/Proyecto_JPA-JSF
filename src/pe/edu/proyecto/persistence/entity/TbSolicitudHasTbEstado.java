package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_solicitud_has_tb_estado database table.
 * 
 */
@Entity
@Table(name="tb_solicitud_has_tb_estado")
public class TbSolicitudHasTbEstado implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbSolicitudHasTbEstadoPK id;

    @Temporal( TemporalType.TIMESTAMP)
	private Date fecha;

	private String usuario;

//	//bi-directional many-to-one association to TbEstado
//    @ManyToOne
//	@JoinColumn(name="tb_estado_idtb_estado")
//	private TbEstado tbEstado;
//
//	//bi-directional many-to-one association to TbSolicitud
//    @ManyToOne
//	@JoinColumn(name="tb_solicitud_id_solicitud")
//	private TbSolicitud tbSolicitud;

    public TbSolicitudHasTbEstado() {
    }

//	public TbSolicitudHasTbEstadoPK getId() {
//		return this.id;
//	}
//
//	public void setId(TbSolicitudHasTbEstadoPK id) {
//		this.id = id;
//	}
	
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

//	public TbEstado getTbEstado() {
//		return this.tbEstado;
//	}
//
//	public void setTbEstado(TbEstado tbEstado) {
//		this.tbEstado = tbEstado;
//	}
//	
//	public TbSolicitud getTbSolicitud() {
//		return this.tbSolicitud;
//	}
//
//	public void setTbSolicitud(TbSolicitud tbSolicitud) {
//		this.tbSolicitud = tbSolicitud;
//	}
	
	@Id
	@Column(name = "tb_solicitud_id_solicitud")
	private int solicitud_solicitud;

	@Id
	@Column(name = "tb_estado_idtb_estado")
	private int estado_idestado;

	public int getSolicitud_solicitud() {
		return solicitud_solicitud;
	}

	public void setSolicitud_solicitud(int solicitud_solicitud) {
		this.solicitud_solicitud = solicitud_solicitud;
	}

	public int getEstado_idestado() {
		return estado_idestado;
	}

	public void setEstado_idestado(int estado_idestado) {
		this.estado_idestado = estado_idestado;
	}
	
}