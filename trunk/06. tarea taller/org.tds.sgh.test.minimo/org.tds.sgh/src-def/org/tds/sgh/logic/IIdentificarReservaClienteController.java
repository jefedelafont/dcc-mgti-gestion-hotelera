package org.tds.sgh.logic;

import java.util.List;

public interface IIdentificarReservaClienteController
{
	List<IDatosReserva> buscarReservasPendientes(String nombreCliente);
	
	IDatosReserva seleccionarReserva(long codigoReserva);
}
