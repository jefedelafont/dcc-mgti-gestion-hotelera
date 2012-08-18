package org.tds.sgh.logic;

import java.util.GregorianCalendar;
import java.util.List;


public interface IHacerReservaController extends IAltaClienteController, IIdentificarClienteController, IConfirmarReservaController
{
	boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin);
	
	List<IDatosHotel> sugerirAlternativas(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin);
	
	IDatosReserva registrarReserva(String nombreCliente, String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean modificablePorHuesped);
}
