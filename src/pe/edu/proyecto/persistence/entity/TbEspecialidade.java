package pe.edu.proyecto.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the tb_especialidades database table.
 * 
 */
@Entity
@Table(name="tb_especialidades")
public class TbEspecialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_especialidad")
	private int idEspecialidad;

	private double costoDia;

	private String descripcion;

	//bi-directional many-to-one association to TbTecnico
	@OneToMany(mappedBy="tbEspecialidade")
	private Set<TbTecnico> tbTecnicos;

    public TbEspecialidade() {
    }

	public int getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public double getCostoDia() {
		return this.costoDia;
	}

	public void setCostoDia(double costoDia) {
		this.costoDia = costoDia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<TbTecnico> getTbTecnicos() {
		return this.tbTecnicos;
	}

	public void setTbTecnicos(Set<TbTecnico> tbTecnicos) {
		this.tbTecnicos = tbTecnicos;
	}
	
}