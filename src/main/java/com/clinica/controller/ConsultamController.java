package com.clinica.controller;

import com.clinica.model.Archivo;
import com.clinica.model.Consultam;
import com.clinica.services.IArchivoService;
import com.clinica.services.IConsultamService;
import com.clinica.util.ConsultaListaExamenm;
import com.clinica.util.ConsultamResumen;
import com.clinica.util.FiltroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/consultam")
public class ConsultamController {

    @Autowired
    private IConsultamService service;
    @Autowired
    private IArchivoService serviceArchivo;

    // listar a los estandares de la base de datos
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consultam>> listar() {
        List<Consultam> consultas = new ArrayList<>();
        try {
            consultas = service.listar();
        } catch (Exception e) {
            return new ResponseEntity<List<Consultam>>(consultas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Consultam>>(consultas, HttpStatus.OK);

    }

    @GetMapping(value = "/listarpageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Consultam>> listar(Pageable pageable) {
        Page<Consultam> consultass = null;
        try {
            consultass = service.listAllByPage(pageable);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<Page<Consultam>>(consultass, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<Consultam>>(consultass, HttpStatus.OK);
    }

    @GetMapping(value = "/listarResumen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConsultamResumen>> listarResumen() {
        List<ConsultamResumen> consultas = new ArrayList<>();
        try {
            consultas = service.listarResumen();
        } catch (Exception e) {
            return new ResponseEntity<List<ConsultamResumen>>(consultas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ConsultamResumen>>(consultas, HttpStatus.OK);
    }

    // Listar a los estandares de la base de datos por Id
    @GetMapping(value = "/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Consultam> listarId(@PathVariable("id") Integer id) {
        Consultam consulta = new Consultam();
        try {
            consulta = service.listarId(id);
        } catch (Exception e) {
            return new ResponseEntity<Consultam>(consulta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Consultam>(consulta, HttpStatus.OK);
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
    public ResponseEntity<Consultam> registrar(@RequestBody ConsultaListaExamenm consultam) {
        Consultam consulta2 = new Consultam();
        try {
            consulta2 = service.registrar(consultam);
        } catch (Exception e) {

            // si hay error en la base de datos, el error cae en el catch
            return new ResponseEntity<Consultam>(consulta2, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Consultam>(consulta2, HttpStatus.OK);

    }

    @PostMapping(value = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Consultam>> buscar(@RequestBody FiltroConsulta filtro) {
        List<Consultam> consultas = new ArrayList<>();
        if (filtro != null) {
            if (filtro.getFechaConsulta() != null) {
                consultas = service.buscarfecha(filtro);
            } else {
                consultas = service.buscar(filtro);

            }
        }

        // si hay error en la base de datos, el error cae en el catch
        return new ResponseEntity<List<Consultam>>(consultas, HttpStatus.OK);


    }

    // Se actualizaran los Estandares
    @PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> actualizar(@RequestBody Consultam consultam) {

        int resultado = 0;
        try {
            service.modificar(consultam);
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
