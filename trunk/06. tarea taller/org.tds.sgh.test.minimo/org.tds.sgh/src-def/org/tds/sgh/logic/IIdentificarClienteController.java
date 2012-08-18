package org.tds.sgh.logic;

import java.util.List;


public interface IIdentificarClienteController
{
	List<IDatosCliente> buscarCliente(String nombreRegex);
	
	IDatosCliente seleccionarCliente(String nombre);
}
