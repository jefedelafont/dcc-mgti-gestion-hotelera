package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.tds.sgh.dto.HabitacionDTO;
import org.tds.sgh.dto.HuespedDTO;

public class Reserva {

	private long codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean isModificablePorHuesped;
	EstadoReserva estadoReserva;
	private TipoHabitacion tipoHabitacion;
	private Habitacion habitacion;
	// nelson Ya�ez//
	private List<Huesped> huespedes;

	public Reserva(long codigo, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) {
		this.codigo = codigo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.isModificablePorHuesped = modificablePorHuesped;
		estadoReserva = EstadoReserva.PENDIENTE;
		huespedes = new ArrayList<Huesped>();
	}

	public long getCodigo() {
		return this.codigo;
	}

	public GregorianCalendar getFechaInicio() {

		return this.fechaInicio;
	}

	public GregorianCalendar getFechaFin() {
		return this.fechaFin;
	}

	public boolean isModificablePorHuesped() {
		return this.isModificablePorHuesped;
	}

	public boolean isPendiente() {
		return estadoReserva.compareTo(EstadoReserva.PENDIENTE) == 0;
	}

	public boolean isTomada() {
		return estadoReserva.compareTo(EstadoReserva.TOMADA) == 0;
	}

	public boolean isCancelada() {
		return estadoReserva.compareTo(EstadoReserva.CANCELADA) == 0;
	}

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public boolean estasEnFechayNoTomada(GregorianCalendar fecha) {
		boolean estasEnFecha = fechaInicio.compareTo(fecha) <= 0
				&& fechaFin.compareTo(fecha) >= 0;
		return estasEnFecha && !isTomada();
	}

	public void registraTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public boolean estasEnRango(Reserva reservaSeleccionada) {
		return reservaSeleccionada.fechaInicio.after(this.fechaFin)
				&& reservaSeleccionada.fechaFin.before(this.fechaInicio);
	}

	public boolean esTuHabitacion(Habitacion habitacion) {
		return this.habitacion.equals(habitacion);
	}

	/**
	 * alvaro modificacion para agregar la condicion
	 * 
	 * @param habitacionLibre
	 * @return
	 */
	public IDatosHabitacion tomarReserva(Habitacion habitacionLibre) {
		Precondition.isFalse(isTomada(),
				"No se puede tomar porque la reserva ya esta tomada");
		this.habitacion = habitacionLibre;
		this.estadoReserva = EstadoReserva.TOMADA;
		IDatosHabitacion iDatosHabitacion = new HabitacionDTO(habitacionLibre);
		return iDatosHabitacion;
	}

	// neslon ya�ez//
	public IDatosHuesped registarHuesped(String nombre2, String documento) {
		Huesped huesped = new Huesped(nombre2, documento);
		huespedes.add(huesped);
		IDatosHuesped iDatosHuesped = new HuespedDTO(huesped);
		return iDatosHuesped;
	}

}
