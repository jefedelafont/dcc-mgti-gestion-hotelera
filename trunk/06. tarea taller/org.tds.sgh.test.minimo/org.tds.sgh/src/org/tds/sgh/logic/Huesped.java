package org.tds.sgh.logic;

public class Huesped {

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

}
