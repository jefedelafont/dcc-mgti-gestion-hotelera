package org.tds.sgh.test;

import org.junit.After;
import org.junit.Before;
import org.tds.sgh.logic.CadenaHotelera;

public class BaseTest
{
	// Atributos --------------------------------------------------------------
	
	protected CadenaHotelera ch = null;
	
	
	// Templates --------------------------------------------------------------
	
	protected CadenaHotelera createCadenaHotelera()
	{
		return new CadenaHotelera();
	}
	
	
	// Configurar entorno -----------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		ch = createCadenaHotelera();
	}

	@After
	public void tearDown() throws Exception
	{
		ch = null;
	}
}
