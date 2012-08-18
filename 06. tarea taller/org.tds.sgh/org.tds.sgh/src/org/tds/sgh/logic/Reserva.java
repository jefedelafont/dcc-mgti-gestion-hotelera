package org.tds.sgh.logic;

import java.util.GregorianCalendar;

import org.tds.sgh.dto.HabitacionDTO;

public class Reserva implements IDatosReserva {

	private long codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean isModificablePorHuesped;
	EstadoReserva estadoReserva;
	private TipoHabitacion tipoHabitacion;
	private Habitacion habitacion;
	
	public Reserva(long codigo, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean modificablePorHuesped) {
		this.codigo = codigo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.isModificablePorHuesped = modificablePorHuesped;
	}
	
	@Override
	public long getCodigo() {
		return this.codigo;
	}

	@Override
	public GregorianCalendar getFechaInicio() {

		return this.fechaInicio;
	}

	@Override
	public GregorianCalendar getFechaFin() {
		return this.fechaFin;
	}

	@Override
	public boolean isModificablePorHuesped() {
		return this.isModificablePorHuesped;
	}

	@Override
	public boolean isPendiente() {
		return estadoReserva.compareTo(EstadoReserva.PENDIENTE) == 0;
	}

	@Override
	public boolean isTomada() {
		return estadoReserva.compareTo(EstadoReserva.TOMADA) == 0;
	}

	@Override
	public boolean isCancelada() {
		return estadoReserva.compareTo(EstadoReserva.CANCELADA) == 0;
	}

	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public boolean estasEnFechayNoTomada(GregorianCalendar fecha) {
		boolean estasEnFecha = fechaInicio.before(fecha)
				&& fechaFin.after(fecha);
		return estasEnFecha && !isTomada();
	}
	
	public void registraTipoHabitacion(IDatosTipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = this.tipoHabitacion;
	}
	
	public boolean estasEnRango(Reserva reservaSeleccionada) {
		return reservaSeleccionada.fechaInicio.after(this.fechaFin)
				&& reservaSeleccionada.fechaFin.before(this.fechaInicio);
	}

	public boolean esTuHabitacion(Habitacion habitacion) {
		return this.habitacion.equals(habitacion);
	}

	public IDatosHabitacion tomarReserva(Habitacion habitacionLibre) {
		this.habitacion = habitacionLibre;
		IDatosHabitacion iDatosHabitacion = new HabitacionDTO(habitacionLibre);
		return iDatosHabitacion;
	}

}
