package com.clinica.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(ConsultaExamenmPK.class)
public class ConsultaExamenm implements Serializable {


	@Id
	private Examen examen;

	@Id
	private Consultam consultam;

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Consultam getConsultam() {
        return consultam;
    }

    public void setConsultam(Consultam consultam) {
        this.consultam = consultam;
    }
}
