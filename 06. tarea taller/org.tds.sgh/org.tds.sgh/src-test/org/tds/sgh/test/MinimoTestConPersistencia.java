package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.infrastructure.DataAccess;
import org.tds.sgh.infrastructure.DataAccessConnection;
import org.tds.sgh.logic.CadenaHotelera;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IDatosCliente;
import org.tds.sgh.logic.IDatosHabitacion;
import org.tds.sgh.logic.IDatosHotel;
import org.tds.sgh.logic.IDatosHuesped;
import org.tds.sgh.logic.IDatosReserva;
import org.tds.sgh.logic.IHacerReservaController;
import org.tds.sgh.logic.ITomarReservaController;

public class MinimoTestConPersistencia extends BaseTest
{
	// Constantes -------------------------------------------------------------

	private static final String nombreCliente = "Roberto Martínez";
	
	private static final String nombreCliente2 = "Juan González";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreHotel2 = "Mariventi";

	private static final String nombreTipoHabitacion = "Queen";

	private static final String nombreTipoHabitacion2 = "King";
	
	private static final String nombreTipoHabitacion3 = "Double";

	private static final String nombreHabitacion = "33";

	private static final String nombreHabitacion2 = "37";
	
	private static final String nombreHabitacion3 = "73";
	
	private static final String nombreHabitacion4 = "37073";

	private static final GregorianCalendar fecha1 = new GregorianCalendar(2020, 10, 17);
	
	private static final GregorianCalendar fecha2 = new GregorianCalendar(2020, 10, 27);
	
	private static final String nombreHuesped = "Gonzalo González";
	
	private static final String documentoHuesped = "55.555.555-5";


	// Atributos --------------------------------------------------------------

	private DataAccessConnection cnx;

	
	private IHacerReservaController hr;
	
	private ITomarReservaController tr;
	
	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{		
		cnx = DataAccess.getInstance().createConnection();
		
		cnx.beginTx();

		super.setUp();
		
		hr = ControllerFactory.HacerReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		cnx.commitTx();
		
		
		hr = null;
		tr = null;
		
		super.tearDown();
	}
	
	
	// Tests ------------------------------------------------------------------
	
	@Test
	public void testHacerReservaEscenarioTipico()
	{
		System.out.println("\n\nHacerReservaEscenarioTipico");
		
		IDatosCliente cliente = hr.seleccionarCliente(nombreCliente);
		assertTrue(cliente != null);
		assertTrue(cliente.getNombre().equals(nombreCliente));

		boolean disp = hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(disp);
		
		IDatosReserva reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva != null);
		assertTrue(reserva.isPendiente());
		
		hr.confirmarReserva(reserva.getCodigo());
	}
	
	@Test
	public void testHacerReservaEscenarioAlternativo()
	{
		System.out.println("\n\nHacerReservaEscenarioAlternativo");
		
		IDatosCliente cliente = hr.seleccionarCliente(nombreCliente2);
		assertTrue(cliente != null);
		assertTrue(cliente.getNombre().equals(nombreCliente2));

		boolean disp = hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(!disp);

		List<IDatosHotel> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(hoteles != null);
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
		
		IDatosReserva reserva2 = hr.registrarReserva(nombreCliente2, nombreHotel2, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva2 != null);
		assertTrue(reserva2.isPendiente());

		hr.confirmarReserva(reserva2.getCodigo());
	}

	@Test
	public void testTomarReserva()
	{
		IDatosReserva reserva1 = ch.listarReservasCliente(nombreCliente).values().iterator().next();
		assertTrue(reserva1 != null);
		
		System.out.println("\n\nTomarReserva");
		
		List<IDatosReserva> reservasNoTomadas = tr.buscarReservasNoTomadas(nombreHotel, fecha1);
		assertTrue(reservasNoTomadas != null);
		assertTrue(reservasNoTomadas.size() == 1);
		assertTrue(reservasNoTomadas.get(0).getCodigo() == reserva1.getCodigo());
		
		
		IDatosReserva reserva = tr.seleccionarReserva(reservasNoTomadas.get(0).getCodigo());
		assertTrue(reserva != null);
		assertTrue(reserva.getCodigo() == reserva1.getCodigo());
		
		
		IDatosHuesped huesped = tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		assertTrue(huesped != null);
		
		
		IDatosHabitacion habitacion = tr.tomarReserva(reserva.getCodigo());
		assertTrue(habitacion != null);
		assertTrue(habitacion.getNombre().equals(nombreHabitacion));
		
		
		tr.confirmarReserva(reserva.getCodigo());
	}

	
	// BaseTest ---------------------------------------------------------------
	
	@Override
	protected CadenaHotelera createCadenaHotelera()
	{
		CadenaHotelera CH = null;
		
		try
		{
			CH = (CadenaHotelera)cnx.get("CadenaHotelera");
		}
		catch (Exception e)
		{
			// ignore
		}
		
		if (CH == null)
		{
			CadenaHotelera newCH = new CadenaHotelera();
			
			newCH.registrarCliente(nombreCliente, telefono, email);
			newCH.registrarCliente(nombreCliente2, telefono, email);
			
			newCH.registrarTipoHabitacion(nombreTipoHabitacion);
			newCH.registrarTipoHabitacion(nombreTipoHabitacion2);
			newCH.registrarTipoHabitacion(nombreTipoHabitacion3);
			
			newCH.registrarHotel(nombreHotel);
			newCH.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
			newCH.registrarHabitacion(nombreHotel, nombreTipoHabitacion2, nombreHabitacion2);
			
			newCH.registrarHotel(nombreHotel2);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion3);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion4);
			
			cnx.save(newCH);
			
			cnx.commitTx();

			
			cnx.beginTx();
			
			CH = (CadenaHotelera)cnx.get("CadenaHotelera");
		}
		
		return CH; 
	}
}
