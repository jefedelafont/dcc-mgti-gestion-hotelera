package org.tds.sgh.logic;

public class ControllerFactory
{
	public static IAltaClienteController AltaClienteController(CadenaHotelera ch)
	{
		return ch;
	}
	
	public static IAltaHotelController AltaHotelController(CadenaHotelera ch)
	{
		return ch;
	}
	
	public static IHacerReservaController HacerReservaController(CadenaHotelera ch)
	{
		// TODO
		return null;
	}
	
	public static ITomarReservaController TomarReservaController(CadenaHotelera ch)
	{
		// TODO
		return null;
	}

	public static ICancelarReservaController CancelarReservaController(CadenaHotelera ch)
	{
		// TODO
		return null;
	}

	public static IIdentificarClienteController IdentificarClienteController(CadenaHotelera ch)
	{
		// TODO
		return null;
	}

	public static IIdentificarReservaClienteController IdentificarReservaClienteController(CadenaHotelera ch)
	{
		// TODO
		return null;
	}
}
