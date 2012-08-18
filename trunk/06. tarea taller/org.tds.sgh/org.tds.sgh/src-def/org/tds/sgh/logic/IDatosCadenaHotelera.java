package org.tds.sgh.logic;

import java.util.Map;

public interface IDatosCadenaHotelera
{
	Map<String,IDatosCliente> listarClientes();

	Map<String,IDatosHotel> listarHoteles();

	Map<String,IDatosTipoHabitacion> listarTiposHabitacion();
	
	Map<String,IDatosHabitacion> listarHabitaciones(String nombreHotel);
	
	Map<Long,IDatosReserva> listarReservasHotel(String nombreHotel);

	Map<Long,IDatosReserva> listarReservasCliente(String nombreCliente);
	
	IDatosTipoHabitacion obtenerTipoHabitacionDeHabitacion(String nombreHotel, String nombreHabitacion);
	
	IDatosTipoHabitacion obtenerTipoHabitacionDeReserva(long codigo);
	
	IDatosHabitacion obtenerHabitacionDeReserva(long codigo);
}
