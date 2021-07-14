package com.clinica.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinica.dao.IMedicoDao;
import com.clinica.model.Medico;
import com.clinica.services.IMedicoService;


@Service
public class MedicoServiceImpl implements IMedicoService{

	@Autowired
	private IMedicoDao dao;

	

	@Override
	public int registrar(Medico medico) {
		int rpta = 0;
		rpta = dao.save(medico) != null ? medico.getIdMedico() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public int modificar(Medico medico) {
		int rpta = 0;
		rpta = dao.save(medico) != null ? medico.getIdMedico() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(int idMedico) {

		dao.delete(idMedico);
	}

	@Override
	public Medico listarId(int idMedico) {
		return dao.findOne(idMedico);
	}

	@Override
	public List<Medico> listar() {
		return dao.findAll();
	}
	@Override
	public Page<Medico> listAllByPage(Pageable pageable) {

		return dao.findAll(pageable);
	}
}
