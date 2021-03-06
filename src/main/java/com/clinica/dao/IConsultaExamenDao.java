package com.clinica.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clinica.model.ConsultaExamen;


@Repository
public interface IConsultaExamenDao extends JpaRepository<ConsultaExamen, Integer> {

	@Modifying
	@Query(value = "insert into consulta_examen(id_consulta,id_examen)values( :idConsulta,:idExamen)", nativeQuery = true)
	int registrar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen);

	@Query("from ConsultaExamen ce where ce.consulta.idConsulta=:idConsulta")
	List<ConsultaExamen>listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);
	@Query("from ConsultaExamen ce where ce.consulta.idConsulta=:idConsulta and ce.examen.idExamen=:idExamen")
	ConsultaExamen listarId(@Param("idConsulta") Integer idconsulta,@Param("idExamen") Integer idexamen);
}
