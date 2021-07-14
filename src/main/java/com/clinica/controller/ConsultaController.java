package com.clinica.controller;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import com.clinica.model.Archivo;
import com.clinica.services.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinica.model.Consulta;
import com.clinica.services.IConsultaService;
import com.clinica.util.ConsultaListaExamen;
import com.clinica.util.ConsultaResumen;
import com.clinica.util.FiltroConsulta;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService service;
    @Autowired
    private IArchivoService serviceArchivo;

    // listar a los estandares de la base de datos
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> consultas = new ArrayList<>();
        try {
            consultas = service.listar();
        } catch (Exception e) {
            return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);

    }

    @GetMapping(value = "/listarpageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Consulta>> listar(Pageable pageable) {
        Page<Consulta> consultass = null;
        try {
            consultass = service.listAllByPage(pageable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Page<Consulta>>(consultass, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<Consulta>>(consultass, HttpStatus.OK);
    }

    @GetMapping(value = "/listarResumen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsultaResumen>> listarResumen() {
        List<ConsultaResumen> consultas = new ArrayList<>();
        try {
            consultas = service.listarResumen();
        } catch (Exception e) {
            return new ResponseEntity<List<ConsultaResumen>>(consultas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ConsultaResumen>>(consultas, HttpStatus.OK);
    }

    // Listar a los estandares de la base de datos por Id
    @GetMapping(value = "/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> listarId(@PathVariable("id") Integer id) {
        Consulta consulta = new Consulta();
        try {
            consulta = service.listarId(id);
        } catch (Exception e) {
            return new ResponseEntity<Consulta>(consulta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
    }

    @GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporte() {

        byte[] data = null;
        try {
            data = service.generarReporte();
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/guardarArchivo")
    public ResponseEntity<Integer> guardarArchivo(@RequestParam("file") MultipartFile file) throws IOException {
        int rpta = 0;
        Archivo ar = new Archivo();
        ar.setValue(file.getBytes());
        rpta = serviceArchivo.guardar(ar);
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    @GetMapping(value = "/leerArchivo/{idArchivo}",produces =MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]>leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException{
    	byte[] arr=serviceArchivo.leerArchivo(idArchivo);
    	return new ResponseEntity<byte[]>(arr,HttpStatus.OK);
	}
    // Registro por el tipo de objeto Estandar
    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consulta> registrar(@RequestBody ConsultaListaExamen consulta) {
        Consulta consulta2 = new Consulta();
        try {
            consulta2 = service.registrar(consulta);
        } catch (Exception e) {

            // si hay error en la base de datos, el error cae en el catch
            return new ResponseEntity<Consulta>(consulta2, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Consulta>(consulta2, HttpStatus.OK);

    }

    @PostMapping(value = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consulta>> buscar(@RequestBody FiltroConsulta filtro) {
        List<Consulta> consultas = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getFechaConsulta() != null) {
                consultas = service.buscarfecha(filtro);
            } else {
                consultas = service.buscar(filtro);

            }
        }

        // si hay error en la base de datos, el error cae en el catch
        return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);


    }

    // Se actualizaran los Estandares
    @PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> actualizar(@RequestBody Consulta consulta) {

        int resultado = 0;
        try {
            service.modificar(consulta);
            resultado = 1;
        } catch (Exception e) {
            resultado = 0;
        }

        return new ResponseEntity<Integer>(resultado, HttpStatus.OK);

    }

    // entidad que eliminara por la su ID
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> eliminar(@PathVariable Integer id) {

        int resultado = 0;
        try {
            service.eliminar(id);
            resultado = 1;
        } catch (Exception e) {
            resultado = 0;
        }

        return new ResponseEntity<Integer>(resultado, HttpStatus.OK);

    }
}
