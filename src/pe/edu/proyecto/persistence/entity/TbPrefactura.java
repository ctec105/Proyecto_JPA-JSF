package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_prefactura database table.
 * 
 */
@Entity
@Table(name="tb_prefactura")
public class TbPrefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prefactura")
	private int idPrefactura;

	private String cliente;

	private String direccion;

    @Temporal( TemporalType.DATE)
	private Date fecha;

	private String ruc;

	private int tb_ordenLiquidacion_id_liquidacion;
	
	private String usuario;

	//bi-directional many-to-one association to TbDetalleprefactura
//	@OneToMany(mappedBy="tbPrefactura")
//	private Set<TbDetalleprefactura> tbDetalleprefacturas;

    public TbPrefactura() {
    }

	public int getIdPrefactura() {
		return this.idPrefactura;
	}

	public void setIdPrefactura(int idPrefactura) {
		this.idPrefactura = idPrefactura;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public int getTb_ordenLiquidacion_id_liquidacion() {
		return this.tb_ordenLiquidacion_id_liquidacion;
	}

	public void setTb_ordenLiquidacion_id_liquidacion(int tb_ordenLiquidacion_id_liquidacion) {
		this.tb_ordenLiquidacion_id_liquidacion = tb_ordenLiquidacion_id_liquidacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

//	public Set<TbDetalleprefactura> getTbDetalleprefacturas() {
//		return this.tbDetalleprefacturas;
//	}
//
//	public void setTbDetalleprefacturas(Set<TbDetalleprefactura> tbDetalleprefacturas) {
//		this.tbDetalleprefacturas = tbDetalleprefacturas;
//	}
	
	
}