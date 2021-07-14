package com.clinica.controller;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Especialidad;
import com.clinica.services.IEspecialidadService;


@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;
	
	@GetMapping(value="/listar",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Especialidad>>listar(){
		List<Especialidad>especialidads=new ArrayList<>();
		try {
			especialidads=service.listar();
		} catch (Exception e) {
			return new ResponseEntity<List<Especialidad>>(especialidads,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Especialidad>>(especialidads, HttpStatus.OK);
	} 
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Especialidad>> listar(Pageable pageable) {
		Page<Especialidad> especialidades= null;
		try {
			especialidades = service.listAllByPage(pageable);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Page<Especialidad>>(especialidades, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Especialidad>>(especialidades, HttpStatus.OK);
	}
	@GetMapping(value="/listar/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Especialidad>listarId(@PathVariable("id") Integer id){
		Especialidad especialidad=new Especialidad();
		try {
			especialidad=service.listarId(id);
		} catch (Exception e) {
			return new ResponseEntity<Especialidad>(especialidad,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}
	@PostMapping(value="/registrar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer>registrar(@RequestBody Especialidad especialidad){
		int rpta = 0;
		try {
			rpta = service.registrar(especialidad);
		} catch (Exception e) {

			// si hay error en la base de datos, el error cae en el catch
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);

	}
	//Se actualizaran los  Estandares
	@PutMapping(value="/actualizar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Especialidad especialidad){

		int resultado=0;
		try {
			service.modificar(especialidad);
			resultado=1;
		}
		catch (Exception e) {
		resultado=0;	
		}
		
		return new ResponseEntity<Integer>(resultado,HttpStatus.OK);
		
	}	
	//entidad que eliminara por la su ID
	@DeleteMapping(value="/eliminar/{id}" ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(@PathVariable Integer id){

		int resultado=0;
		try {
			service.eliminar(id);
			resultado=1;
		}
		catch (Exception e) {
		resultado=0;	
		}
		
		return new ResponseEntity<Integer>(resultado,HttpStatus.OK);
		
	}		
}
