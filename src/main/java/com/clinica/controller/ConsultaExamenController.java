package com.clinica.controller;
import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.ConsultaExamen;
import com.clinica.services.IConsultaExamenService;



@RestController
@RequestMapping("/consultaexamen")
public class ConsultaExamenController {

	@Autowired
	private IConsultaExamenService service;

	// listar a los estandares de la base de datos
	@GetMapping(value = "/listar/{idConsulta}/{idExamen}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsultaExamen> listar(@PathVariable("idConsulta") Integer idconsulta,@PathVariable("idExamen") Integer idexamen) {
		ConsultaExamen consultasexamen = new ConsultaExamen();
		
			consultasexamen = service.listarId(idconsulta,idexamen);
		
			return new ResponseEntity<ConsultaExamen>(consultasexamen, HttpStatus.OK);
		}

	



	// Listar a los estandares de la base de datos por Id
	@GetMapping(value = "/listar/{idConsulta}", produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<List<ConsultaExamen>> listar(@PathVariable("idConsulta") Integer idconsulta) {
		List<ConsultaExamen>consultasexamen = new ArrayList<>();
			consultasexamen = service.listarExamenesPorConsulta(idconsulta);
		
		
		return new ResponseEntity<List<ConsultaExamen>>(consultasexamen, HttpStatus.OK);
	}




}
