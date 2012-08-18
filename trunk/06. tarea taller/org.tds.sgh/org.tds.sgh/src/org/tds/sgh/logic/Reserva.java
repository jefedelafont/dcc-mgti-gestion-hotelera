package org.tds.sgh.logic;

import java.util.GregorianCalendar;

public class Reserva implements IDatosReserva {

	private long codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean isModificablePorHuesped ;
	private boolean isPendiente;
	private boolean isTomada;
	private boolean isCancelada;
	

	
	@Override
	public long getCodigo() {
		// TODO Auto-generated method stub
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
