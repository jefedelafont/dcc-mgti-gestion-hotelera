package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaHotelController;
import org.tds.sgh.logic.IDatosHabitacion;
import org.tds.sgh.logic.IDatosHotel;
import org.tds.sgh.logic.IDatosTipoHabitacion;
import org.tds.sgh.logic.Precondition.PreconditionException;

public class AltaHotelTest extends BaseTest
{
	// Constantes -------------------------------------------------------------
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreTipoHabitacion = "Queen";
	
	private static final String nombreHabitacion = "33";
	

	// Atributos --------------------------------------------------------------
	
	private IAltaHotelController ah;

	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ah = ControllerFactory.AltaHotelController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ah = null;
	}

	
	// Tests ------------------------------------------------------------------
	
	// Hotel
	
	@Test
	public void testNoHayHoteles()
	{
		assertTrue("Ya existen hoteles registrados", ch.listarHoteles().isEmpty());
	}
	
	@Test
	public void testAgregarHoteles()
	{
		ah.registrarHotel(nombreHotel);
		
		assertTrue("El hotel no fue registrado", !ch.listarHoteles().isEmpty());
		
		IDatosHotel hotel = ch.listarHoteles().get(nombreHotel);
		assertTrue("El hotel no fue resgistrado", hotel != null);
		
		assertTrue("El nombre del hotel es incorrecto", hotel.getNombre().equals(nombreHotel));
		
		assertTrue("Existen habitaciones en el hotel", ch.listarHabitaciones(nombreHotel).isEmpty());
		
		assertTrue("Existen reservas en el hotel", ch.listarReservasHotel(nombreHotel).isEmpty());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosHotelesConIgualNombre()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarHotel(nombreHotel);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testColeccionDeHotelesNoSePuedeModificar()
	{
		ch.listarHoteles().clear();
	}

	
	
	// TipoHabitacion

	@Test
	public void testNoHayTiposHabitacion()
	{
		assertTrue("Ya existen tipos de habitacion registrados", ch.listarTiposHabitacion().isEmpty());
	}
	
	@Test
	public void testAgregarTipoHabitacion()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		assertTrue("El tipo de habitación no fue registrado", !ch.listarTiposHabitacion().isEmpty());
		
		IDatosTipoHabitacion tipoHabitacion = ch.listarTiposHabitacion().get(nombreTipoHabitacion);
		assertTrue("El tipo de habitación no fue resgistrado", tipoHabitacion != null);
		
		assertTrue("El nombre del tipo de habitación es incorrecto", tipoHabitacion.getNombre().equals(nombreTipoHabitacion));
	}

	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosTiposHabitacionConIgualNombre()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testColeccionDeTiposHabitacionNoSePuedeModificar()
	{
		ch.listarTiposHabitacion().clear();
	}
	
	
	// Habitacion

	@Test(expected=PreconditionException.class)
	public void testHabitacionRequireHotel()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
	
	@Test(expected=PreconditionException.class)
	public void testHabitacionRequireTipoHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
	
	@Test
	public void testAgregarHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		IDatosHabitacion habitacion = ch.listarHabitaciones(nombreHotel).get(nombreHabitacion);
		
		assertTrue("La habitación no fue resgistrada en el hotel", habitacion != null);
		
		assertTrue("El nombre de la habitación es incorrecto", habitacion.getNombre().equals(nombreHabitacion));

		assertTrue("El tipo de la habitación es incorrecto", ch.obtenerTipoHabitacionDeHabitacion(nombreHotel, nombreHabitacion).getNombre().equals(nombreTipoHabitacion));
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosHabitacionesConIgualNombre()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testColeccionDeHabitacionesNoSePuedeModificar()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ch.listarHabitaciones(nombreHotel).clear();
	}
}
