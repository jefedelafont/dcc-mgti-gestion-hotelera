package org.tds.sgh.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaClienteController;
import org.tds.sgh.logic.IAltaHotelController;
import org.tds.sgh.logic.IDatosHotel;
import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IHacerReservaController;
import org.tds.sgh.logic.Precondition.PreconditionException;

//import com.sun.java.swing.plaf.windows.TMSchema.Control;

public class HacerReservaTest extends BaseTest
{
	// Constantes -------------------------------------------------------------

	private static final String nombreCliente = "Roberto Martínez";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreHotel2 = "Mariventi";

	private static final String nombreHotel3 = "Ajedrezzi";

	private static final String nombreTipoHabitacion = "Queen";

	private static final String nombreTipoHabitacion2 = "King";

	private static final String nombreHabitacion = "33";

	private static final String nombreHabitacion2 = "37";
	
	private static final String nombreHabitacion3 = "73";

	private static final GregorianCalendar fecha1 = new GregorianCalendar(2020, 10, 17);
	
	private static final GregorianCalendar fecha2 = new GregorianCalendar(2020, 10, 27);
	
	private static final GregorianCalendar fecha3 = new GregorianCalendar(2020, 11, 07);
	

	// Atributos --------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;
	
	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
	}
	
	
	// Tests ------------------------------------------------------------------

	// confirmarDisponibilidad

	@Test(expected=PreconditionException.class)
	public void testConfirmarDisponibilidadHotelInvalido()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testConfimarDisponibilidadTipoHabitacionInvalido()
	{
		ah.registrarHotel(nombreHotel);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testConfirmarDisponibilidadFechasInvalidas()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha2, fecha1);
	}
	
	@Test
	public void testHayDisponibilidadSoloUnaHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		assertTrue(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2));
	}
	
	@Test
	public void testHayDisponibilidadDosHabitaciones()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion2);
		
		assertTrue(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2));
	}
	
	@Test
	public void testNoHayDisponibilidadParaTipoSinHabitaciones()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion2);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		assertFalse(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion2, fecha1, fecha2));
	}
	

	// registrarReserva

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaClienteInvalido()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaHotelInvalido()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaTipoHabitacionInvalido()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaFechasInvalidas()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha1, true);
	}
	
	@Test
	public void testRegistrarReserva()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);

		
		assertTrue(reserva.isPendiente());
		assertTrue(ch.obtenerTipoHabitacionDeReserva(reserva.getCodigo()).getNombre().equals(nombreTipoHabitacion));
		assertTrue(ch.obtenerHabitacionDeReserva(reserva.getCodigo()) == null);
		assertTrue(reserva.getFechaInicio().equals(fecha1));
		assertTrue(reserva.getFechaFin().equals(fecha3));
		assertTrue(reserva.isModificablePorHuesped());

		Map<Long,IDatosReserva> reservas = ch.listarReservasCliente(nombreCliente);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(reserva.getCodigo()) != null);
		
		reservas = ch.listarReservasHotel(nombreHotel);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(reserva.getCodigo()) != null);
	}
	
	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaSinDisponibilidadInvalido()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha3, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaSinDisponibilidadAunqueExistanAlternativasInvalido()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);

		ah.registrarHotel(nombreHotel2);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);

		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha3, true);
	}

	
	// sugerirAlternativas
	
	@Test(expected=PreconditionException.class)
	public void testSugerirAlternativasTipoHabitacionInvalido()
	{
		hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testFechasInvalidas()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha1);
	}

	@Test
	public void testSugerirUno()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		
		List<IDatosHotel> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
		
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
	}

	@Test
	public void testSugerirUnoConDobleDisponibilidad()
	{
		ac.registrarCliente(nombreCliente, telefono, email);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion3);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);
		
		List<IDatosHotel> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha3);
		
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
	}

	@Test
	public void testSugerirDos()
	{
		ac.registrarCliente(nombreCliente, telefono, email);

		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion2);
		
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion2, nombreHabitacion2);

		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion3);

		ah.registrarHotel(nombreHotel3);
		ah.registrarHabitacion(nombreHotel3, nombreTipoHabitacion, nombreHabitacion3);

		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);
		
		List<IDatosHotel> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha3);
		
		assertTrue(hoteles.size() == 2);
		for (IDatosHotel hotel : hoteles)
		{
			assertTrue(hotel.getNombre().equals(nombreHotel2) || hotel.getNombre().equals(nombreHotel3));
		}
	}
}
