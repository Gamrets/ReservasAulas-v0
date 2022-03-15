package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;

public class Profesores {

	private int capacidad; // cantidad maxima de elementos de array
	private int tamano; // cantidad de elemntos que hay en array

	private Profesor[] coleccionProfesores;

	// cantidad de espacio que puedo ejercer a la array
	public Profesores(int capacidad) {

		if (capacidad <= 0) { // como la array no puede tener espacio negativo saco el error
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad; // inicializo el parametro
		coleccionProfesores = new Profesor[capacidad]; // creo la array
	}

	public Profesor[] get() {

		return copiaProfundaProfesores();
	}

	private Profesor[] copiaProfundaProfesores() {

		Profesor[] copiaProfesor = new Profesor[capacidad]; // creo una array de la misma capacidad que tiene array que
															// voy a
		// copiar

		for (int i = 0; !tamanoSuperado(i); i++) { // voy recorriendo el array

			copiaProfesor[i] = new Profesor(coleccionProfesores[i]); // y le voy asignado valores que tiene array
																		// original
		}

		return copiaProfesor;

	}

	public int getTamano() {
		return tamano; // me devuelve numero de elmentos que hay actualmente en el array
	}

	public int getCapacidad() {
		return capacidad; // devuelve cuantos elementos en total puedo almacenar en el array
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (capacidadSuperada(tamano)) { // si array supera a la capacidad significa que esta lleno
			throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");
		}

		int indice = buscarIndice(profesor);

		if (tamanoSuperado(indice)) { // Si supera al tamaño significa que hay huecos y se puede agregar otro
			coleccionProfesores[tamano] = new Profesor(profesor);
			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");

		}

	}

	private int buscarIndice(Profesor profesor) { // recorre arry y localiza en que posicion se encuetnra profesor que
													// se esta
		// buscando y la devuelve

		int indice = tamano + 1;
		boolean encontrado = false;

		// la variable boleana ayuda a no recorrer el array si se encontro elemento que
		// se esta buscando

		for (int i = 0; !tamanoSuperado(i) && !encontrado; i++) {

			if (coleccionProfesores[i].equals(profesor)) {
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

	public Profesor buscar(Profesor profesor) { // Busco indice/posicion donde esta esta profesor

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}

		int indice = buscarIndice(profesor);// Localizo posicion en que se encuentra profesor

		if (tamanoSuperado(indice)) { // compara tamaño y resultados de la busqueda

			return null; // en caso q lo supera no hay profesores

		} else {

			return new Profesor(coleccionProfesores[indice]); // y en otro se devulve una nuevo profesor con ese indice
		}

	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {

		if (profesor == null) {

			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}

		int indice = buscarIndice(profesor); // compruebo donde esta aula

		if (tamanoSuperado(indice)) { // si supera tamaño no esta

			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; i < coleccionProfesores.length - 1; i++) {

			coleccionProfesores[i] = coleccionProfesores[i + 1];
		}

		coleccionProfesores[i] = null;

	}

	// Se recorre todo el tamaño del array y se representan todos sus elementos

	public String[] representar() {

		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {

			representacion[i] = coleccionProfesores[i].toString();

		}
		return representacion;
	}

}
