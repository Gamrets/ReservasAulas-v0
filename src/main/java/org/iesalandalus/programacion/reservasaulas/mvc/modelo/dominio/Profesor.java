package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Profesor {

	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";
	private static final String ER_CORREO = ".+@[a-zA-Z]+\\\\.[a-zA-Z]+";
	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo, String telefono) {

		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(String nombre, String correo) {

		setNombre(nombre);
		setCorreo(correo);

	}

	public Profesor(Profesor profesor) {

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}

		setNombre(profesor.getNombre());
		setCorreo(profesor.getCorreo());
		setTelefono(profesor.getTelefono());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}

		// isEmpty() devuelve true en el caso de que el tamaño de la cadena sera 0.

		if (nombre.isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}

		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {

		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}

		// isEmpty() devuelve true en el caso de que el tamaño de la cadena sera 0.

		if (correo.isEmpty()) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}

		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono != null && !telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		}

		this.telefono = telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		String cadenaTelefono = (getTelefono() == null) ? "" : ", telefono=" + getTelefono();
		return "nombre=" + getNombre() + ", correo=" + getCorreo() + cadenaTelefono;
	}

}
