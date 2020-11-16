package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_ordenliquidacion database table.
 * 
 */
@Entity
@Table(name="tb_ordenliquidacion")
public class TbOrdenliquidacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_liquidacion")
	private int idLiquidacion;

	private int estado;

    @Temporal( TemporalType.DATE)
	private Date fecha;

	@Column(name="id_cliente")
	private String idCliente;

	private String nombre;

	private String usuario;
	
//	@OneToOne(mappedBy="liquidacion")
//    private TbPrefactura prefactura; 
	

//	//bi-directional many-to-one association to TbOrdentrabajo
//	@OneToMany(mappedBy="tbOrdenliquidacion")
//	private Set<TbOrdentrabajo> tbOrdentrabajos;

//    public TbPrefactura getPrefactura() {
//		return prefactura;
//	}
//
//	public void setPrefactura(TbPrefactura prefactura) {
//		this.prefactura = prefactura;
//	}

	public TbOrdenliquidacion() {
    }

	public int getIdLiquidacion() {
		return this.idLiquidacion;
	}

	public void setIdLiquidacion(int idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

//	public Set<TbOrdentrabajo> getTbOrdentrabajos() {
//		return this.tbOrdentrabajos;
//	}
//
//	public void setTbOrdentrabajos(Set<TbOrdentrabajo> tbOrdentrabajos) {
//		this.tbOrdentrabajos = tbOrdentrabajos;
//	}
	
}