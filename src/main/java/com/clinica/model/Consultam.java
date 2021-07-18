package com.clinica.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="consultam")
public class Consultam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConsultam;

	/*
	 * persistencia de datos, sintaxis que se hara una notacion de del objeto
	 * estandar por ejemplo ==>consulta.estandar que se dara por consultas
	 * personalizadas
	 */
	@JsonSerialize(using=ToStringSerializer.class) 
	private LocalDateTime fecha;


	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false)
	private Medico medico ;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente ;
	@ManyToOne
	@JoinColumn(name = "id_especialidad", nullable = false)
	private Especialidad especialidad;
	
	public int getIdConsultam() {
		return idConsultam;
	}
	public void setIdConsultam(int idConsultam) {
		this.idConsultam = idConsultam;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
}
