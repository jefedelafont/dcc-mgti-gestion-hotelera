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
		return ch;
	}
	
	public static ITomarReservaController TomarReservaController(CadenaHotelera ch)
	{
		// TODO
		return ch;
	}

	public static ICancelarReservaController CancelarReservaController(CadenaHotelera ch)
	{
		return ch;
	}

	public static IIdentificarClienteController IdentificarClienteController(CadenaHotelera ch)
	{
		return ch;
	}

	public static IIdentificarReservaClienteController IdentificarReservaClienteController(CadenaHotelera ch)
	{
		// TODO
		return ch;
	}
}
