package org.tds.sgh.logic;

import java.util.GregorianCalendar;
import java.util.List;

public interface ITomarReservaController extends IHacerReservaController, IIdentificarReservaClienteController
{
	List<IDatosReserva> buscarReservasNoTomadas(String nombreHotel, GregorianCalendar fecha);
	
	IDatosReserva seleccionarReserva(long codigoReserva);
	
	IDatosHuesped registrarHuesped(long codigoReserva, String nombre, String documento);
	
	IDatosHabitacion tomarReserva(long codigoReserva);
}
