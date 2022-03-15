package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {

	private String nombre;
	
	public Aula(String nombre) {
		
		setNombre(nombre);
		
	}
	
	public Aula(Aula aula) {
		
		if(aula == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}
		//new Aula(aula.getNombre());
		setNombre(aula.getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		}
		
		//isEmpty() devuelve true en el caso de que el tamaño de la cadena sera 0. 
		
		if(nombre.isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		}
		
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre Aula="  + getNombre() ;
	}
	
	
	
	
}
