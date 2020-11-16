package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the tb_solicitud database table.
 * 
 */
@Entity
@Table(name="tb_solicitud")
public class TbSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud")
	private Integer idSolicitud;

	@Column(name="des_problema")
	private String desProblema;

	private String estado;

    @Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="horometro_actual")
	private int horometroActual;

	private String representante;

	private String telefono;

	//bi-directional many-to-one association to TbEquipo
    @ManyToOne
	@JoinColumn(name="tb_equipo_nrotarjeta")
	private TbEquipo tbEquipo;

//	//bi-directional many-to-one association to TbSolicitudHasTbEstado
//	@OneToMany(mappedBy="tbSolicitud")
//	private Set<TbSolicitudHasTbEstado> tbSolicitudHasTbEstados;

    public TbSolicitud() {
    	tbEquipo = new TbEquipo();
    }

	public Integer getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getDesProblema() {
		return this.desProblema;
	}

	public void setDesProblema(String desProblema) {
		this.desProblema = desProblema;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHorometroActual() {
		return this.horometroActual;
	}

	public void setHorometroActual(int horometroActual) {
		this.horometroActual = horometroActual;
	}

	public String getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TbEquipo getTbEquipo() {
		return this.tbEquipo;
	}

	public void setTbEquipo(TbEquipo tbEquipo) {
		this.tbEquipo = tbEquipo;
	}

	@Override
	public String toString() {
		return "TbSolicitud [idSolicitud=" + idSolicitud + ", desProblema=" + desProblema + ", estado=" + estado
				+ ", fecha=" + fecha + ", horometroActual=" + horometroActual + ", representante=" + representante
				+ ", telefono=" + telefono + ", tbEquipo=" + tbEquipo.getNrotarjeta() + "]";
	}
	
//	public Set<TbSolicitudHasTbEstado> getTbSolicitudHasTbEstados() {
//		return this.tbSolicitudHasTbEstados;
//	}
//
//	public void setTbSolicitudHasTbEstados(Set<TbSolicitudHasTbEstado> tbSolicitudHasTbEstados) {
//		this.tbSolicitudHasTbEstados = tbSolicitudHasTbEstados;
//	}
	
	
}