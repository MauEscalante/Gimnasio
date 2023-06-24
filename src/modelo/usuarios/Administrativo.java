package modelo.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import modelo.baseDeDatos.LimiteClasesException;
import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.NoEsRentableException;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Excepciones.ProfesorNoDisponibleException;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Administrativo extends Usuario {

	private ArrayList<Sede> sedes;

	public Administrativo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.sedes = new ArrayList<>();

	}

	public void agregarSede(Sede sede) {

		sedes.add(sede);
	}

	public ArrayList<Sede> getSedes() {
		return sedes;
	}

	public void agendarClase(String nroDNIProfesor, Sede sede, String nombreClase, Actividad actividad,
			Emplazamiento emplazamiento, LocalDateTime fecha, int duracionClase) throws Exception {

		Profesor profesor = sede.buscarProfesor(nroDNIProfesor);
		

		try {
			if (profesor.estoyDisponbile(fecha)) {
				Clase clase = new Clase(profesor, sede, nombreClase, actividad, emplazamiento, fecha, duracionClase);
				sede.agregarClase(clase);
				profesor.agregarClase(clase);
			}
		} catch (ProfesorNoDisponibleException e) {
			e.printStackTrace();
		}

	}


	public void cambiarEstadoClase(Clase clase, EstadoClase estadoClase) throws LimiteClasesException, NoEsRentableException {
		clase.cambiarEstado(estadoClase);
	}

	public void crearCliente(String nombre, String apellido, String dni, Nivel nivel) {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni, nivel));

	}

	public void bajaCliente(Cliente cliente) {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		gimnasio.eliminarUsuario(cliente);

	}

	/*
	 * agrega articulo a sede, necesita mandar cantidad de articulos que quiere //
	 * agregarle a la sede
	 */

	public void agregarArticulos(Sede sede, Articulo articulo, int cantidad) {
		sede.agregarArticuloAStock(articulo, cantidad);

	}

	public double consultarDesgaste(Articulo articulo) {

		return articulo.calcularDesgaste();
	}

	@Override
	public boolean soySoporteTecnico() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getId() {

		return this.id;
	}

	@Override
	public boolean soyAdministrativo() {
		return true;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	public void asignarEmplazamientoSede(Sede s, Emplazamiento em) {

		s.agregarEmplazamiento(em);
	}

	@Override
	public boolean soyCliente() {
		return false;
	}

	public ArrayList<Articulo> listarArticulosSede(Sede s) {
		return s.listarArticulos();
	}

	public Map<Articulo, Integer> visualizarDesgasteArticulos(Sede s) {
		return s.visualizarDesgasteArticulos();
	}

	public void darBajaArticulo(Sede s, Articulo art) {
		
		s.darBajaArticulo(art);
	}

	public void confirmarClase(Clase cla) throws LimiteClasesException, NoEsRentableException {
		
		cla.cambiarEstado(EstadoClase.FINALIZADA);
	}

	public double verRentabilidadClase(Clase cla) {
		
		return cla.rentabilidadClase();
	}

}
