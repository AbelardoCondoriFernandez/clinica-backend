package com.clinica.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.model.Especialidad;
@Repository
public interface IEspecialidadDao extends JpaRepository<Especialidad, Integer>{

}
