package paquetePersona;

public class Persona {
	public String nombre;
	public int edad;
	public char sexo;
	public float peso;
	public float altura;
	public int dni;
	
	public Persona(String nombre, int edad,char sexo, float peso, float altura) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		
	}
	
	
	public Persona(String nombre, int edad,char sexo) {
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
	}
	
	public Persona() {
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public float getPeso() {
		return peso;
	}


	public void setPeso(float peso) {
		this.peso = peso;
	}


	public float getAltura() {
		return altura;
	}


	public void setAltura(float altura) {
		this.altura = altura;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public float calcularIMC() {
	    return peso / (altura * altura);
	}
	
	public boolean esMayorDeEdad() {
	    if (edad >= 18) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void comprobarSexo() {
	    if (sexo != 'H' && sexo != 'M') {
	        this.sexo = 'H';
	    }
	}


	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", sexo=" + sexo + ", peso=" + peso + ", altura="
				+ altura + ", dni=" + dni + "]";
	}
	

	
	
}
