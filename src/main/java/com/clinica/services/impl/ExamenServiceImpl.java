package com.clinica.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinica.dao.IExamenDao;
import com.clinica.model.Examen;
import com.clinica.services.IExamenService;

@Service
public class ExamenServiceImpl implements IExamenService{

	@Autowired
	private IExamenDao dao;

	

	@Override
	public int registrar(Examen examen) {
		int rpta = 0;
		rpta = dao.save(examen) != null ? examen.getIdExamen() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public int modificar(Examen examen) {
		int rpta = 0;
		rpta = dao.save(examen) != null ? examen.getIdExamen() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(int idExamen) {

		dao.delete(idExamen);
	}

	@Override
	public Examen listarId(int idExamen) {
		return dao.findOne(idExamen);
	}

	@Override
	public List<Examen> listar() {
		return dao.findAll();
	}
	@Override
	public Page<Examen> listAllByPage(Pageable pageable) {

		return dao.findAll(pageable);
	}
}
