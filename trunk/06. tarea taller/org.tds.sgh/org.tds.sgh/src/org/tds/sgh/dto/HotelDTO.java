package org.tds.sgh.dto;

import org.tds.sgh.logic.IDatosHotel;

public class HotelDTO implements IDatosHotel {

	private String nombre;
	
	@Override
	public String getNombre() {
		return this.nombre;
	}

}
