package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaClienteController;
import org.tds.sgh.logic.IDatosCliente;
import org.tds.sgh.logic.Precondition.PreconditionException;

public class AltaClienteTest extends BaseTest
{
	// Constantes -------------------------------------------------------------
	
	private static final String nombre = "Roberto Martínez";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	

	// Atributos --------------------------------------------------------------
	
	private IAltaClienteController ac;

	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ac = ControllerFactory.AltaClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
	}

	
	// Tests ------------------------------------------------------------------
	
	@Test
	public void testNoHayClientes()
	{
		assertTrue("Ya existen clientes registrados", ch.listarClientes().isEmpty());
	}
	
	@Test
	public void testAgregarCliente()
	{
		ac.registrarCliente(nombre, telefono, email);
		
		assertTrue("El cliente no fue registrado", !ch.listarClientes().isEmpty());
		
		IDatosCliente cliente = ch.listarClientes().get(nombre);
		assertTrue("El cliente no fue resgistrado", cliente != null);
		
		assertTrue("El nombre del cliente es incorrecto", cliente.getNombre().equals(nombre));
		assertTrue("El teléfono del cliente es incorrecto", cliente.getTelefono().equals(telefono));
		assertTrue("El email del cliente es incorrecto", cliente.getEMail().equals(email));
	}
	
	@Test
	public void testClienteNuevoSinReservas()
	{
		ac.registrarCliente(nombre, telefono, email);
		assertTrue("Existen reservas para el cliente", ch.listarReservasCliente(nombre).isEmpty());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosClientesConIgualNombre()
	{
		ac.registrarCliente(nombre, telefono, email);
		ac.registrarCliente(nombre, telefono, email);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testColeccionDeClientesNoSePuedeModificar()
	{
		ch.listarClientes().clear();
	}
}
