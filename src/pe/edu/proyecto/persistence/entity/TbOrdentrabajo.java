package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_ordentrabajo database table.
 * 
 */
@Entity
@Table(name="tb_ordentrabajo")
@NamedQuery(name = "obtenerOrdenesXCliente", query = "SELECT t FROM TbOrdentrabajo t, TbSolicitud s "
		+ "WHERE s.tbEquipo.tbCliente.idCliente = :cliente "
		+ "AND   t.idLiquidacion = -1  "
		+ "AND   t.tbSolicitudIdSolicitud = s.idSolicitud  " + "Order by t.idOtrabajo ;")
public class TbOrdentrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_otrabajo")
	private int idOtrabajo;

	private String comentarios;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_termino")
	private Date fechaTermino;

	@Column(name="tb_solicitud_id_solicitud")
	private int tbSolicitudIdSolicitud;

	private String usuario;

//	//bi-directional many-to-one association to TbOrdenliquidacion
//    @ManyToOne
//	@JoinColumn(name="id_oliquidacion")
//	private TbOrdenliquidacion tbOrdenliquidacion;
//
//	//bi-directional many-to-one association to TbOrdentrabajoHasTbTecnico
//	@OneToMany(mappedBy="tbOrdentrabajo")
//	private Set<TbOrdentrabajoHasTbTecnico> tbOrdentrabajoHasTbTecnicos;
//
//	//bi-directional many-to-one association to TbPaquetesHasTbOrdentrabajo
//	@OneToMany(mappedBy="tbOrdentrabajo")
//	private Set<TbPaquetesHasTbOrdentrabajo> tbPaquetesHasTbOrdentrabajos;
	
	
    public TbOrdentrabajo() {
    }

	public int getIdOtrabajo() {
		return this.idOtrabajo;
	}

	public void setIdOtrabajo(int idOtrabajo) {
		this.idOtrabajo = idOtrabajo;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return this.fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public int getTbSolicitudIdSolicitud() {
		return this.tbSolicitudIdSolicitud;
	}

	public void setTbSolicitudIdSolicitud(int tbSolicitudIdSolicitud) {
		this.tbSolicitudIdSolicitud = tbSolicitudIdSolicitud;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

//	public TbOrdenliquidacion getTbOrdenliquidacion() {
//		return this.tbOrdenliquidacion;
//	}
//
//	public void setTbOrdenliquidacion(TbOrdenliquidacion tbOrdenliquidacion) {
//		this.tbOrdenliquidacion = tbOrdenliquidacion;
//	}
//	
//	public Set<TbOrdentrabajoHasTbTecnico> getTbOrdentrabajoHasTbTecnicos() {
//		return this.tbOrdentrabajoHasTbTecnicos;
//	}
//
//	public void setTbOrdentrabajoHasTbTecnicos(Set<TbOrdentrabajoHasTbTecnico> tbOrdentrabajoHasTbTecnicos) {
//		this.tbOrdentrabajoHasTbTecnicos = tbOrdentrabajoHasTbTecnicos;
//	}
//	
//	public Set<TbPaquetesHasTbOrdentrabajo> getTbPaquetesHasTbOrdentrabajos() {
//		return this.tbPaquetesHasTbOrdentrabajos;
//	}
//
//	public void setTbPaquetesHasTbOrdentrabajos(Set<TbPaquetesHasTbOrdentrabajo> tbPaquetesHasTbOrdentrabajos) {
//		this.tbPaquetesHasTbOrdentrabajos = tbPaquetesHasTbOrdentrabajos;
//	}
	
	
	
	
	@Column(name = "id_oliquidacion", nullable = true)
	private int idLiquidacion;


	public int getIdLiquidacion() {
		return idLiquidacion;
	}

	public void setIdLiquidacion(int idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}
	
	
	
}