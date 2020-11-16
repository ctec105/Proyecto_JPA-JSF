package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_equipohorometro database table.
 * 
 */
@Entity
@Table(name="tb_equipohorometro")
public class TbEquipohorometro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_horometro")
	private int idHorometro;

    @Temporal( TemporalType.DATE)
	private Date fecha;

	private int horas;

	//bi-directional many-to-one association to TbEquipo
    @ManyToOne
	@JoinColumn(name="tb_equipo_nrotarjeta")
	private TbEquipo tbEquipo;

    public TbEquipohorometro() {
    }

	public int getIdHorometro() {
		return this.idHorometro;
	}

	public void setIdHorometro(int idHorometro) {
		this.idHorometro = idHorometro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHoras() {
		return this.horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public TbEquipo getTbEquipo() {
		return this.tbEquipo;
	}

	public void setTbEquipo(TbEquipo tbEquipo) {
		this.tbEquipo = tbEquipo;
	}
	
}