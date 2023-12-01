package ejercicio;

import java.io.File;
import java.io.FileWriter;

public class GestorFicherosTexto {
	
	private static File archivoPrestamos = new File(GestorFicherosBinarios.rutaCarpetaRaiz + "prestamos.yaml");
	
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
