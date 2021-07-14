package com.clinica.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinica.model.Examen;


public interface IExamenService {
	int registrar(Examen examen);
	int modificar(Examen examen);
	void eliminar(int idExamen);
	Examen listarId(int idExamen);
	List<Examen>listar();
	Page<Examen> listAllByPage(Pageable pageable);

}
