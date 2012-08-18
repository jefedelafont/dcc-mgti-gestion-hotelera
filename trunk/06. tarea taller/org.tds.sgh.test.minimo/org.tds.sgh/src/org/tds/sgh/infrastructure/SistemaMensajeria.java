package org.tds.sgh.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaMensajeria
{
	// Logger -----------------------------------------------------------------
	
	private static final Logger log = LoggerFactory.getLogger(SistemaMensajeria.class);
	
	
	// Singleton --------------------------------------------------------------
	
	private static SistemaMensajeria _instance = null;
	
	public static SistemaMensajeria getInstance()
	{
		if (_instance == null)
		{
			_instance = new SistemaMensajeria();
		}
		
		return _instance;
	}
	
	private SistemaMensajeria()
	{
	}
	
	
	// Operations -------------------------------------------------------------
	
	public void enviarMensaje(String email, String asunto, String texto)
	{
		log.debug("\nmail a " + email + " : " + asunto);
		System.out.print("mail a " + email + " : " + asunto + (texto != null ? "\n" + texto : ""));
	}
}
