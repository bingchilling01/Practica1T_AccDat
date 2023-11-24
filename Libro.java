package ejercicio;

import java.io.Serializable;

//Se implementa la interfaz Serializable para poder leer y escribir en ficheros binarios
public class Libro implements Serializable {
	
	// Atributos del libro
	private int id;
	private String titulo;
	private String autor;
	private int anioPublicacion;
	private String genero;
	
	// Constructor, que incluye como parámetros todos los atributos,
	// útil cuando queramos insertar un libro nuevo
	public Libro(int id, String titulo, String autor, int anioPub, String genero) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anioPub;
		this.genero = genero;
	}

	// Getters
	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public int getAnioPublicacion() {
		return anioPublicacion;
	}
	public String getGenero() {
		return genero;
	}

	// Setters, no hay setter de ID porque no se va a cambiar
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	// Método que devuelve un String que sirve para imprimir los detalles del autor
	public String imprimirLibro() {
		return "--------------------------------------------------------------------" + 
			   "Libro con ID: " + this.id + "\n" +
			   "Título: " + this.titulo + "\n" +
			   "Autor: " + this.autor + "\n" + 
			   "Año de publicación: " + this.anioPublicacion + "\n" + 
			   "Género: " + this.genero + "\n" + 
			   "--------------------------------------------------------------------\n";		
	}
	
	
		
}