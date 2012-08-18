package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tds.sgh.dto.ReservaDTO;

public class Cliente implements IDatosCliente {
	// Atributos --------------------------------------------------------------

	private String nombre;

	private String telefono;

	private String email;

	private Map<Long, Reserva> reservas;

	// Constructor ------------------------------------------------------------

	public Cliente(String nombre, String telefono, String email) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.reservas = new HashMap<Long, Reserva>();
	}

	// IDatosCliente ----------------------------------------------------------

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public String getEMail() {
		return email;
	}

	public IDatosCliente export() {
		DatosCliente dc = new DatosCliente(this.nombre, this.telefono,
				this.email);
		return dc;
	}

	/**
	 * Alvaro jose peralta ocampo
	 * 
	 * @return
	 */
	public List<IDatosReserva> buscarReservasPendientes() {
		List<IDatosReserva> lstIDatosReserva = new ArrayList<IDatosReserva>();
		for (Reserva reserva : reservas.values()) {
			if (reserva.isPendiente()) {
				IDatosReserva iDatosReserva = new ReservaDTO(reserva);
				lstIDatosReserva.add(iDatosReserva);
			}
		}
		return lstIDatosReserva;
	}

	// Operaciones ------------------------------------------------------------
	public void RegistrarReserva(Reserva reserva) {
		this.reservas.put(reserva.getCodigo(), reserva);
	}

	public Map<Long, Reserva> getReservas() {
		return reservas;
	}

}
