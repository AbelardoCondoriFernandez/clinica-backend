package com.clinica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="detalle_consulta")
public class DetalleConsulta {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	@JsonIgnore
	@ManyToOne
	// nullable false es para que se inserta un estandar cuando halla una insercion
	@JoinColumn(name = "id_consulta", nullable = false)
	private Consulta consulta;

	@Column(name="diagnostico", nullable = false, length = 300)
	private String diagnostico;
	@Column(name = "tratamiento", nullable = false, length = 100)
	private String tratamiento;
	@Column(name="cantidad", nullable = false, length = 300)
	private String cantidad;
	@Column(name = "recetamedica", nullable = false, length = 100)
	private String recetamedica;
	@Column(name = "veces", nullable = false, length = 100)
	private String veces;
	@JsonSerialize(using= ToStringSerializer.class)
	private LocalDate fechainicio;
	@JsonSerialize(using=ToStringSerializer.class)
	private LocalDate fechafin;

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getRecetamedica() {
		return recetamedica;
	}

	public void setRecetamedica(String recetamedica) {
		this.recetamedica = recetamedica;
	}

	public String getVeces() {
		return veces;
	}

	public void setVeces(String veces) {
		this.veces = veces;
	}

	public LocalDate getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(LocalDate fechainicio) {
		this.fechainicio = fechainicio;
	}

	public LocalDate getFechafin() {
		return fechafin;
	}

	public void setFechafin(LocalDate fechafin) {
		this.fechafin = fechafin;
	}

	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
}
