package com.clinica.services;

import com.clinica.model.ConsultaExamenm;

import java.util.List;


public interface IConsultaExamenmService {

	void modificar(ConsultaExamenm consultaExamenm);


	ConsultaExamenm listarId(Integer idConsultam, Integer idExamen);

	List<ConsultaExamenm> listar();

	List<ConsultaExamenm> listarExamenesPorConsulta(Integer idConsultam) ;

}
