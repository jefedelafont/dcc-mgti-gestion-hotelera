package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tds.sgh.dto.ReservaDTO;

public class Hotel implements IDatosHotel {
	// Atributos --------------------------------------------------------------

	private String nombre;
	private Map<String, Habitacion> habitaciones;
	private Map<Long, Reserva> reservas;
	private Reserva reservaSeleccionada;
	private Hotel hotelEnUso;

	// Constructores ----------------------------------------------------------

	public Hotel(String nombre) {
		this.nombre = nombre;
		this.habitaciones = new HashMap<String, Habitacion>();
		this.reservas = new HashMap<Long, Reserva>();
	}

	// IDatosHotel ------------------------------------------------------------

	@Override
	public String getNombre() {
		return nombre;
	}

	// Operaciones ------------------------------------------------------------

	Habitacion registrarHabitacion(TipoHabitacion tipoHabitacion, String nombre) {
		Precondition.notContain(habitaciones, nombre, "En el hotel '"
				+ this.nombre + "' ya existe una habitaci�n con el nombre '"
				+ nombre + "'");

		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		habitaciones.put(nombre, habitacion);
		return habitacion;
	}

	Map<String, IDatosHabitacion> listarHabitaciones() {
		return Collections
				.<String, IDatosHabitacion> unmodifiableMap(habitaciones);
	}

	IDatosTipoHabitacion obtenerTipoHabitacionDeHabitacion(
			String nombreHabitacion) {
		return habitaciones.get(nombreHabitacion).getTipoHabitacion();
	}

	// MAREL
	boolean confirmarDisponibilidad(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		boolean res = true;

		int cantidadReservas = this.cantidadReservasTipoHabitacion(
				nombreTipoHabitacion, fechaInicio, fechaFin);
		int cantidadaHabitaciones = this
				.cantidadHabitacionessTipoHabitacion(nombreTipoHabitacion);

		return cantidadaHabitaciones > cantidadReservas;
	}

	// MAREL
	private int cantidadReservasTipoHabitacion(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		int res = 0;

		for (Reserva reserva : reservas.values()) {
			if (reserva.getTipoHabitacion().getNombre()
					.equals(nombreTipoHabitacion)
					&& reserva.getFechaInicio().after(fechaInicio)
					&& reserva.getFechaFin().before(fechaFin)) {
				res += 1;
			}
		}

		return res;
	}

	// MAREL
	private int cantidadHabitacionessTipoHabitacion(String nombreTipoHabitacion) {
		int res = 0;

		for (Habitacion habitacion : habitaciones.values()) {
			if (habitacion.getTipoHabitacion().equals(nombreTipoHabitacion)) {
				res += 1;
			}
		}

		return res;
	}

	// MAREL
	public List<IDatosHotel> sugerirAlternativas(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}

	// MAREL
	public IDatosReserva registrarReserva(String nombreCliente,
			String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin,
			boolean modificablePorHuesped) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Alvaro Jose Peralta Ocampo
	 * 
	 * @param fecha
	 * @return
	 */
	public List<IDatosReserva> buscarReservasNoTomadas(GregorianCalendar fecha) {
		List<IDatosReserva> datosReservas = new ArrayList<IDatosReserva>();
		for (Reserva reserva : reservas.values()) {
			boolean enFechayNoTomada = reserva.estasEnFechayNoTomada(fecha);
			if (enFechayNoTomada) {
				IDatosReserva iDatosReserva = new ReservaDTO(reserva);
				datosReservas.add(iDatosReserva);
			}
		}
		return datosReservas;
	}

	/**
	 * alvaro jose peralta ocampo
	 * 
	 * @param codigoReserva
	 * @return
	 */
	public IDatosReserva seleccionarReserva(long codigoReserva) {
		reservaSeleccionada = reservas.get(codigoReserva);
		return null;
	}
	
	// MAREL
	IDatosReserva registrarReserva(Cliente cliente, TipoHabitacion tipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean modificablePorHuesped) {
		// assert tipos de habitacion por hotel
		
		long codigo = 1; //mock del codigo
		Reserva reserva = new Reserva(codigo, fechaInicio, fechaFin, modificablePorHuesped);
		reserva.registraTipoHabitacion(tipoHabitacion);
		
		// reservas del hotel
		this.reservas.put(reserva.getCodigo(), reserva);
		
		// reservas del cliente
		cliente.RegistrarReserva(reserva);
		
		return reserva;
	}
	
	public void confirmarReserva(long codigoReserva) {
		for (Reserva reserva : reservas.values()) 
		{
			if (reserva.getCodigo() == codigoReserva && reserva.isPendiente()) {
				reserva.estadoReserva =EstadoReserva.TOMADA;
			}
		}
	}
	
}
