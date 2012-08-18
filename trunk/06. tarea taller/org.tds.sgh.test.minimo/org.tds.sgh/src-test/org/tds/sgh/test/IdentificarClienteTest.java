package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tds.sgh.logic.ControllerFactory;
import org.tds.sgh.logic.IAltaClienteController;
import org.tds.sgh.logic.IDatosCliente;
import org.tds.sgh.logic.IIdentificarClienteController;
import org.tds.sgh.logic.Precondition.PreconditionException;

public class IdentificarClienteTest extends BaseTest
{
	// Constantes -------------------------------------------------------------
	
	private static final String nombre = "Roberto Martínez";

	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";


	private static final String nombre2 = "Gonzalo López";
	

	// Atributos --------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IIdentificarClienteController ic;

	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		ac = ControllerFactory.AltaClienteController(ch);
		ic = ControllerFactory.IdentificarClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ic = null;
	}

	
	// Tests ------------------------------------------------------------------

	// buscarClientes
	
	@Test
	public void testBuscarExacto()
	{
		ac.registrarCliente(nombre, telefono, email);
		
		List<IDatosCliente> clientes = ic.buscarCliente(nombre);
		
		assertTrue(clientes.size() == 1);
		assertTrue(clientes.get(0).getNombre().equals(nombre));
		assertTrue(clientes.get(0).getTelefono().equals(telefono));
		assertTrue(clientes.get(0).getEMail().equals(email));
	}
	
	@Test
	public void testBuscarExistente()
	{
		ac.registrarCliente(nombre, telefono, email);
		
		List<IDatosCliente> clientes = ic.buscarCliente("^Rober.*");
		
		assertTrue(clientes.size() == 1);
		assertTrue(clientes.get(0).getNombre().equals(nombre));
	}
	
	@Test
	public void testBuscarExistentes()
	{
		ac.registrarCliente(nombre, telefono, email);
		ac.registrarCliente(nombre2, telefono, email);
		
		List<IDatosCliente> clientes = ic.buscarCliente(".*o.*");
		
		assertTrue(clientes.size() == 2);
		assertTrue(clientes.get(0).getNombre().equals(nombre));
	}

	@Test
	public void testBuscarInexistenteEnVacio()
	{
		List<IDatosCliente> clientes = ic.buscarCliente("^Ana$");
		assertTrue(clientes.size() == 0);
	}

	@Test
	public void testBuscarInexistente()
	{
		ac.registrarCliente(nombre, telefono, email);
		ac.registrarCliente(nombre2, telefono, email);
		
		List<IDatosCliente> clientes = ic.buscarCliente("^Ana$");
		
		assertTrue(clientes.size() == 0);
	}

	
	// seleccionarCliente

	@Test
	public void testSeleccionarExistente()
	{
		ac.registrarCliente(nombre, telefono, email);
		
		IDatosCliente cliente = ic.seleccionarCliente(nombre);
		
		assertTrue(cliente.getNombre().equals(nombre));
		assertTrue(cliente.getTelefono().equals(telefono));
		assertTrue(cliente.getEMail().equals(email));
	}

	@Test
	public void testSeleccionarExistenteEnDos()
	{
		ac.registrarCliente(nombre, telefono, email);
		ac.registrarCliente(nombre2, telefono, email);
		
		IDatosCliente cliente = ic.seleccionarCliente(nombre);
		
		assertTrue(cliente.getNombre().equals(nombre));
		assertTrue(cliente.getTelefono().equals(telefono));
		assertTrue(cliente.getEMail().equals(email));

	
		cliente = ic.seleccionarCliente(nombre2);
		
		assertTrue(cliente.getNombre().equals(nombre2));
		assertTrue(cliente.getTelefono().equals(telefono));
		assertTrue(cliente.getEMail().equals(email));
	}

	@Test(expected=PreconditionException.class)
	public void testSeleccionarInexistenteEnVacio()
	{
		ic.seleccionarCliente(nombre);
	}

	@Test(expected=PreconditionException.class)
	public void testSeleccionarInexistente()
	{
		ac.registrarCliente(nombre, telefono, email);

		ic.seleccionarCliente(nombre2);
	}
}
