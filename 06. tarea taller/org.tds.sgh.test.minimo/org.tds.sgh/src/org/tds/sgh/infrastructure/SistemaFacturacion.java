package org.tds.sgh.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaFacturacion
{
	// Logger -----------------------------------------------------------------
	
	private static final Logger log = LoggerFactory.getLogger(SistemaFacturacion.class);
	
	
	// Singleton --------------------------------------------------------------
	
	private static SistemaFacturacion _instance = null;
	
	public static SistemaFacturacion getInstance()
	{
		if (_instance == null)
		{
			_instance = new SistemaFacturacion();
		}
		
		return _instance;
	}
	
	private SistemaFacturacion()
	{
	}
	
	
	// Operations -------------------------------------------------------------
	
	public void iniciarEstadia(long codigoReserva)
	{
		log.debug("\niniciar estadia " + String.valueOf(codigoReserva));
		System.out.println("iniciar estadia " + String.valueOf(codigoReserva));
	}
}
