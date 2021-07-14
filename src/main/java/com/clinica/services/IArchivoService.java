package com.clinica.services;

import com.clinica.model.Archivo;

public interface IArchivoService {
    int guardar(Archivo archivo);
    byte[] leerArchivo(Integer idArchivo);
}
