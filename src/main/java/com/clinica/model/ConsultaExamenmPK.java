package com.clinica.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ConsultaExamenmPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "idExamen", nullable = false)
	private Examen examen;

	@ManyToOne
	@JoinColumn(name = "idConsultam", nullable = false)
	private Consultam consultam;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consultam == null) ? 0 : consultam.hashCode());
		result = prime * result + ((examen == null) ? 0 : examen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaExamenmPK other = (ConsultaExamenmPK) obj;
		if (consultam == null) {
			if (other.consultam != null)
				return false;
		} else if (!consultam.equals(other.consultam))
			return false;
		if (examen == null) {
			if (other.examen != null)
				return false;
		} else if (!examen.equals(other.examen))
			return false;
		return true;
	}


}
