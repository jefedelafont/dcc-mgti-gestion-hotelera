package org.tds.sgh.dto;

import org.tds.sgh.logic.IDatosTipoHabitacion;
import org.tds.sgh.logic.TipoHabitacion;

public class TipoHabitacionDTO implements IDatosTipoHabitacion {

	private TipoHabitacion tipoHabitacion;

	public TipoHabitacionDTO(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	@Override
	public String getNombre() {
		return tipoHabitacion.getNombre();
	}

}
