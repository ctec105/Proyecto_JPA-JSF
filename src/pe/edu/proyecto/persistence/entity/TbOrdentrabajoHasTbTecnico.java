package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_ordentrabajo_has_tb_tecnicos database table.
 * 
 */
@Entity
@Table(name="tb_ordentrabajo_has_tb_tecnicos")
public class TbOrdentrabajoHasTbTecnico implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TbOrdentrabajoHasTbTecnicoPK id;

	private int dias;

//	//bi-directional many-to-one association to TbOrdentrabajo
//    @ManyToOne
//	@JoinColumn(name="tb_ordenTrabajo_id_otrabajo")
//	private TbOrdentrabajo tbOrdentrabajo;
//
//	//bi-directional many-to-one association to TbTecnico
//    @ManyToOne
//	@JoinColumn(name="tb_tecnicos_id_tecnicos")
//	private TbTecnico tbTecnico;

    public TbOrdentrabajoHasTbTecnico() {
    }

//	public TbOrdentrabajoHasTbTecnicoPK getId() {
//		return this.id;
//	}
//
//	public void setId(TbOrdentrabajoHasTbTecnicoPK id) {
//		this.id = id;
//	}
	
	public int getDias() {
		return this.dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

//	public TbOrdentrabajo getTbOrdentrabajo() {
//		return this.tbOrdentrabajo;
//	}
//
//	public void setTbOrdentrabajo(TbOrdentrabajo tbOrdentrabajo) {
//		this.tbOrdentrabajo = tbOrdentrabajo;
//	}
//	
//	public TbTecnico getTbTecnico() {
//		return this.tbTecnico;
//	}
//
//	public void setTbTecnico(TbTecnico tbTecnico) {
//		this.tbTecnico = tbTecnico;
//	}
	
	
	
	
	
	@Id
	@Column(name = "tb_ordenTrabajo_id_otrabajo")
	private int ordenTrabajo_otrabajo;

	@Id
	@Column(name = "tb_tecnicos_id_tecnicos")
	private int tecnicos_tecnicos;

	public int getOrdenTrabajo_otrabajo() {
		return ordenTrabajo_otrabajo;
	}

	public void setOrdenTrabajo_otrabajo(int ordenTrabajo_otrabajo) {
		this.ordenTrabajo_otrabajo = ordenTrabajo_otrabajo;
	}

	public int getTecnicos_tecnicos() {
		return tecnicos_tecnicos;
	}

	public void setTecnicos_tecnicos(int tecnicos_tecnicos) {
		this.tecnicos_tecnicos = tecnicos_tecnicos;
	}
	
	
	
}