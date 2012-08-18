package org.tds.sgh.logic;


public class Cliente implements IDatosCliente
{
	// Atributos --------------------------------------------------------------
	
	private String nombre;
	
	private String telefono;
	
	private String email;

	
	// Constructor ------------------------------------------------------------
	
	public Cliente(String nombre, String telefono, String email)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}

	
	// IDatosCliente ----------------------------------------------------------
	
	@Override
	public String getNombre()
	{
		return nombre;
	}

	@Override
	public String getTelefono()
	{
		return telefono;
	}

	@Override
	public String getEMail()
	{
		return email;
	}


	public IDatosCliente export()
	{
		DatosCliente dc = new DatosCliente(this.nombre, this.telefono, this.email);
		return dc;
	}

	
	// Operaciones ------------------------------------------------------------

	
}
