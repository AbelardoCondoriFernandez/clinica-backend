package com.clinica.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPaciente;
	
	@Column(name="nombres",nullable=false,length=50)
	private String nombres;
	@Column(name="apellidos",nullable=false,length=50)	
	private String apellidos;

	@Column(name="dni",nullable=false,length=8)
	private String dni;
	@Column(name="direccion",nullable=false,length=150)
	private String direccion;
	@Column(name="telefono",nullable=false,length=9)
	private String telefono;
	@Column(name="email",nullable=false,length=50)
	private String email;
	@JsonSerialize(using= ToStringSerializer.class)
	private LocalDateTime fechadenacimiento;
	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getFechadenacimiento() {
		return fechadenacimiento;
	}

	public void setFechadenacimiento(LocalDateTime fechadenacimiento) {
		this.fechadenacimiento = fechadenacimiento;
	}
}
