package ejercicio;

import java.io.Serializable;

//Se implementa la interfaz Serializable para poder leer y escribir en ficheros binarios
public class Autor implements Serializable {

	// Atributos del autor
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anioNacimiento;

	// Constructor, que incluye como parámetros todos los atributos,
	// útil cuando queramos insertar un autor nuevo
	public Autor(int id, String nombre, String nacionalidad, int anioNacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.anioNacimiento = anioNacimiento;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	// Setters, no hay setter de ID porque no se va a cambiar
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	// Método que devuelve un String que sirve para imprimir los detalles del libro
	public String imprimirAutor() {
		return "--------------------------------------------------------------------" + 
			   "Autor con ID: " + this.id + "\n" + 
			   "Nombre: " + this.nombre + "\n" + 
			   "Nacionalidad: " + this.nacionalidad + "\n" + 
			   "Año nacimiento: " + this.anioNacimiento + "\n" + 
			   "--------------------------------------------------------------------\n";
	}

}