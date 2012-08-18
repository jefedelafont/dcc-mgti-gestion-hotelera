package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tds.sgh.dto.HabitacionDTO;
import org.tds.sgh.dto.ReservaDTO;
import org.tds.sgh.dto.TipoHabitacionDTO;

public class Hotel implements IDatosHotel{
	// Atributos --------------------------------------------------------------

	private String nombre;
	private Map<String, Habitacion> habitaciones;
	private Map<Long, Reserva> reservas;
	private Reserva reservaSeleccionada;

	// Constructores ----------------------------------------------------------

	public Hotel(String nombre) {
		this.nombre = nombre;
		this.habitaciones = new HashMap<String, Habitacion>();
		this.reservas = new HashMap<Long, Reserva>();
	}

	// IDatosHotel ------------------------------------------------------------

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

	public IDatosTipoHabitacion obtenerTipoHabitacionDeHabitacion(
			String nombreHabitacion) {
		TipoHabitacion tipoHabitacion = habitaciones.get(nombreHabitacion)
				.getTipoHabitacion();
		TipoHabitacionDTO tipoHabitacionDTO = new TipoHabitacionDTO(
				tipoHabitacion);
		return tipoHabitacionDTO;
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
					&& fechaInicio.compareTo(reserva.getFechaFin()) < 0
					&& fechaFin.compareTo(reserva.getFechaInicio()) > 0) {
				res += 1;
			}
		}

		return res;
	}

	// MAREL
	private int cantidadHabitacionessTipoHabitacion(String nombreTipoHabitacion) {
		int res = 0;

		for (Habitacion habitacion : habitaciones.values()) {
			if (habitacion.getTipoHabitacion().getNombre()
					.equals(nombreTipoHabitacion)) {
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
		IDatosReserva iDatosReserva = new ReservaDTO(reservaSeleccionada);
		return iDatosReserva;
	}

	/**
	 * marel
	 */
	public IDatosReserva registrarReserva(long codigo, Cliente cliente,
			TipoHabitacion tipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) {
		
		
		Precondition.isTrue(this.confirmarDisponibilidad(tipoHabitacion.getNombre(), fechaInicio, fechaFin), "No hay disponibilidad");
		
		reservaSeleccionada = new Reserva(codigo, fechaInicio, fechaFin,
				modificablePorHuesped);
		reservaSeleccionada.registraTipoHabitacion(tipoHabitacion);

		// reservas del hotel
		this.reservas.put(reservaSeleccionada.getCodigo(), reservaSeleccionada);

		// reservas del cliente
		cliente.RegistrarReserva(reservaSeleccionada);

		IDatosReserva iDatosReserva = new ReservaDTO(reservaSeleccionada);
		return iDatosReserva;
	}

	public void confirmarReserva(long codigoReserva) {
		for (Reserva reserva : reservas.values()) {
			if (reserva.getCodigo() == codigoReserva && reserva.isPendiente()) {
				reserva.estadoReserva = EstadoReserva.TOMADA;
			}
		}
	}

	public IDatosHabitacion tomarReserva(long codigoReserva) {
		if(reservaSeleccionada.getCodigo() != codigoReserva){
			reservaSeleccionada = reservas.get(codigoReserva);
		}
		TipoHabitacion tipoHabitacion = reservaSeleccionada.getTipoHabitacion();
		Habitacion habitacionLibre = buscarHabitacionDesocupada(tipoHabitacion);
		IDatosHabitacion iDatosHabitacion = reservaSeleccionada
				.tomarReserva(habitacionLibre);
		return iDatosHabitacion;
	}

	private Habitacion buscarHabitacionDesocupada(TipoHabitacion tipoHabitacion) {
		Habitacion habitacionLibre = null;
		Collection<Reserva> reservasEnRango = new ArrayList<Reserva>();
		for (Reserva reserva : reservas.values()) {
			boolean estasEnRango = reserva.estasEnRango(reservaSeleccionada);
			if (estasEnRango) {
				reservasEnRango.add(reserva);
			}
		}

		boolean habitacionOcupada = false;
		for (Habitacion habitacion : habitaciones.values()) {
			boolean eresDelTipo = habitacion.eresDelTIpo(tipoHabitacion);
			if (eresDelTipo) {
				for (Reserva reserva : reservasEnRango) {
					if (reserva.esTuHabitacion(habitacion)) {
						habitacionOcupada = true;
					}
				}
				if (!habitacionOcupada) {
					habitacionLibre = habitacion;
					break;
				}
			}
		}
		return habitacionLibre;
	}
   //Nelson Ya�ez//
	public IDatosHuesped registrarHuespedEnReservaSeleccionada(
			long codigoReserva, String nombre2, String documento) {
		if(reservaSeleccionada.getCodigo() != codigoReserva){
			reservaSeleccionada = reservas.get(codigoReserva);
		}
		return this.reservaSeleccionada.registarHuesped(nombre2, documento);
	}

	// marel
	public Map<Long, Reserva> listarReservasHotel() {
		return this.reservas;
	}
	
	// marel
	public void cancelarReserva(long codigoReserva) {
		Reserva reserva = reservas.get(codigoReserva);
		
		Precondition.isNotNull(reserva, "No existe la reserva");
		Precondition.isTrue(reserva.isPendiente(),"El estado de la reserva debe ser pendiente");
		
		reserva.estadoReserva = EstadoReserva.CANCELADA;
	}
	
	// marel
	public boolean containsReserva(long codigoReserva) {
		return this.reservas.containsKey(codigoReserva);
	}

}
