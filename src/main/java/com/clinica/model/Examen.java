package com.clinica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="examen")
public class Examen {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idExamen;
	@Column(name="nombre",nullable=false,length=50)
	private String nombre;
	@Column(name="descripcion",nullable=false,length=150)
	private String descripcion;
	@Column(name="precio",nullable=false,length=50)
	private double precio;

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(int idExamen) {
		this.idExamen = idExamen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
