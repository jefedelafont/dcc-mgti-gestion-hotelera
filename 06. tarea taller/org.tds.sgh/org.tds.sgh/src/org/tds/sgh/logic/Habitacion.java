package org.tds.sgh.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Habitacion implements IDatosHabitacion
{
	
	private long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	
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
	@OneToOne(cascade=CascadeType.ALL)
	public TipoHabitacion getTipoHabitacion()
	{
		return this.tipoHabitacion;
	}

	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public boolean eresDelTIpo(TipoHabitacion tipoHabitacion) {
		return this.tipoHabitacion.equals(tipoHabitacion);
	}
}
