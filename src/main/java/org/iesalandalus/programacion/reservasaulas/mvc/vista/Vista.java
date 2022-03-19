package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.*;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;

public class Vista {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido";
	private static final String CORREO_VALIDO = "Correo válido";

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {

		Consola.mostrarCabecera("Gestión de las Reservas de Aulas del IES Al-Ándalus");

		int ordinalOpcion;

		do {

			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();

		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {

		controlador.terminar();

	}

	public void insertarAula() {

		Consola.mostrarCabecera("Insertar aula");

		try {
			controlador.insertarAula(Consola.leerAula());

			System.out.println("Aula ha sido insertada.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {

		Consola.mostrarCabecera("Borrar aula");

		try {

			controlador.borrarAula(Consola.leerAula());

			System.out.println("Aula ha sido borrada.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {

		Consola.mostrarCabecera("Buscar aula");

		Aula aula;
		try {

			aula = controlador.buscarAula(Consola.leerAula());
			String mensaje;

			if (aula != null) {

				mensaje = aula.toString();

			} else {

				mensaje = "Aula no  esta registrada en el sistema.";
			}

			System.out.println(mensaje);

		} catch (NullPointerException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de aulas");

		String[] aulas = controlador.representarAulas();

		if (aulas.length > 0) {

			for (String aula : aulas) {

				System.out.println(aula);
			}

		} else {

			System.out.println(ERROR + "No hay aulas que listar.Debe insertar primero un aula en el sistema.");
		}
	}

	public void insertarProfesor() {

		Consola.mostrarCabecera("Insertar profesor");

		try {

			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor ha sido insertado.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {

		Consola.mostrarCabecera("Borrar profesor");

		try {

			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Profesor  ha sido borrado.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {

		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor;

		try {

			profesor = controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje;

			if (profesor != null) {

				mensaje = profesor.toString();

			} else {

				mensaje = "El profesor no está registrado en el sistema.";
			}

			System.out.println(mensaje);

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {

		Consola.mostrarCabecera("Listado de profesores");

		String[] profesores = controlador.representarProfesores();

		if (profesores.length > 0) {

			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {

			System.out
					.println(ERROR + "No hay profesores que listar. Debe insertar primero un profesor en el sistema.");
		}
	}

	public void realizarReserva() {

		try {

			Profesor profesor = null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("Reserva  ha sido insertada, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO + ".");

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {

		Consola.mostrarCabecera("Realizar Reserva");

		String nombreAula;
		String nombreProfesor;
		String[] profesores = controlador.representarProfesores();
		String[] aulas = controlador.representarAulas();
		String correoProfesor = new String();
		String correoProfesorLimpio = new String();

		Reserva reserva = null;
		Aula aula = null;
		Permanencia permanencia = null;
		boolean aulaRegistrada = false;
		boolean profesorRegistrado = false;

		try {
			nombreProfesor = Consola.leerNombreProfesor();
			nombreAula = Consola.leerNombreAula();

			for (int i = 0; i < profesores.length; i++) {

				String datosProfesores = profesores[i].toString();

				if (nombreProfesor.equals(profesor.getNombre())) {

					profesorRegistrado = true;
					correoProfesor = profesor.getCorreo();

				}
			}

			for (int j = 0; j < aulas.length; j++) {

				if (aulas[j].equals(nombreAula)) {

					aula = new Aula(nombreAula);
					aulaRegistrada = true;

				}
			}

			if (!profesorRegistrado) {
				System.out.println(ERROR + "No está registrado el profesor " + nombreProfesor + " en el sistema.");

			} else if (!aulaRegistrada) {
				System.out.println(ERROR + "No está registrada el aula" + nombreAula + " en el sistema.");

			}

			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorLimpio);

			reserva = new Reserva(profesorALeer, aula, permanencia);

		} catch (IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());
		}

		return reserva;

		return null;

	}

	public void aunlarReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {

			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Rserva  ha sido anulada.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}

	}

	public void listarReservas() {

		Consola.mostrarCabecera("Listado de reservas");

		String[] reservas = controlador.representarReservas;

		if (reservas.length > 0) {

			for (String reserva : reservas) {

				System.out.println(reserva);
			}

		} else {

			System.out.println(ERROR + "No hay reservas que listar. Debe insertar primero una reserva en el sistema.");
		}
	}

	public void listarReservasAula() {

		Consola.mostrarCabecera("Listado de reservas de aulas");

		Reserva[] reservas = controlador.getReservasAulas(Consola.leerAula());

		for (Reserva reserva : reservas) {

			if (reserva != null)

				System.out.println(reserva);
		}

	}

	public void listarReservasProfesor() {

		Consola.mostrarCabecera("Listado de reservas de profesores");

		Reserva[] reservas = controlador.getReservasProfesores(Consola.leerProfesor());

		for (Reserva reserva : reservas) {

			if (reserva != null)

				System.out.println(reserva);
		}

	}

	public void listarReservasPermanencia() {

		Consola.mostrarCabecera("Listado de reservas permanencia");

		Reserva[] reservas = controlador.getReservasPermanencia(Consola.leerDia(), Consola.leerTramo());

		for (Reserva reserva : reservas) {

			if (reserva != null)

				System.out.println(reserva);
		}

	}

	public void consultarDisponibilidad() {

		Consola.mostrarCabecera("Consultar Disponibilidad");
		String nombreAula;
		String[] aulas = controlador.representarAulas();
		LocalDate dia;
		Tramo tramo;

		boolean aulaRegistrada = false;

		try {

			nombreAula = Consola.leerNombreAula();

			for (int i = 0; i < aulas.length; i++) {

				if (aulas[i].equals(nombreAula)) {

					aulaRegistrada = true;

					Aula aulaAConsultar = new Aula(nombreAula);
					dia = Consola.leerDia();
					tramo = Consola.leerTramo();
					Permanencia permanencia = new Permanencia(dia, tramo);

					if (controlador.consultarDisponibilidad(aulaAConsultar, permanencia) == true) {

						System.out.println("El aula " + nombreAula + " esta disponible el día " + dia + " de " + tramo);

					} else {

						System.out.println(
								"El  no aula " + nombreAula + " esta disponible el día " + dia + " de " + tramo);
					}

				}
			}

			if (!aulaRegistrada) {

				System.out.println(ERROR + "No está registrada el aula " + nombreAula + " en el sistema.");

			}

		} catch (IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());

		}

	}

}
