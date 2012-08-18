package org.tds.sgh.dto;

import java.util.GregorianCalendar;

import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IDatosTipoHabitacion;

public class ReservaDTO implements IDatosReserva{

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

	@Override
	public IDatosTipoHabitacion getTipoHabitacion() {
		// TODO Auto-generated method stub
		return null;
	}

}
