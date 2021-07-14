package com.clinica.services.impl;

import com.clinica.dao.IArchivoDao;
import com.clinica.model.Archivo;
import com.clinica.services.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    private IArchivoDao dao;
    @Override
    public int guardar(Archivo archivo) {
        Archivo ar=dao.save(archivo);
        return ar.getIdArchivo()>0?1:0;
    }

    @Override
    public byte[] leerArchivo(Integer idArchivo) {
        return dao.findOne(idArchivo).getValue();
    }
}
