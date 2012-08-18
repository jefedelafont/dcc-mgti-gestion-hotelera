package org.tds.sgh.logic;

import java.util.GregorianCalendar;

public interface IDatosReserva
{
	long getCodigo();
	
	GregorianCalendar getFechaInicio();
	
	GregorianCalendar getFechaFin();

	boolean isModificablePorHuesped();

	boolean isPendiente();

	boolean isTomada();

	boolean isCancelada();
}
