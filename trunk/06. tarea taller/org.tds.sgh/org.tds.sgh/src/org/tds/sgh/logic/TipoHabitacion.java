package org.tds.sgh.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoHabitacion implements IDatosTipoHabitacion 
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
	
	
	// Constructores ----------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// IDatosTipoHabitacion ---------------------------------------------------
	
	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
