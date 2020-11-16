package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_equipo database table.
 * 
 */
@Entity
@Table(name="tb_equipo")
public class TbEquipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nrotarjeta;

	private String nroserie;

	//bi-directional many-to-one association to TbClaseequipo
    @ManyToOne
	@JoinColumn(name="tb_claseEquipo_id_claseEquipo")
	private TbClaseequipo tbClaseequipo;

	//bi-directional many-to-one association to TbCliente
    @ManyToOne
	@JoinColumn(name="tb_cliente_id_cliente")
	private TbCliente tbCliente;

	//bi-directional many-to-one association to TbModelo
    @ManyToOne
	@JoinColumn(name="tb_modelo_id_modelo")
	private TbModelo tbModelo;

	//bi-directional many-to-one association to TbEquipohorometro
	@OneToMany(mappedBy="tbEquipo")
	private Set<TbEquipohorometro> tbEquipohorometros;

	//bi-directional many-to-one association to TbSolicitud
	@OneToMany(mappedBy="tbEquipo")
	private Set<TbSolicitud> tbSolicituds;

    public TbEquipo() {
//    	tbCliente=new TbCliente();
    	tbClaseequipo = new TbClaseequipo();
    }

	public String getNrotarjeta() {
		return this.nrotarjeta;
	}

	public void setNrotarjeta(String nrotarjeta) {
		this.nrotarjeta = nrotarjeta;
	}

	public String getNroserie() {
		return this.nroserie;
	}

	public void setNroserie(String nroserie) {
		this.nroserie = nroserie;
	}

	public TbClaseequipo getTbClaseequipo() {
		return this.tbClaseequipo;
	}

	public void setTbClaseequipo(TbClaseequipo tbClaseequipo) {
		this.tbClaseequipo = tbClaseequipo;
	}
	
	public TbCliente getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(TbCliente tbCliente) {
		this.tbCliente = tbCliente;
	}
	
	public TbModelo getTbModelo() {
		return this.tbModelo;
	}

	public void setTbModelo(TbModelo tbModelo) {
		this.tbModelo = tbModelo;
	}
	
	public Set<TbEquipohorometro> getTbEquipohorometros() {
		return this.tbEquipohorometros;
	}

	public void setTbEquipohorometros(Set<TbEquipohorometro> tbEquipohorometros) {
		this.tbEquipohorometros = tbEquipohorometros;
	}
	
	public Set<TbSolicitud> getTbSolicituds() {
		return this.tbSolicituds;
	}

	public void setTbSolicituds(Set<TbSolicitud> tbSolicituds) {
		this.tbSolicituds = tbSolicituds;
	}
	
}