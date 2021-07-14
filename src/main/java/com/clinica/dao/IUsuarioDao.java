package com.clinica.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.model.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	//obtiene informacion del usuario en base a su nombre
	Usuario findOneByUsername(String username);

}
