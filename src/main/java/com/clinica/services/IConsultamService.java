package com.clinica.services;

import com.clinica.model.Consultam;
import com.clinica.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IConsultamService {
	Consultam registrar(ConsultaListaExamenm consultam);

	void modificar(Consultam consultam);

	void eliminar(int idConsultam);

	Consultam listarId(int idConsultam);

	List<Consultam> listar();

	Page<Consultam> listAllByPage(Pageable pageable);
	List<Consultam> buscar(FiltroConsulta filtro);
	List<Consultam> buscarfecha(FiltroConsulta filtro);

	List<ConsultamResumen> listarResumen();
	byte[] generarReporte()throws Exception;
}
