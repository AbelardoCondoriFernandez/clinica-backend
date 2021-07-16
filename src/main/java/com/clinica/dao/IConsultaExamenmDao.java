package com.clinica.dao;


import com.clinica.model.ConsultaExamenm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IConsultaExamenmDao extends JpaRepository<ConsultaExamenm, Integer> {

	@Modifying
	@Query(value = "insert into consulta_examenm(id_consultam,id_examen)values( :idConsultam,:idExamen)", nativeQuery = true)
	int registrar(@Param("idConsultam") Integer idConsultam, @Param("idExamen") Integer idExamen);

	@Query("from ConsultaExamenm ce where ce.consultam.idConsultam=:idConsultam")
	List<ConsultaExamenm>listarExamenesPorConsultas(@Param("idConsultam") Integer idconsultam);
	@Query("from ConsultaExamenm ce where ce.consultam.idConsultam=:idConsultam and ce.examen.idExamen=:idExamen")
	ConsultaExamenm listarId(@Param("idConsultam") Integer idconsultam, @Param("idExamen") Integer idexamen);
}
