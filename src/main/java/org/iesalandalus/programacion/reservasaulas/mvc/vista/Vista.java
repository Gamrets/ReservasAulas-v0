package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista {

	private static final String ERROR = "ERROR";
	private static final String NOMBRE_VALIDO = "NOMBRE_VALIDO";
	private static final String CORREO_VALIDO = "CORREO_VALIDO";
	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Programa para la reserva de aula");
		int ordinalOpcion;
		do {
			
			Consola.mostrarMenu();
			ordinalOpcion = (int) Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
			
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		
		System.out.println("Adios");

	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Aula ha sido insertada");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.borrarAula(aula);
			System.out.println("Aula ha sido borrada");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = controlador.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("No existe ningún aula con ese nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		String[] aulas = controlador.representarAulas();
		if (aulas.length != 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("Profesor ha sido insertado" );
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("Profesor ha sido borrado");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		String[] profesores = controlador.representarProfesores();
		if (profesores.length != 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}

	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada correctamente");
		} catch (NullPointerException | OperationNotSupportedException  | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void anularReserva() {
		Consola.mostrarCabecera("Anular Reserva");
		try {
			Profesor profesor = Consola.leerProfesor();
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("Reserva anulada correctamente.");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {
		Consola.mostrarCabecera("Listar Reservas");
		String[] reservas = controlador.representarReservas();
		if (reservas.length != 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
				
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}

	public void listarReservasProfesor() {
		try {

			Consola.mostrarCabecera("Listar Reservas Profesor");
			Profesor profesor = Consola.leerProfesor();
			Reserva[] reservasProfesor = controlador.getReservasProfesor(profesor);
			if (reservasProfesor.length != 0) {
				for (int i = 0; i <= reservasProfesor.length; i++) {

					if (reservasProfesor[i] == null && i >= 1) {

						throw new NullPointerException("No existen mas reservas para este profesor");

					} else if (reservasProfesor[i] == null) {

						throw new NullPointerException("No existen reservas para este profesor");
						
					} else {

						System.out.println(reservasProfesor[i]);
					}
				}
			} else {
				System.out.println(profesor.getNombre() + " no tiene ninguna reserva a su nombre.");
			}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservasPermanencia() {
		try {
			
		
		Consola.mostrarCabecera("Listar Reservas Permanencia");
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		Reserva[] reservasPermanencia = controlador.getReservasPermanencia(permanencia);
		if (reservasPermanencia.length != 0) {
			
			for (int i = 0; i <= reservasPermanencia.length; i++) {

				if (reservasPermanencia[i] == null && i >= 1) {

					throw new NullPointerException("No existen mas reservas para esta permanencia");

				} else if (reservasPermanencia[i] == null) {

					throw new NullPointerException("No existen reservas para esta permanencia");
					
				} else {

					System.out.println(reservasPermanencia[i]);
				}
			}
		} else {
			System.out.println(permanencia.getDia() + ": no hay ninguna reserva.");
		}

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void consultarDisponibilidad() {
		try{
		Consola.mostrarCabecera("Consultar Disponibilidad");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		if (controlador.consultarDisponibilidad(aula, permanencia)) {
			System.out.println(aula + " se encuentra disponible el dia " + permanencia.getDia() + ".");
		} else {
			System.out.println(aula + " no se encuentra disponible el dia " + permanencia.getDia() + ".");
		}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Leer Reserva");
		Aula aula = Consola.leerAula();
		Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
		return new Reserva(profesor, aula, permanencia);
	}

	public void listarReservasAula() {

		try {
			Consola.mostrarCabecera("Listar Reservas Aula");
			Aula aula = Consola.leerAula();
			Reserva[] reservasAulas = controlador.getReservasAula(aula);
			if (reservasAulas.length >=1) {
				
				
				for(int i = 0; i <=reservasAulas.length; i++ ) {
					
					if(reservasAulas[i] == null && i >= 1) {
						
						throw new NullPointerException("No existen mas reservas para este aula");
						
					}else if(reservasAulas[i] == null){
						
						throw new NullPointerException("No existen reservas para este aula");
					}else {
						
						System.out.println(reservasAulas[i]);
					}
				}				
			} else {
				System.out.println(aula.getNombre() + " no tiene ninguna reserva a su nombre.");
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}