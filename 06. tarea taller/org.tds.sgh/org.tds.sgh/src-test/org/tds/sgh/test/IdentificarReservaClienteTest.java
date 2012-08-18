package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaClienteController;
import org.tds.sgh.logic.IAltaHotelController;
import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IHacerReservaController;
import org.tds.sgh.logic.IIdentificarReservaClienteController;
import org.tds.sgh.logic.Precondition.PreconditionException;

public class IdentificarReservaClienteTest extends BaseTest
{
	// Constantes -------------------------------------------------------------

	private static final String nombreCliente = "Roberto Martínez";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreHotel2 = "Mariventi";

	@SuppressWarnings("unused")
	private static final String nombreHotel3 = "Ajedrezzi";

	private static final String nombreTipoHabitacion = "Queen";

	@SuppressWarnings("unused")
	private static final String nombreTipoHabitacion2 = "Kind";

	private static final String nombreHabitacion = "33";

	private static final String nombreHabitacion2 = "37";
	
	@SuppressWarnings("unused")
	private static final String nombreHabitacion3 = "73";

	private static final GregorianCalendar fecha1 = new GregorianCalendar(2020, 10, 17);
	
	private static final GregorianCalendar fecha2 = new GregorianCalendar(2020, 10, 27);
	
	private static final GregorianCalendar fecha3 = new GregorianCalendar(2020, 11, 07);
	

	// Atributos --------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;
	
	private IIdentificarReservaClienteController irc;
	
	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		irc = ControllerFactory.IdentificarReservaClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		irc = null;
	}
	
	
	// Tests ------------------------------------------------------------------

	// buscarReservasActivas
	
	@Test(expected=PreconditionException.class)
	public void testClienteInexistente()
	{
		irc.buscarReservasPendientes(nombreCliente);
	}
	
	@Test
	public void testClienteSinReservasNoTieneActivas()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		
		assertTrue(irc.buscarReservasPendientes(nombreCliente).isEmpty());
	}

	@Test
	public void testClienteConUnaNuevaReservaTieneActiva()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		List<IDatosReserva> reservas = irc.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(0).getCodigo() == reserva.getCodigo());
	}
	
	public void testClienteConDosReservasNuevas()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);

		IDatosReserva reserva1 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		IDatosReserva reserva2 = hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);

		List<IDatosReserva> reservas = irc.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.size() == 2);
		assertTrue(reservas.get(0).getCodigo() == reserva1.getCodigo() || reservas.get(0).getCodigo() == reserva2.getCodigo());
		assertTrue(reservas.get(1).getCodigo() == reserva1.getCodigo() || reservas.get(1).getCodigo() == reserva2.getCodigo());
	}
	

	// seleccionarReserva
	
	@Test(expected=PreconditionException.class)
	public void testCodigoReservaInvalido()
	{
		irc.seleccionarReserva(0);
	}

	@Test
	public void testRegistrarReservaHotelInvalido()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		IDatosReserva reservaSeleccionada = irc.seleccionarReserva(reserva.getCodigo());
		assertTrue(reservaSeleccionada != null);
	}
}
