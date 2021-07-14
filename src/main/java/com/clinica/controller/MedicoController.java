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

import com.clinica.model.Medico;
import com.clinica.services.IMedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private IMedicoService service;
	
	@GetMapping(value="/listar",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>>listar(){
		List<Medico>medicos=new ArrayList<>();
		try {
			medicos=service.listar();
		} catch (Exception e) {
			return new ResponseEntity<List<Medico>>(medicos,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
	} 
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Medico>> listar(Pageable pageable) {
		Page<Medico> medicoss= null;
		try {
			medicoss = service.listAllByPage(pageable);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<Page<Medico>>(medicoss, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Medico>>(medicoss, HttpStatus.OK);
	}
	@GetMapping(value="/listar/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Medico>listarId(@PathVariable("id") Integer id){
		Medico medico=new Medico();
		try {
			medico=service.listarId(id);
		} catch (Exception e) {
			return new ResponseEntity<Medico>(medico,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}
	@PostMapping(value="/registrar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer>registrar(@RequestBody Medico medico){
		int rpta = 0;
		try {
			rpta = service.registrar(medico);
		} catch (Exception e) {

			// si hay error en la base de datos, el error cae en el catch
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);

	}
	//Se actualizaran los  Estandares
	@PutMapping(value="/actualizar", consumes=MediaType.APPLICATION_JSON_VALUE ,produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Medico medico){

		int resultado=0;
		try {
			service.modificar(medico);
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
