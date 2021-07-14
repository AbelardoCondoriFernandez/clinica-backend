package com.clinica.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinica.dao.IPacienteDao;
import com.clinica.model.Paciente;
import com.clinica.services.IPacienteService;
@Service
public class PacienteServiceImpl implements IPacienteService{

	private final IPacienteDao dao;

	public PacienteServiceImpl(IPacienteDao dao) {
		this.dao = dao;
	}


	@Override
	public int registrar(Paciente paciente) {
		int rpta = 0;
		rpta = dao.save(paciente) != null ? paciente.getIdPaciente() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public int modificar(Paciente paciente) {
		int rpta = 0;
		rpta = dao.save(paciente) != null ? paciente.getIdPaciente() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(int idPaciente) {

		dao.delete(idPaciente);
	}

	@Override
	public Paciente listarId(int idPaciente) {
		return dao.findOne(idPaciente);
	}

	@Override
	public List<Paciente> listar() {
		return dao.findAll();
	}
	@Override
	public Page<Paciente> listAllByPage(Pageable pageable) {

		return dao.findAll(pageable);
	}
}
