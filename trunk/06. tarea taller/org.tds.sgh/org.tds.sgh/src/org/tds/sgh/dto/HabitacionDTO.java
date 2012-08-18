package org.tds.sgh.dto;

import org.tds.sgh.logic.Habitacion;
import org.tds.sgh.logic.IDatosHabitacion;

public class HabitacionDTO implements IDatosHabitacion {
	private String nombre;

	public HabitacionDTO(Habitacion habitacion) {
		this.nombre = habitacion.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

}
