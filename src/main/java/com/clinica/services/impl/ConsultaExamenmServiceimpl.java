package com.clinica.services.impl;


import com.clinica.dao.IConsultaExamenmDao;
import com.clinica.model.ConsultaExamenm;
import com.clinica.services.IConsultaExamenmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaExamenmServiceimpl implements IConsultaExamenmService {


	@Autowired 
	private IConsultaExamenmDao dao;
	
	@Override
	public void modificar(ConsultaExamenm consultaExamenm) {

		dao.save(consultaExamenm);
	}


	@Override
	public ConsultaExamenm listarId(Integer idConsultam,Integer idExamen) {
		// TODO Auto-generated method stub
		return dao.listarId(idConsultam,idExamen);
	}

	@Override
	public List<ConsultaExamenm> listar() {
		return dao.findAll();
		
	}


	@Override
	public List<ConsultaExamenm> listarExamenesPorConsulta(Integer idconsultam) {
		return dao.listarExamenesPorConsultas(idconsultam);
	}

}
