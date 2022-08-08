package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
	
	
	private static final String ER_TELEFONO = "(9|6)[0-9]{8}";
	private static final String ER_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private String nombre;
	private String correo;
	private String telefono;
	
	public Profesor(String nombre,String correo) {
		
		setNombre(nombre);
		setCorreo(correo);
	}
	
    public Profesor(String nombre,String correo,String telefono) {
    	
    	setNombre(nombre);
		setCorreo(correo);
		
		if(telefono != null && !telefono.matches(ER_TELEFONO)) {
		
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
			
			
		}else {
			
			setTelefono(telefono);
		}
		
		
	}  
    
    
    public Profesor(Profesor profesor) {
    	
        if(profesor == null) {
    		
    		throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
    	}
    	
    	setNombre(profesor.getNombre());
		setCorreo(profesor.getCorreo());
		setTelefono(profesor.getTelefono());
    	
    }
    
    private  void setNombre(String nombre) {
    	
    	if(nombre == null) {
    		
    		throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
    	}
    	
    	if(nombre.isEmpty()) {
    		
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
    	
    	this.nombre = nombre;
    	
    }
    
	public void setCorreo(String correo) {
		
            if(correo == null) {
    		
    		throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
    	}
            
            if(correo.isEmpty()) {
            	
    			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
    		}
            
            Pattern pattern = Pattern.compile(ER_CORREO); 
            Matcher mather = pattern.matcher(correo);
            
            if(mather.find() == false) {
            	
            	throw new NullPointerException("ERROR: El correo no sigue patron propuesto.");
            }
		
		this.correo = correo;
	}
	
	public void setTelefono(String telefono) {
		
		 if(telefono != null && !telefono.matches(ER_TELEFONO)) {
	    		
	    		throw new NullPointerException("ERROR: El teléfono del profesor no es válido.");
	    	}
		
		this.telefono = telefono;
	}
	
	
	public String getNombre() {
		
		return this.nombre;
	}
    

	public String getCorreo() {
		return this.correo;
	}



	public String getTelefono() {
		return this.telefono;
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
		//return Objects.equals(correo, other.correo) && Objects.equals(nombre, other.nombre)
		//		&& Objects.equals(telefono, other.telefono);
		
		return Objects.equals(this.getNombre(), other.getNombre());
			
	}

	@Override
	public String toString() {
		
		String cadenaTelefono;
		
        if(getTelefono() == null) {
			
			cadenaTelefono = "";
			
		}else {
			
			cadenaTelefono = ", telefono=" + getTelefono();
		}
        
		return "nombre=" + getNombre() + ", correo=" + getCorreo() + cadenaTelefono;
	}

	
    
    

}