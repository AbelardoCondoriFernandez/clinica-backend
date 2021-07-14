package com.clinica.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.model.Examen;
@Repository
public interface IExamenDao extends JpaRepository<Examen, Integer>{

}
