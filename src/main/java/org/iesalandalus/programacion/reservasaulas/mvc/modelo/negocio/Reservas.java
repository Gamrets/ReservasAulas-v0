package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;

public class Reservas {

	private int capacidad; // cantidad maxima de elementos de array
	private int tamano; // cantidad de elemntos que hay en array

	private Reserva[] coleccionReservas;

	// cantidad de espacio que puedo ejercer a la array
	public Reservas(int capacidad) {

		if (capacidad <= 0) { // como la array no puede tener espacio negativo saco el error
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad; // inicializo el parametro
		coleccionReservas = new Reserva[capacidad]; // creo la array
	}

	public Reserva[] get() {

		return copiaProfundaReservas();
	}

	private Reserva[] copiaProfundaReservas() {

		Reserva[] copiaReserva = new Reserva[capacidad]; // creo una array de la misma capacidad que tiene array que
															// voy a
		// copiar

		for (int i = 0; !tamanoSuperado(i); i++) { // voy recorriendo el array

			copiaReserva[i] = new Reserva(coleccionReservas[i]); // y le voy asignado valores que tiene array
																		// original
		}

		return copiaReserva;

	}

	public int getTamano() {
		return tamano; // me devuelve numero de elmentos que hay actualmente en el array
	}

	public int getCapacidad() {
		return capacidad; // devuelve cuantos elementos en total puedo almacenar en el array
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}

		if (capacidadSuperada(tamano)) { // si array supera a la capacidad significa que esta lleno
			throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
		}

		int indice = buscarIndice(reserva);

		if (tamanoSuperado(indice)) { // Si supera al tamaño significa que hay huecos y se puede agregar otro
			coleccionReservas[tamano] = new Reserva(reserva);
			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");

		}

	}

	private int buscarIndice(Reserva reserva) { // recorre arry y localiza en que posicion se encuetnra reserva que
													// se esta
		// buscando y la devuelve

		int indice = tamano + 1;
		boolean encontrado = false;

		// la variable boleana ayuda a no recorrer el array si se encontro elemento que
		// se esta buscando

		for (int i = 0; !tamanoSuperado(i) && !encontrado; i++) {

			if (coleccionReservas[i].equals(reserva)) {
				encontrado = true;
				indice = i;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {

		return indice >= tamano;

	}

	private boolean capacidadSuperada(int indice) {

		return indice >= capacidad;

	}

	public Reserva buscar(Reserva reserva) { // Busco indice/posicion donde esta esta reserva

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}

		int indice = buscarIndice(reserva);// Localizo posicion en que se encuentra reserva

		if (tamanoSuperado(indice)) { // compara tamaño y resultados de la busqueda

			return null; // en caso q lo supera no hay reservas

		} else {

			return new Reserva(coleccionReservas[indice]); // y en otro se devulve una nueva reserva con ese indice
		}

	}

	public void borrar(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {

			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}

		int indice = buscarIndice(reserva); // compruebo donde esta reserva

		if (tamanoSuperado(indice)) { // si supera tamaño no esta

			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; i < coleccionReservas.length - 1; i++) {

			coleccionReservas[i] = coleccionReservas[i + 1];
		}

		coleccionReservas[i] = null;

	}

	// Se recorre todo el tamaño del array y se representan todos sus elementos

	public String[] representar() {

		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {

			representacion[i] = coleccionReservas[i].toString();

		}
		return representacion;
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {

		Reserva[] reserva = new Reserva[capacidad];

		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reserva[j++] = coleccionReservas[i];
			}
		}
		return reserva;

	}

	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reserva = new Reserva[capacidad];

		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reserva[j++] = coleccionReservas[i];
			}
		}
		return reserva;

	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {

		Reserva[] reserva = new Reserva[capacidad];

		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reserva[j++] = coleccionReservas[i];
			}
		}
		return reserva;

	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {

		boolean disponible = true;

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		for (int i = 0; !tamanoSuperado(i); i++) {
			if (aula.equals(coleccionReservas[i].getAula())
					&& permanencia.equals(coleccionReservas[i].getPermanencia())) {
				disponible = false;
			}

		}

		return disponible;
	}
}
