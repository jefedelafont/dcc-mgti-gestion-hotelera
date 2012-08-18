package org.tds.sgh.dto;

import org.tds.sgh.logic.Hotel;
import org.tds.sgh.logic.IDatosHotel;

public class HotelDTO implements IDatosHotel {

	Hotel hotel;

	public HotelDTO(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public String getNombre() {
		return this.hotel.getNombre();
	}

}
