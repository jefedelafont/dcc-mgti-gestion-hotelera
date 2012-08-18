package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.tds.sgh.dto.ReservaDTO;



@Entity
public class Cliente implements IDatosCliente {
	// Atributos --------------------------------------------------------------

	private long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="codigo")
	public Map<Long, Reserva> getReservas() {
		return reservas;
	}

	protected void setId(long id) {
		this.id = id;
	}

	private String nombre;

	private String telefono;

	private String email;

	private Map<Long, Reserva> reservas;

	// Constructor ------------------------------------------------------------

	public void setReservas(Map<Long, Reserva> reservas) {
		this.reservas = reservas;
	}

	public Cliente(String nombre, String telefono, String email) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.reservas = new HashMap<Long, Reserva>();
	}

	// IDatosCliente ----------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

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



}
