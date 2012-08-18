package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaClienteController;
import org.tds.sgh.logic.IAltaHotelController;
import org.tds.sgh.logic.ICancelarReservaController;
import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IHacerReservaController;
import org.tds.sgh.logic.ITomarReservaController;
import org.tds.sgh.logic.Precondition.PreconditionException;

public class CancelarReservaTest extends BaseTest
{
	// Constantes -------------------------------------------------------------

	private static final String nombreCliente = "Roberto Martínez";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreTipoHabitacion = "Queen";

	private static final String nombreHabitacion = "33";

	private static final GregorianCalendar fecha1 = new GregorianCalendar(2020, 10, 17);
	
	private static final GregorianCalendar fecha2 = new GregorianCalendar(2020, 10, 27);
	
	private static final String nombreHuesped = "Gonzalo González";
	
	private static final String documentoHuesped = "55.555.555-5";

	
	// Atributos --------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;

	private ITomarReservaController tr;
	
	private ICancelarReservaController cr;
	
	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
		cr = ControllerFactory.CancelarReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		tr = null;
		cr = null;
	}

	
	// Tests ------------------------------------------------------------------

	@Test(expected=PreconditionException.class)
	public void testCodigoReservaInvalido()
	{
		cr.cancelarReserva(0);
	}
	
	@Test(expected=PreconditionException.class)
	public void testReservaNoEstaPendiente()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		tr.tomarReserva(reserva.getCodigo());
		
		cr.cancelarReserva(reserva.getCodigo());
	}

	@Test
	public void testReservaCancelada()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		cr.cancelarReserva(reserva.getCodigo());
		
		Map<Long,IDatosReserva> reservas = ch.listarReservasCliente(nombreCliente);
		assertTrue(!reservas.isEmpty());
		assertTrue(reservas.get(reserva.getCodigo()) != null);
		assertTrue(reservas.get(reserva.getCodigo()).isCancelada());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeCancelarDosVeces()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha1, true);

		cr.cancelarReserva(reserva.getCodigo());
		
		cr.cancelarReserva(reserva.getCodigo());
	}
}
