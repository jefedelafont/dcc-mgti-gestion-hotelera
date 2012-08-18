package org.tds.sgh.logic;

public interface ICancelarReservaController extends IIdentificarClienteController, IIdentificarReservaClienteController, IConfirmarReservaController
{
	void cancelarReserva(long codigoReserva);
}
