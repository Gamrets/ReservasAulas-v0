package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;

public class Aulas {

	private int capacidad; // cantidad maxima de elementos de array
	private int tamano; // cantidad de elemntos que hay en array

	private Aula[] coleccionAulas;

	// cantidad de espacio que puedo ejercer a la array
	public Aulas(int capacidad) {

		if (capacidad <= 0) { // como la array no puede tener espacio negativo saco el error
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad; // inicializo el parametro
		coleccionAulas = new Aula[capacidad]; // creo la array
	}

	public Aula[] get() {

		return copiaProfundaAulas();
	}

	private Aula[] copiaProfundaAulas() {

		Aula[] copiaAulas = new Aula[capacidad]; // creo una array de la misma capacidad que tiene array que voy a
													// copiar

		for (int i = 0; !tamanoSuperado(i); i++) { // voy recorriendo el array

			copiaAulas[i] = new Aula(coleccionAulas[i]); // y le voy asignado valores que tiene array original
		}

		return copiaAulas;

	}

	public int getTamano() {
		return tamano; // me devuelve numero de elmentos que hay actualmente en el array
	}

	public int getCapacidad() {
		return capacidad; // devuelve cuantos elementos en total puedo almacenar en el array
	}

	public void insertar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}

		if (capacidadSuperada(tamano)) { // si array supera a la capacidad significa que esta lleno
			throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");
		}

		int indice = buscarIndice(aula);

		if (tamanoSuperado(indice)) { //Si supera al tamaño significa que hay huecos y se puede agregar otra
			coleccionAulas[tamano] = new Aula(aula);
			tamano++;

		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");

		}

	}

	private int buscarIndice(Aula aula) { // recorre arry y localiza en que posicion se encuetnra aula que se esta
		// buscando y la devuelve

		int indice = tamano + 1;
		boolean encontrado = false;

		// la variable boleana ayuda a no recorrer el array si se encontro elemento que
		// se esta buscando

		for (int i = 0; !tamanoSuperado(i) && !encontrado; i++) {

			if (coleccionAulas[i].equals(aula)) {
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

	public Aula buscar(Aula aula) { // Busco indice/posicion donde esta esta aula

		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}

		int indice = buscarIndice(aula);// Localizo posicion en que se encuentra aula

		if (tamanoSuperado(indice)) { // compara tamaño y resultados de la busqueda

			return null; // en caso q lo supera no aulas

		} else {

			return new Aula(coleccionAulas[indice]); // y en otro se devulve una nueva aula con es indice
		}

	}

	public void borrar(Aula aula) throws OperationNotSupportedException {

		if (aula == null) {

			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}

		int indice = buscarIndice(aula); // compruebo donde esta aula

		if (tamanoSuperado(indice)) { // si supera tamaño no esta

			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");

		} else {

			desplazarUnaPosicionHaciaIzquierda(indice);
			tamano--;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; i < coleccionAulas.length - 1; i++) {

			coleccionAulas[i] = coleccionAulas[i + 1];
		}

		coleccionAulas[i] = null;

	}

	// Se recorre todo el tamaño del array y se representan todos sus elementos

	public String[] representar() {

		String[] representacion = new String[tamano];
		for (int i = 0; !tamanoSuperado(i); i++) {

			representacion[i] = coleccionAulas[i].toString();

		}
		return representacion;
	}

}
