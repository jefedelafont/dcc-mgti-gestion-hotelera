package org.tds.sgh.dto;

import org.tds.sgh.logic.Huesped;
import org.tds.sgh.logic.IDatosHuesped;

public class HuespedDTO implements IDatosHuesped{

	private String nombre;
	private String documento;

	public HuespedDTO(Huesped huesped) {
		this.nombre = huesped.getNombre();
		this.documento = huesped.getDocumento();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}

}
