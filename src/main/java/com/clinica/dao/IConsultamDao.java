package com.clinica.dao;

import com.clinica.model.Consultam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IConsultamDao extends JpaRepository<Consultam, Integer> {

	@Query("from Consulta c where c.paciente.dni=:dni or LOWER(c.paciente.nombres) like %:nombreCompleto% or LOWER(c.paciente.apellidos) like %:nombreCompleto%")
	List<Consultam>buscar(@Param("dni") String dni, @Param("nombreCompleto") String nombreCompleto);

	@Query("from Consultam c where c.fecha between :fechaConsulta and :fechaSgte")
	List<Consultam> buscarPorFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSgte);

	@Query(value="select * from fn_listarResumen(:valor)",nativeQuery=true)
	List<Object[]> listarResumen(@Param("valor") Integer valor);

}
