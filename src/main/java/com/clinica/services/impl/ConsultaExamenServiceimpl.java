package com.clinica.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.dao.IConsultaExamenDao;
import com.clinica.model.ConsultaExamen;
import com.clinica.services.IConsultaExamenService;

@Service
public class ConsultaExamenServiceimpl implements IConsultaExamenService{


	@Autowired 
	private IConsultaExamenDao dao; 
	
	@Override
	public void modificar(ConsultaExamen consultaExamen) {

		dao.save(consultaExamen);
	}


	@Override
	public ConsultaExamen listarId(Integer idConsulta,Integer idExamen) {
		// TODO Auto-generated method stub
		return dao.listarId(idConsulta,idExamen);
	}

	@Override
	public List<ConsultaExamen> listar() {
		return dao.findAll();
		
	}


	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta) {
		return dao.listarExamenesPorConsulta(idconsulta);
	}

}
