package ejercicio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorFicheros {
	
	// Método para insertar un libro nuevo en su archivo binario
	public void escribirLibroNuevo(Libro nuevoLibro, String filename) {
		try {
			File archivoLibros = new File(filename);
			FileOutputStream streamSalida = new FileOutputStream(archivoLibros, true);
			
			// Si el archivo está vacío se escribirá el nuevo libro con la cabecera
			if(archivoLibros.length() == 0) {
				ObjectOutputStream escribirLibros = new ObjectOutputStream(streamSalida);
				escribirLibros.writeObject(nuevoLibro);
				escribirLibros.close();
			} else {
			// Si no está vacío se escribirá sin la cabecera para evitar errores de lectura
				EscritorSinCabecera escribirSinCabecera = new EscritorSinCabecera(streamSalida);
				escribirSinCabecera.writeObject(nuevoLibro);
				escribirSinCabecera.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método para leer todos los libros del archivo binario
	public void leerLibrosBinario(String filename) {
		ArrayList<Libro> listaLibros = new ArrayList<>();
		boolean eof = false;
		try {
			File archivoLibros = new File(filename);
			// Si el archivo binario no existe, lo creo, para evitar un FileNotFoundException
			if (!archivoLibros.exists()) {
				archivoLibros.createNewFile();
			}
			
			// Declaramos un ObjectInputStream para poder leer el archivo binario
			ObjectInputStream leerLibros = new ObjectInputStream(new FileInputStream(filename));
			
			// El ObjectInputStream lee el archivo hasta que llegue al EOF
			while (!eof) {
				Libro libro = (Libro) leerLibros.readObject();
				// Añadimos los libros al ArrayList
				listaLibros.add(libro);
			}
			// Cerramos el ObjectInputStream para evitar fallos
			leerLibros.close();
			
			// Cuando se llegue al EOF se lanzará una excepción, y aquí continuamos
			// la ejecución
		} catch (EOFException eofEx) {
			eof = true;
			// Si el ArrayList NO está vacío se imprime por pantalla todos los libross
			if(!listaLibros.isEmpty()) {
				for (Libro libro : listaLibros) {
					libro.imprimirLibro();
				}
			} else {
				ES.msgErrln("No hay libros");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}