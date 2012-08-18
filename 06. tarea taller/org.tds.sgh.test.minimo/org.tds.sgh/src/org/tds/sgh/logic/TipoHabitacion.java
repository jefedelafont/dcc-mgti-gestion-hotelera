package org.tds.sgh.logic;


public class TipoHabitacion implements IDatosTipoHabitacion
{
	// Atributos --------------------------------------------------------------
	
	private String nombre;
	
	
	// Constructores ----------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// IDatosTipoHabitacion ---------------------------------------------------
	
	@Override
	public String getNombre()
	{
		return this.nombre;
	}
}
