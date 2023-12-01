package ejercicio;

import java.io.Serializable;
import java.util.ArrayList;

//Se implementa la interfaz Serializable para poder leer y escribir en ficheros binarios
public class Libro implements Serializable {
	
	// Atributos constantes y estáticos para definir el año de publicación MÍNIMO y MÁXIMO
	private static final int ANIO_MIN = 1;
	private static final int ANIO_MAX = 2023;

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

	// Método que devuelve un String que sirve para imprimir los detalles del libro
	public String detallesLibro() {
		return "--------------------------------------------------------------------\n" + 
			   "Libro con ID: " + this.id + "\n" +
			   "Título: " + this.titulo + "\n" +
			   "Autor: " + this.autor + "\n" + 
			   "Año de publicación: " + this.anioPublicacion + "\n" + 
			   "Género: " + this.genero + "\n" + 
			   "--------------------------------------------------------------------\n";
	}

	public static void nuevoLibro() {
		ArrayList<Libro> listaLibros = GestorFicherosBinarios.leerFicheroLibros();
		boolean idRepetido = true;
		int idNuevo = 0;
		while (idRepetido) {
			idNuevo = ES.leeEntero("Introduce el ID del libro nuevo: ");
			idRepetido = false;
			for (Libro libro : listaLibros) {
				if (libro.id == idNuevo) {
					ES.msgErrln("Este ID ya existe, introduce otro");
					idRepetido = true;
				} else {
					idRepetido = false;
				}
			}
		}

		if (!idRepetido) {
			String tituloNuevo = ES.leeCadena("Título del libro nuevo: ");
			String autorLibro = ES.leeCadena("Autor del libro: ");
			int anioPub = ES.leeEntero("Año de publicación (entre " + ANIO_MIN + " y " + ANIO_MAX + "): ", ANIO_MIN, ANIO_MAX);
			String generoLibro = ES.leeCadena("Género del libro: ");

			listaLibros.add(new Libro(idNuevo, tituloNuevo, autorLibro, anioPub, generoLibro));
			GestorFicherosBinarios.escribirFicheroLibros(listaLibros);
		}
	}

	// Método que imprime todos los libros por pantalla
	public static void imprimirLibros() {
		// Obtenemos la lista de libros llamando a la función de lectura
		ArrayList<Libro> listaLibros = GestorFicherosBinarios.leerFicheroLibros();
		// Si el ArrayList NO está vacío se imprime por pantalla todos los libros
		if (!listaLibros.isEmpty()) {
			for (Libro libro : listaLibros) {
				ES.msgln(libro.detallesLibro());
			}
		} else {
			ES.msgErrln("No hay libros");
		}
	}
	
	public static void modificarLibro() {
		ArrayList<Libro> listaLibros = GestorFicherosBinarios.leerFicheroLibros();
		
		boolean libroEncontrado = false;
		while(!libroEncontrado) {
			int idBuscado = ES.leeEntero("Introduce el ID del libro a modificar: ");
			for (Libro libro : listaLibros) {
				if(libro.id == idBuscado) {
					libro.titulo = ES.leeCadena("Nuevo título del libro: ");
					libro.autor = ES.leeCadena("Nuevo autor del libro: ");
					libro.anioPublicacion = ES.leeEntero("Año de publicación (entre " + ANIO_MIN + " y " + ANIO_MAX + "): ", ANIO_MIN, ANIO_MAX);
					libro.genero = ES.leeCadena("Género del libro: ");
					ES.msgln("Libro modificado");
					GestorFicherosBinarios.escribirFicheroLibros(listaLibros);
					libroEncontrado = true;
				}
			}
			if(!libroEncontrado) {
				ES.msgErrln("No hay ningún libro con el ID: " + idBuscado);
			}
		}
		
	}
	
	public static void eliminarLibro() {
		ArrayList<Libro> listaLibros = GestorFicherosBinarios.leerFicheroLibros();
		
		boolean libroEncontrado = false;
		while(!libroEncontrado) {
			int idBuscado = ES.leeEntero("Introduce el ID del libro a eliminar: ");
			for (Libro libro : listaLibros) {
				if(libro.id == idBuscado) {
					listaLibros.remove(libro);
					ES.msgln("Libro eliminado");
					GestorFicherosBinarios.escribirFicheroLibros(listaLibros);
					libroEncontrado = true;
				} else {
					ES.msg("No hay ningún libro con el ID: " + idBuscado);
				}
			}
		}
	}

}