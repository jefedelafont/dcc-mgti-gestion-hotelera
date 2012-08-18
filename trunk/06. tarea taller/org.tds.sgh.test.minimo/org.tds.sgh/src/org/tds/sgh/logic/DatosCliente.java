package org.tds.sgh.logic;

public class DatosCliente implements IDatosCliente
{
	private String nombre;
	private String telefono;
	private String email;
	
	public DatosCliente(String nombre, String telefono, String email)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}
	
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

}
