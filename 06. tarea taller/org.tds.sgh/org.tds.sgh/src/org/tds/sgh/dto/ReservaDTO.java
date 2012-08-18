package org.tds.sgh.dto;

import java.util.GregorianCalendar;

import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IDatosTipoHabitacion;
import org.tds.sgh.logic.Reserva;

public class ReservaDTO implements IDatosReserva{

	private long codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean isModificablePorHuesped;
	private boolean isPendiente;
	private boolean isTomada;
	private boolean isCancelada;
	
	public ReservaDTO(Reserva reserva) {
		this.codigo = reserva.getCodigo();
		this.fechaInicio = reserva.getFechaInicio();
		this.fechaFin = reserva.getFechaFin();
		this.isModificablePorHuesped = reserva.isModificablePorHuesped();
		this.isPendiente = reserva.isPendiente();
		this.isTomada = reserva.isTomada();
		this.isCancelada = reserva.isCancelada();
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
		return this.isPendiente;
	}

	@Override
	public boolean isTomada() {
		return this.isTomada;
	}

	@Override
	public boolean isCancelada() {
		return this.isCancelada;
	}

}
