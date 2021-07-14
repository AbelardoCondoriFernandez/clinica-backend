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

import com.clinica.model.Examen;
import com.clinica.services.IExamenService;

@RestController
@RequestMapping("/examen")
public class ExamenController {

	@Autowired
	private IExamenService service;
	
	@GetMapping(value="/listar",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Examen>>listar(){
		List<Examen>examens=new ArrayList<>();
		try {
			examens=service.listar();
		} catch (Exception e) {
			return new ResponseEntity<List<Examen>>(examens,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Examen>>(examens, HttpStatus.OK);
	} 
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Examen>> listar(Pageable pageable) {
		Page<Examen> examenes= null;
		try {
			examenes = service.listAllByPage(pageable);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Page<Examen>>(examenes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Examen>>(examenes, HttpStatus.OK);
	}
	@GetMapping(value="/listar/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Examen>listarId(@PathVariable("id") Integer id){
		Examen examen=new Examen();
		try {
			examen=service.listarId(id);
		} catch (Exception e) {
			return new ResponseEntity<Examen>(examen,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Examen>(examen, HttpStatus.OK);
	}
	@PostMapping(value="/registrar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer>registrar(@RequestBody Examen examen){
		int rpta = 0;
		try {
			rpta = service.registrar(examen);
		} catch (Exception e) {

			// si hay error en la base de datos, el error cae en el catch
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);

	}
	//Se actualizaran los  Estandares
	@PutMapping(value="/actualizar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Examen examen){

		int resultado=0;
		try {
			service.modificar(examen);
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
