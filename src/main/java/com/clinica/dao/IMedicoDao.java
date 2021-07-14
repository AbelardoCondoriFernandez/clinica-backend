package com.clinica.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.model.Medico;
@Repository
public interface IMedicoDao extends JpaRepository<Medico, Integer>{

}
