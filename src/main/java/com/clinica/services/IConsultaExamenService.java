package com.clinica.services;

import java.util.List;

import com.clinica.model.Consulta;
import com.clinica.model.ConsultaExamen;


public interface IConsultaExamenService {

	void modificar(ConsultaExamen consultaExamen);


	ConsultaExamen listarId(Integer idConsulta, Integer idExamen);

	List<ConsultaExamen> listar();

	List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta) ;

}
