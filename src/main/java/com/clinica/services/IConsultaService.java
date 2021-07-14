package com.clinica.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinica.model.Consulta;
import com.clinica.util.ConsultaListaExamen;
import com.clinica.util.ConsultaResumen;
import com.clinica.util.FiltroConsulta;


public interface IConsultaService {
	Consulta registrar(ConsultaListaExamen consulta);

	void modificar(Consulta consulta);

	void eliminar(int idConsulta);

	Consulta listarId(int idConsulta);

	List<Consulta> listar();

	Page<Consulta> listAllByPage(Pageable pageable);
	List<Consulta> buscar(FiltroConsulta filtro);
	List<Consulta> buscarfecha(FiltroConsulta filtro);

	List<ConsultaResumen> listarResumen();
	byte[] generarReporte()throws Exception;
}
