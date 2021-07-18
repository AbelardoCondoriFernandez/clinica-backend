package com.clinica.controller;

import com.clinica.model.ConsultaExamenm;
import com.clinica.services.IConsultaExamenmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/consultaexamenm")
public class ConsultaExamenmController {

	@Autowired
	private IConsultaExamenmService service;

	// listar a los estandares de la base de datos
	@GetMapping(value = "/listar/{idConsultam}/{idExamen}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsultaExamenm> listar(@PathVariable("idConsultam") Integer idconsultam, @PathVariable("idExamen") Integer idexamen) {
		ConsultaExamenm consultasexamenm = new ConsultaExamenm();
		
			consultasexamenm = service.listarId(idconsultam,idexamen);
		
			return new ResponseEntity<ConsultaExamenm>(consultasexamenm, HttpStatus.OK);
		}

	



	// Listar a los estandares de la base de datos por Id
	@GetMapping(value = "/listar/{idConsultam}", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<ConsultaExamenm>> listar(@PathVariable("idConsultam") Integer idconsultam) {
		List<ConsultaExamenm>consultasexamenm = new ArrayList<>();
			consultasexamenm = service.listarExamenesPorConsulta(idconsultam);
		
		
		return new ResponseEntity<List<ConsultaExamenm>>(consultasexamenm, HttpStatus.OK);
	}




}
