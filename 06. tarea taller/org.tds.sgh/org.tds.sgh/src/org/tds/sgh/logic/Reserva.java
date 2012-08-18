package org.tds.sgh.logic;

import java.util.GregorianCalendar;

public class Reserva implements IDatosReserva {

	@Override
	public long getCodigo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GregorianCalendar getFechaInicio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getFechaFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isModificablePorHuesped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPendiente() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTomada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCancelada() {
		// TODO Auto-generated method stub
		return false;
	}

}
