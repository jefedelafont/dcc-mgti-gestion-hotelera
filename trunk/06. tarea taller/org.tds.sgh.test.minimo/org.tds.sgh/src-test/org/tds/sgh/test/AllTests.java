package org.tds.sgh.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
	AltaClienteTest.class,
	AltaHotelTest.class,
	IdentificarClienteTest.class,
	HacerReservaTest.class,
	CancelarReservaTest.class,
	TomarReservaTest.class,
	IdentificarReservaClienteTest.class
})
public class AllTests
{
}
