package com.clinica.dao;

import com.clinica.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArchivoDao extends JpaRepository<Archivo,Integer> {
}
