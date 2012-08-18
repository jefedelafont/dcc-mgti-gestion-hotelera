package org.tds.sgh.logic;


public interface IAltaHotelController
{
	IDatosHotel registrarHotel(String nombre);
	
	IDatosTipoHabitacion registrarTipoHabitacion(String nombre);
	
	IDatosHabitacion registrarHabitacion(String hotel, String tipo, String nombre);
}
