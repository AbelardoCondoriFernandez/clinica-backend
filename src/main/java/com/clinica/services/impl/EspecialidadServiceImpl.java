package com.clinica.services.impl;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinica.dao.IEspecialidadDao;
import com.clinica.model.Especialidad;
import com.clinica.services.IEspecialidadService;


@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired
	private IEspecialidadDao dao;

	@Override
	public int registrar(Especialidad especialidad) {
		int rpta = 0;
		rpta = dao.save(especialidad) != null ? especialidad.getIdEspecialidad() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public int modificar(Especialidad especialidad) {
		int rpta = 0;
		rpta = dao.save(especialidad) != null ? especialidad.getIdEspecialidad() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(int idEspecialidad) {

		dao.delete(idEspecialidad);
	}

	@Override
	public Especialidad listarId(int idEspecialidad) {
		return dao.findOne(idEspecialidad);
	}

	@Override
	public List<Especialidad> listar() {
		return dao.findAll();
	}
	@Override
	public Page<Especialidad> listAllByPage(Pageable pageable) {

		return dao.findAll(pageable);
	}
}
