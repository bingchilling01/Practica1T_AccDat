package ejercicio;

import java.io.File;
import java.io.FileWriter;

public final class GestorFicherosTexto extends GestorFicheros{
	
	// Ruta del archivo de texto de préstamos
	private static File archivoPrestamos = new File(rutaCarpetaRaiz + "prestamos.yaml");
	
	// Método para escribir los datos de un préstamo en el fichero en formato YAML
	public static void escribirFicheroPrestamos(Prestamo prestamo) {
		
		try {
			FileWriter escritorArchivoPrestamos = new FileWriter(archivoPrestamos, true);
			if(archivoPrestamos.length() == 0) {
				escritorArchivoPrestamos.write("prestamos:\n" + 
											   " prestamo:\n");
			}
			
			escritorArchivoPrestamos.write(
											   "  - id_prestamo: " + prestamo.getIdPrestamo() + "\n" +
											   "    id_libro: " + prestamo.getIdLibro() + "\n" +
											   "    usuario: " + prestamo.getUsuario() + "\n" +
											   "    fecha_prestamo: " + prestamo.getFechaPrestamo() + "\n" +
											   "    fecha_devolucion: " + prestamo.getFechaDevolucion() + "\n");
			
			escritorArchivoPrestamos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
