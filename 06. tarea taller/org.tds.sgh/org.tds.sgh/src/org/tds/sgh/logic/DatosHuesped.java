package org.tds.sgh.logic;


/*
 * Nelson Yañez C.
 * 
 */
public class DatosHuesped implements IDatosHuesped {
	public DatosHuesped(String nombre, String documento)
	{
		this.nombre=nombre;
		this.documento=documento;
	}
	private String nombre;
	private String documento;

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public String getDocumento() {
		// TODO Auto-generated method stub
		return documento;
	}



}
