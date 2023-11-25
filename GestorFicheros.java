package ejercicio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class GestorFicheros {

	// Ruta donde se va a guardar todos los ficheros
	private static final String rutaAbsoluta = "C:/Practica1T_AD/";

	// Gestión de ficheros de LIBROS
	// Fichero binario de los libros y su fichero copia de seguridad
	private static File archivoLibros = new File(rutaAbsoluta + "libros.bin");
	private static File archivoRespaldoLibros = new File(rutaAbsoluta + "respaldos/libros_bin.bak");

	// Método para insertar un libro nuevo en su archivo binario
	public void escribirLibroNuevo(Libro nuevoLibro) {
		try {

			// El true en el constructor de FileOutputStream significa que escribe
			// al final del archivo, sin eliminar su contenido antes
			FileOutputStream streamSalida = new FileOutputStream(archivoLibros, true);

			// Si el archivo está vacío se escribirá el nuevo libro con la cabecera
			if (archivoLibros.length() == 0) {
				ObjectOutputStream escribirLibro = new ObjectOutputStream(streamSalida);
				escribirLibro.writeObject(nuevoLibro);
				escribirLibro.close();
			} else {
				// Guardamos una copia de seguridad antes de insertar el libro nuevo, si
				// el archivo binario NO está vacío
				guardarRespaldoLibros();

				// Si no está vacío se escribirá sin la cabecera para evitar errores de lectura
				EscritorSinCabecera escribirSinCabecera = new EscritorSinCabecera(streamSalida);
				escribirSinCabecera.writeObject(nuevoLibro);
				escribirSinCabecera.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método que escribe un ArrayList de libros en el archivo binario, se usará
	// este método para cuando el usuario quiera modificar o eliminar libros
	private void escribirModificacionesLibros(ArrayList<Libro> listaLibros) {
		try {

			// Guardamos una copia de seguridad antes de modificar libros
			guardarRespaldoLibros();
			// En esta escritura borraremos el contenido el archivo y la
			// sobreescribiremos con el ArrayList de libros
			ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(archivoLibros, false));
			// Con este bucle escribimos todos los libros que hay en el ArrayList
			// en el archivo binario
			for (Libro libro : listaLibros) {
				escritura.writeObject(libro);
			}
			escritura.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método para leer todos los libros del archivo binario
	public ArrayList<Libro> leerFicheroLibros() {
		ArrayList<Libro> listaLibros = new ArrayList<>();
		boolean eof = false;
		try {

			// Si el archivo binario no existe, lo creo, para evitar un
			// FileNotFoundException
			if (!archivoLibros.exists()) {
				archivoLibros.createNewFile();
			}

			// Declaramos un ObjectInputStream para poder leer el archivo binario
			ObjectInputStream leerLibros = new ObjectInputStream(new FileInputStream(archivoLibros));

			// El ObjectInputStream lee el archivo hasta que llegue al EOF
			while (!eof) {
				Libro libro = (Libro) leerLibros.readObject();
				// Añadimos los libros al ArrayList
				listaLibros.add(libro);
			}
			// Cerramos el ObjectInputStream para evitar fallos
			leerLibros.close();

			// Cuando se llegue al EOF se lanzará una excepción, aquí finalizamos
			// la lectura
		} catch (EOFException eofEx) {
			eof = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listaLibros;
	}

	// Método que imprime todos los libros por pantalla
	public void imprimirLibros(ArrayList<Libro> listaLibros) {
		// Obtenemos la lista de libros llamando a la función de lectura
		listaLibros = leerFicheroLibros();
		// Si el ArrayList NO está vacío se imprime por pantalla todos los libros
		if (!listaLibros.isEmpty()) {
			for (Libro libro : listaLibros) {
				libro.imprimirLibro();
			}
		} else {
			ES.msgErrln("No hay libros");
		}
	}

	public void guardarRespaldoLibros() {
		ArrayList<Libro> listaLibros = new ArrayList<>();
		// Guardamos los libros en el ArrayList llamando a la función de lectura
		listaLibros = leerFicheroLibros();
		try {

			ObjectOutputStream escribirRespaldoLibros = new ObjectOutputStream(new FileOutputStream(archivoRespaldoLibros));

			// Escribimos todos los libros del ArrayList en el archivo de respaldo
			for (Libro libro : listaLibros) {
				escribirRespaldoLibros.writeObject(libro);
			}
			escribirRespaldoLibros.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void importarLibrosXML() {

	}

	public void exportarLibrosXML() {

	}

	
	
	// Gestión de ficheros de AUTORES
	// Fichero binario de los libros y su fichero copia de seguridad
	private static File archivoAutores = new File(rutaAbsoluta + "autores.bin");
	private static File archivoRespaldoAutores = new File(rutaAbsoluta + "respaldos/autores_bin.bak");

	// Método para insertar un libro nuevo en su archivo binario
	public void escribirAutorNuevo(Autor nuevoAutor) {
		try {

			// El true en el constructor de FileOutputStream significa que escribe
			// al final del archivo, sin eliminar su contenido antes
			FileOutputStream streamSalida = new FileOutputStream(archivoAutores, true);

			// Si el archivo está vacío se escribirá el nuevo autor con la cabecera
			if (archivoAutores.length() == 0) {
				ObjectOutputStream escribirAutor = new ObjectOutputStream(streamSalida);
				escribirAutor.writeObject(nuevoAutor);
				escribirAutor.close();
			} else {
				// Guardamos una copia de seguridad antes de insertar el autor nuevo, si
				// el archivo binario NO está vacío
				guardarRespaldoLibros();

				// Si no está vacío se escribirá sin la cabecera para evitar errores de lectura
				EscritorSinCabecera escribirSinCabecera = new EscritorSinCabecera(streamSalida);
				escribirSinCabecera.writeObject(nuevoAutor);
				escribirSinCabecera.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método que escribe un ArrayList de autores en el archivo binario, se usará
	// este método para cuando el usuario quiera modificar o eliminar autores
	private void escribirModificacionesAutores(ArrayList<Autor> listaAutores) {
		try {

			// Guardamos una copia de seguridad antes de modificar autores
			guardarRespaldoAutores();
			// En esta escritura borraremos el contenido el archivo y la
			// sobreescribiremos con el ArrayList de autores
			ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(archivoAutores, false));
			// Con este bucle escribimos todos los autores que hay en el ArrayList
			// en el archivo binario
			for (Autor autor : listaAutores) {
				escritura.writeObject(autor);
			}
			escritura.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método para leer todos los autores del archivo binario
	public ArrayList<Autor> leerFicheroAutores() {
		ArrayList<Autor> listaAutores = new ArrayList<>();
		boolean eof = false;
		try {

			// Si el archivo binario no existe, lo creamos, para evitar un
			// FileNotFoundException
			if (!archivoAutores.exists()) {
				archivoAutores.createNewFile();
			}

			// Declaramos un ObjectInputStream para poder leer el archivo binario
			ObjectInputStream leerAutores = new ObjectInputStream(new FileInputStream(archivoAutores));

			// El ObjectInputStream lee el archivo hasta que llegue al EOF
			while (!eof) {
				Autor autor = (Autor) leerAutores.readObject();
				// Añadimos los libros al ArrayList
				listaAutores.add(autor);
			}
			// Cerramos el ObjectInputStream para evitar fallos
			leerAutores.close();

			// Cuando se llegue al EOF se lanzará una excepción, aquí finalizamos
			// la lectura del archivo
		} catch (EOFException eofEx) {
			eof = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listaAutores;
	}

	public void imprimirAutores(ArrayList<Autor> listaAutores) {
		// Obtenemos la lista de autores llamando a la función de lectura
		listaAutores = leerFicheroAutores();
		// Si el ArrayList NO está vacío se imprime por pantalla todos los autores
		if (!listaAutores.isEmpty()) {
			for (Autor autor : listaAutores) {
				autor.imprimirAutor();
			}
		} else {
			ES.msgErrln("No hay libros");
		}
	}

	public void guardarRespaldoAutores() {
		// Declaramos un ArrayList de autores
		ArrayList<Autor> listaAutores = new ArrayList<>();
		// Guardamos los autores en el ArrayList con la función de lectura
		listaAutores = leerFicheroAutores();
		try {

			ObjectOutputStream escribirRespaldoAutores = new ObjectOutputStream(new FileOutputStream(archivoRespaldoAutores));

			// Escribimos todos los autores del ArrayList en el archivo de respaldo
			for (Autor autor : listaAutores) {
				escribirRespaldoAutores.writeObject(autor);
			}
			escribirRespaldoAutores.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void importarAutoresXML() {

	}

	public void exportarAutoresXML() {

	}
}