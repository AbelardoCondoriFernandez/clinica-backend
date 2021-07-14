package com.clinica.services;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinica.model.Especialidad;


public interface IEspecialidadService {
	int registrar(Especialidad especialidad);
	int modificar(Especialidad especialidad);
	void eliminar(int idEspecialidad);
	Especialidad listarId(int idEspecialidad);
	List<Especialidad>listar();
	Page<Especialidad> listAllByPage(Pageable pageable);

}
