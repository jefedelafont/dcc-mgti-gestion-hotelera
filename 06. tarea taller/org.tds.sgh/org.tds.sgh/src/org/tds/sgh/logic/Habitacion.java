package org.tds.sgh.logic;


public class Habitacion implements IDatosHabitacion
{
	// Atributos --------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	
	// Constructores ----------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.nombre = nombre;
		this.tipoHabitacion = tipoHabitacion;
	}
	

	// IDatosHabitacion -------------------------------------------------------

	@Override
	public String getNombre()
	{
		return nombre;
	}

	
	// Operaciones ------------------------------------------------------------

	TipoHabitacion getTipoHabitacion()
	{
		return this.tipoHabitacion;
	}
}
