package org.tds.sgh.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tds.sgh.dto.HotelDTO;

public class CadenaHotelera implements IDatosCadenaHotelera,
                                       IAltaHotelController,
                                       IAltaClienteController,
                                       IIdentificarClienteController,
                                       IHacerReservaController,ITomarReservaController
                                       
                                       
{
	
	
	private static CadenaHotelera instance;
	// MAREL
	public static CadenaHotelera getInstance() {
		if ( instance == null ) instance = new CadenaHotelera();
		
		return instance;
	}
	
	private static long numeracionReservas = 0;
	
	// Atributos --------------------------------------------------------------
	
	private Map<String,Cliente> clientes;
	
	private Map<String,Hotel> hoteles;
	
	private Map<String,TipoHabitacion> tiposHabitacion;
	
	private Hotel hotelEnUso;

	// Constructores ----------------------------------------------------------
	public CadenaHotelera()
	{
		this.clientes = new HashMap<String,Cliente>();
		this.hoteles = new HashMap<String,Hotel>();
		this.tiposHabitacion = new HashMap<String,TipoHabitacion>();
	}
	

	// IDatosCadenaHotelera ---------------------------------------------------
	
	@Override
	public Map<String,IDatosCliente> listarClientes()
	{
		return Collections.<String,IDatosCliente>unmodifiableMap(clientes);
	}
	
	@Override
	public Map<String,IDatosHotel> listarHoteles()
	{
		return Collections.<String,IDatosHotel>unmodifiableMap(hoteles);
	}
	
	@Override
	public Map<String,IDatosTipoHabitacion> listarTiposHabitacion()
	{
		return Collections.<String,IDatosTipoHabitacion>unmodifiableMap(tiposHabitacion);
	}
	
	@Override
	public Map<String,IDatosHabitacion> listarHabitaciones(String nombreHotel)
	{
		Precondition.contains(hoteles, nombreHotel, "El hotel '" + nombreHotel + "' no est� registrado");
		return hoteles.get(nombreHotel).listarHabitaciones();
	}

	@Override
	public Map<Long,IDatosReserva> listarReservasCliente(String nombreCliente)
	{
		//TODO
		return null;
	}
	
	@Override
	public Map<Long, IDatosReserva> listarReservasHotel(String nombreHotel)
	{
		//TODO
		return null;
	}
	
	@Override
	public IDatosTipoHabitacion obtenerTipoHabitacionDeHabitacion(String nombreHotel, String nombreHabitacion)
	{
		Precondition.contains(hoteles, nombreHotel, "El hotel '" + nombreHotel + "' no est� registrado");
		return hoteles.get(nombreHotel).obtenerTipoHabitacionDeHabitacion(nombreHabitacion);
	}

	@Override
	public IDatosTipoHabitacion obtenerTipoHabitacionDeReserva(long codigo)
	{
		//TODO
		return null;
	}

	@Override
	public IDatosHabitacion obtenerHabitacionDeReserva(long codigo)
	{
		//TODO
		return null;
	}
	
	
	// IAltaHotel -------------------------------------------------------------
	
	@Override
	public IDatosHotel registrarHotel(String nombre)
	{
		Precondition.notContain(hoteles, nombre, "Ya existe un hotel con el nombre '" + nombre + "'");
		
		Hotel h = new Hotel(nombre);
		hoteles.put(nombre, h);
		return h;
	}
	
	@Override
	public IDatosTipoHabitacion registrarTipoHabitacion(String nombre)
	{
		Precondition.notContain(tiposHabitacion, nombre, "Ya existe un tipo de habitaci�n con el nombre '" + nombre + "'");
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		tiposHabitacion.put(nombre, tipoHabitacion);
		return tipoHabitacion;
	}
	
	@Override
	public IDatosHabitacion registrarHabitacion(String nombreHotel, String nombreTipoHabitacion, String nombre)
	{
		Precondition.contains(hoteles, nombreHotel, "No existe un hotel con el nombre '" + nombreHotel + "'");
		Precondition.contains(tiposHabitacion, nombreTipoHabitacion, "No existe un tipo de habitaci�n con el nombre '" + nombreTipoHabitacion + "'");
		
		Hotel hotel = hoteles.get(nombreHotel);
		TipoHabitacion tipoHabitacion = tiposHabitacion.get(nombreTipoHabitacion);
		
		return hotel.registrarHabitacion(tipoHabitacion, nombre);
	}
	

	// IAltaCliente -----------------------------------------------------------
	
	@Override
	public IDatosCliente registrarCliente(String nombre, String telefono, String email)
	{
		Precondition.notContain(clientes, nombre, "Ya existe un cliente con el nombre '" + nombre + "'");
		
		Cliente cliente = new Cliente(nombre, telefono, email);
		clientes.put(nombre, cliente);
		return cliente;
	}


	@Override
	public IDatosCliente seleccionarCliente(String nombre)
	{
		Cliente c = clientes.get(nombre);
		IDatosCliente dc = c.export();
		return dc;
	}


	// MAREL
	@Override
	public void confirmarReserva(long codigoReserva) {
		hotelEnUso.confirmarReserva(codigoReserva);
		
	}

	
	/**
	 * Marel
	 */
	@Override
	public boolean confirmarDisponibilidad(String nombreHotel,
			String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) {
		
		hotelEnUso = hoteles.get(nombreHotel);
		return hotelEnUso.confirmarDisponibilidad(nombreTipoHabitacion, fechaInicio, fechaFin);
	}


	@Override
	public List<IDatosHotel> sugerirAlternativas(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		
		List<IDatosHotel> hotelesDTO = new ArrayList<IDatosHotel>(); 
		
		for ( Hotel hotel : hoteles.values() ) {
			if ( hotel.confirmarDisponibilidad(nombreTipoHabitacion, fechaInicio, fechaFin) ) {
				hotelesDTO.add(new HotelDTO(hotel));
			}
		}
		
		return hotelesDTO;
	}


	@Override
	public IDatosReserva registrarReserva(String nombreCliente,
			String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin,
			boolean modificablePorHuesped) {
		
		numeracionReservas+= 1;
		
		Hotel hotel = this.hoteles.get(nombreHotel);
		Cliente cliente = this.clientes.get(nombreCliente);
		TipoHabitacion tipoHabitacion = this.tiposHabitacion.get(nombreTipoHabitacion);
		
		return hotel.registrarReserva(numeracionReservas, cliente, tipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);

	}

	
	/**
	 * Alvaro Jose Peralta ocampo
	 */
	@Override
	public List<IDatosReserva> buscarReservasPendientes(String nombreCliente) {
		Cliente cliente = clientes.get(nombreCliente);
		return cliente.buscarReservasPendientes();
	}


	/**
	 * Alvaro jose peralta ocampo
	 */
	@Override
	public List<IDatosReserva> buscarReservasNoTomadas(String nombreHotel,
			GregorianCalendar fecha) {
		// busca en la lista de hoteles el hotel
		Hotel hotel = this.hoteles.get(nombreHotel);
		return hotel.buscarReservasNoTomadas(fecha);
	}


	@Override
	public IDatosReserva seleccionarReserva(long codigoReserva) {
		return hotelEnUso.seleccionarReserva(codigoReserva);
	}


	@Override
	public IDatosHuesped registrarHuesped(long codigoReserva, String nombre,
			String documento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDatosHabitacion tomarReserva(long codigoReserva) { 
		return  hotelEnUso.tomarReserva(codigoReserva);
	}
	
	/**
	 * Nelson Yanez
	 */
	public List<IDatosCliente> buscarCliente(String nombreRegex)
	{
		//**restona los datos de cliente que cumplen con contener tring nombreRegex **//
		List<IDatosCliente> clis = new ArrayList<IDatosCliente>();
			
		for(Cliente cli:this.clientes.values())
		{
			if (cli.getNombre().matches(nombreRegex)) {
		
				clis.add(new DatosCliente(cli.getNombre(), cli.getTelefono(),cli.getEMail()));
				}
			
		}
		return clis;
	}
}

