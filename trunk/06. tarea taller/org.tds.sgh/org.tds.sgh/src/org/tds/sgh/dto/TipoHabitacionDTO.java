package org.tds.sgh.dto;

import org.tds.sgh.logic.IDatosTipoHabitacion;
import org.tds.sgh.logic.TipoHabitacion;

public class TipoHabitacionDTO implements IDatosTipoHabitacion {

	private String nombre;

	public TipoHabitacionDTO(TipoHabitacion tipoHabitacion) {
		this.nombre = tipoHabitacion.getNombre();
	}

	@Override
	public String getNombre() {
		return nombre;
	}

}
