package com.clinica.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinica.model.Medico;


public interface IMedicoService {
	int registrar(Medico medico);
	int modificar(Medico medico);
	void eliminar(int idMedico);
	Medico listarId(int idMedico);
	List<Medico>listar();
	Page<Medico> listAllByPage(Pageable pageable);

}
