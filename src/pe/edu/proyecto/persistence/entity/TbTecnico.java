package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the tb_tecnicos database table.
 * 
 */
@Entity
@Table(name="tb_tecnicos")
public class TbTecnico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tecnicos")
	private int idTecnicos;

	@Column(name="ape_mat")
	private String apeMat;

	@Column(name="ape_pat")
	private String apePat;

	private String disponible;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private String nombre;

	//bi-directional many-to-one association to TbEspecialidade
    @ManyToOne
	@JoinColumn(name="tb_especialidades_id_especialidad")
	private TbEspecialidade tbEspecialidade;

	//bi-directional many-to-one association to TbTecnico
    @ManyToOne
	@JoinColumn(name="supervisor")
	private TbTecnico tbTecnico;

	//bi-directional many-to-one association to TbTecnico
	@OneToMany(mappedBy="tbTecnico")
	private Set<TbTecnico> tbTecnicos;

    public TbTecnico() {
    	tbEspecialidade = new TbEspecialidade();
    }

	public int getIdTecnicos() {
		return this.idTecnicos;
	}

	public void setIdTecnicos(int idTecnicos) {
		this.idTecnicos = idTecnicos;
	}

	public String getApeMat() {
		return this.apeMat;
	}

	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}

	public String getApePat() {
		return this.apePat;
	}

	public void setApePat(String apePat) {
		this.apePat = apePat;
	}

	public String getDisponible() {
		return this.disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TbEspecialidade getTbEspecialidade() {
		return this.tbEspecialidade;
	}

	public void setTbEspecialidade(TbEspecialidade tbEspecialidade) {
		this.tbEspecialidade = tbEspecialidade;
	}
	
	public TbTecnico getTbTecnico() {
		return this.tbTecnico;
	}

	public void setTbTecnico(TbTecnico tbTecnico) {
		this.tbTecnico = tbTecnico;
	}
	
	public Set<TbTecnico> getTbTecnicos() {
		return this.tbTecnicos;
	}

	public void setTbTecnicos(Set<TbTecnico> tbTecnicos) {
		this.tbTecnicos = tbTecnicos;
	}
	
}