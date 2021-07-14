package com.clinica.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinica.model.Paciente;

public interface IPacienteService {
	int registrar(Paciente paciente);
	int modificar(Paciente paciente);
	void eliminar(int idPaciente);
	Paciente listarId(int idPaciente);
	List<Paciente>listar();
	Page<Paciente> listAllByPage(Pageable pageable);

}
