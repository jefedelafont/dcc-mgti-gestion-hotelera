package org.tds.sgh.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Huesped {

	private long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	
	private String nombre;
	private String documento;

	public Huesped(String nombre, String documento) {
		this.nombre = nombre;
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	
}
