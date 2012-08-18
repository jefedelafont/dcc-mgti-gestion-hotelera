package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements IDatosHotel
{
	// Atributos --------------------------------------------------------------
	
	private String nombre;
	
	private Map<String,Habitacion> habitaciones;
	private List<IDatosReserva> reservas;
	
	// Constructores ----------------------------------------------------------
	
	public Hotel(String nombre)
	{
		this.nombre = nombre;
		this.habitaciones = new HashMap<String,Habitacion>();
		this.reservas = new ArrayList<IDatosReserva>();
	}
	
	
	// IDatosHotel ------------------------------------------------------------
	
	@Override
	public String getNombre()
	{
		return nombre;
	}
	
	
	// Operaciones ------------------------------------------------------------
	
	Habitacion registrarHabitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		Precondition.notContain(habitaciones, nombre, "En el hotel '" + this.nombre + "' ya existe una habitación con el nombre '" + nombre + "'");
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		habitaciones.put(nombre, habitacion);
		return habitacion;
	}
	
	Map<String,IDatosHabitacion> listarHabitaciones()
	{
		return Collections.<String,IDatosHabitacion>unmodifiableMap(habitaciones);
	}

	IDatosTipoHabitacion obtenerTipoHabitacionDeHabitacion(String nombreHabitacion)
	{
		return habitaciones.get(nombreHabitacion).getTipoHabitacion();
	}
	
	boolean confirmarDisponibilidad(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		boolean res = true;
		
		for ( IDatosReserva reserva: reservas ) {
			
		}
		
		return true;
	}
	

	public List<IDatosHotel> sugerirAlternativas(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}


	public IDatosReserva registrarReserva(String nombreCliente,
			String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin,
			boolean modificablePorHuesped) {
		// TODO Auto-generated method stub
		return null;
	}
}
