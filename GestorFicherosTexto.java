package ejercicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class GestorFicherosTexto extends GestorFicheros {

	// Ruta del archivo de texto de préstamos
	static File archivoPrestamosTXT = new File(rutaCarpetaRaiz + "prestamos.txt");
	static File archivoPrestamosAUX = new File(rutaCarpetaRaiz + "prestamos_aux.txt");
	private static File archivoPrestamosYAML = new File(rutaCarpetaRaiz + "prestamos.yaml");

	// Método para escribir los datos de un préstamo en el fichero en formato YAML,
	// NO usado
	public static void escribirFicheroPrestamosYAML(Prestamo prestamo) {
		comprobarDirectorios();
		try {
			FileWriter escritorArchivoPrestamos = new FileWriter(archivoPrestamosYAML, true);
			if(archivoPrestamosYAML.length() == 0) {
				escritorArchivoPrestamos.write("prestamos:\n" + 
											   " prestamo:\n");
			}
			
			escritorArchivoPrestamos.write(
											   "  - id_prestamo: " + prestamo.getIdPrestamo() + "\n" +
											   "    id_libro_prestado: " + prestamo.getIdLibro() + "\n" +
											   "    usuario: " + prestamo.getUsuario() + "\n" +
											   "    fecha_prestamo: " + prestamo.getFechaPrestamo() + "\n" +
											   "    fecha_devolucion: " + prestamo.getFechaDevolucion() + "\n");
			
			escritorArchivoPrestamos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método para escribir los datos de un préstamo en el fichero en formato TXT
	public static void escribirFicheroPrestamosTXT(Prestamo prestamo) {
		comprobarDirectorios();
				
		try {
			
			FileWriter escritorArchivoPrestamos = new FileWriter(archivoPrestamosTXT, true);
			
			escritorArchivoPrestamos.write(prestamo.detallesPrestamo());
			
			escritorArchivoPrestamos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void escribirModificacionesPrestamos(ArrayList<String> lineasPrestamos) {
		comprobarDirectorios();
		try {
			FileWriter escritorArchivoPrestamos = new FileWriter(archivoPrestamosTXT, false);
			
			for (String linea : lineasPrestamos) {
				escritorArchivoPrestamos.write(linea+"\n");
			}
			escritorArchivoPrestamos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static ArrayList<String> leerFicheroPrestamos() {
		comprobarDirectorios();
		
		if(!archivoPrestamosTXT.exists()) {
			try {
				archivoPrestamosTXT.createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		ArrayList<String> lineasPrestamo = new ArrayList<>();
		
		try {
			if(archivoPrestamosTXT.length() > 0) {
				
				String linea;
				BufferedReader lectorArchivoPrestamos = new BufferedReader(new FileReader(archivoPrestamosTXT));
				
				while ((linea = lectorArchivoPrestamos.readLine()) != null) {
					lineasPrestamo.add(linea);
				}
				
				lectorArchivoPrestamos.close();
		}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return lineasPrestamo;
	}
	
}
