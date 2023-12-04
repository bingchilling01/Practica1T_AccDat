package ejercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Se implementa la interfaz Serializable para poder leer y escribir en ficheros binarios
public class Prestamo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// LocalDate sirve para coger la fecha de AHORA
	private LocalDate hoy = LocalDate.now();
	// Y esto formatea la fecha al formato DÍA/MES/AÑO
	private DateTimeFormatter formatoDMA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// Atributos del préstamo
	private int idPrestamo;
	private int idLibro;
	private String usuario;
	private String fechaPrestamo;
	private String fechaDevolucion;

	// Constructor, que incluye el ID del préstamo, el ID del libro
	// prestado y el usuario que toma el libro prestado
	public Prestamo(int idPrestamo, int idLibro, String usuario) {
		this.idPrestamo = idPrestamo;
		this.idLibro = idLibro;
		this.usuario = usuario;
		// Tomamos como fecha de préstamo el momento de insertar dicho préstamo
		this.fechaPrestamo = hoy.format(formatoDMA);
		// La fecha de devolución está vacía porque aún no se ha devuelto el libro
		this.fechaDevolucion = "";
	}

	// Otro constructor, en éste se puede especificar el día del préstamo
	public Prestamo(int idPrestamo, int idLibro, String usuario, String fechaPrestamo) {
		this.idPrestamo = idPrestamo;
		this.idLibro = idLibro;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		// La fecha de devolución está vacía porque aún no se ha devuelto el libro
		this.fechaDevolucion = "";
	}

	// Otro constructor más, en éste se puede especificar todo, será útil para
	// importar y
	// exportar el XML
	public Prestamo(int idPrestamo, int idLibro, String usuario, String fechaPrestamo, String fechaDevolucion) {
		this.idPrestamo = idPrestamo;
		this.idLibro = idLibro;
		this.usuario = usuario;
		this.fechaPrestamo = fechaPrestamo;
		// La fecha de devolución está vacía porque aún no se ha devuelto el libro
		this.fechaDevolucion = fechaDevolucion;
	}

	// Getters
	public int getIdPrestamo() {
		return idPrestamo;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	// Setters
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	// Método que devuelve un String que sirve para imprimir los detalles del
	// préstamo
	public String detallesPrestamo() {
		return 	   "--------------------------------------------------------------------\n" + 
				   "Préstamo con ID: " + this.idPrestamo + "\n" +
				   "ID del libro prestado: " + this.idLibro + "\n" +
				   "Usuario que ha tomado el libro: " + this.usuario + "\n" + 
				   "Fecha del préstamo: " + this.fechaPrestamo + "\n" + 
				   "Fecha de devolución: " + this.fechaDevolucion + "\n" + 
				   "--------------------------------------------------------------------\n";
	}
	
	public static void nuevoPrestamo() {
		boolean idRepetido = true;
		int idNuevo = 0;
		while(idRepetido) {
			idNuevo = ES.leeEntero("ID del préstamo: ");
			idRepetido = false;
			ArrayList<String> lineasPrestamo = GestorFicherosTexto.leerFicheroPrestamos();
			if (lineasPrestamo.contains("Préstamo con ID: " + idNuevo)) {
				ES.msgErrln("Ya existe un préstamo con este ID, introduce otro");
				idRepetido = true;
			}
		}
		int idLibro = ES.leeEntero("ID del libro prestado: ");
		String usuario = ES.leeCadena("Nombre del usuario que ha tomado prestado el libro: ");
		String fechaPrestamo = ES.leeCadena("Fecha del préstamo (Si es hoy, pon HOY): ");
		if(fechaPrestamo.equalsIgnoreCase("hoy")) {
			GestorFicherosTexto.escribirFicheroPrestamosTXT(new Prestamo(idNuevo, idLibro, usuario));
		} else {
			GestorFicherosTexto.escribirFicheroPrestamosTXT(new Prestamo(idNuevo, idLibro, usuario, fechaPrestamo));
		}
		
	}
	
	public static void leerPrestamos() {
		ArrayList<String> lineasPrestamos = GestorFicherosTexto.leerFicheroPrestamos();
		if(!lineasPrestamos.isEmpty()) {
			ES.msgln("Préstamos registrados:\n");
			for (String linea : lineasPrestamos) {
				ES.msgln(linea);
			}
		} else {
			ES.msgErrln("No hay ningún préstamo registrado");
		}
	}
	
	public static void modificarPrestamo() {
		ArrayList<String> lineasPrestamos = GestorFicherosTexto.leerFicheroPrestamos();
		
		boolean prestamoEncontrado = false;
		
		while (!prestamoEncontrado) {
			
			int idBuscado = ES.leeEntero("ID del préstamo a modificar: ");
			
			if(lineasPrestamos.contains("Préstamo con ID: " + idBuscado)) {
				int idLibro = ES.leeEntero("Actualizar ID del libro prestado: ");
				String usuario = ES.leeCadena("Actualizar el usuario que ha tomado el libro: ");
				String fechaPrestamo = ES.leeCadena("Actualizar fecha de préstamo (si es hoy, pon HOY): ");
				String fechaDevolucion = ES.leeCadena("Actualizar fecha de devolución, si no se ha devuelto, déjalo vacío: ");
				
				int posicion = lineasPrestamos.indexOf("Préstamo con ID: " + idBuscado);
				
				lineasPrestamos.set(posicion-1, "--------------------------------------------------------------------");
				lineasPrestamos.set(posicion+0, "Préstamo con ID: " + idBuscado);
				lineasPrestamos.set(posicion+1, "ID del libro prestado: " + idLibro);
				lineasPrestamos.set(posicion+2, "Usuario que ha tomado el libro: " + usuario);
				lineasPrestamos.set(posicion+3, "Fecha del préstamo: " + fechaPrestamo);
				lineasPrestamos.set(posicion+4, "Fecha de devolución: " + fechaDevolucion);
				lineasPrestamos.set(posicion+5, "--------------------------------------------------------------------");
				
				GestorFicherosTexto.escribirModificacionesPrestamos(lineasPrestamos);
				
				ES.msgln("Préstamo actualizado");
				prestamoEncontrado = true;
			} else {
				ES.msgErrln("No hay ningún préstamo con ID: " + idBuscado);
			}
			
		}
		
	}
	
	public static void eliminarPrestamo() {
	    ArrayList<String> lineasPrestamos = GestorFicherosTexto.leerFicheroPrestamos();

	    boolean prestamoEncontrado = false;

	    while (!prestamoEncontrado) {

	        int idBuscado = ES.leeEntero("ID del préstamo a eliminar: ");

	        if(lineasPrestamos.contains("Préstamo con ID: " + idBuscado)) {
	            int posicion = lineasPrestamos.indexOf("Préstamo con ID: " + idBuscado);

	            for (int i = -1; i < 6; i++) {
	                lineasPrestamos.set(posicion+i, "");
	            }
	            
	            for (int i = lineasPrestamos.size() - 1; i >= 0; i--) {
	                if (lineasPrestamos.get(i).isEmpty()) {
	                    lineasPrestamos.remove(i);
	                }
	            }

	            GestorFicherosTexto.escribirModificacionesPrestamos(lineasPrestamos);

	            ES.msgln("Préstamo eliminado");
	            prestamoEncontrado = true;
	        } else {
	            ES.msgErrln("No hay ningún préstamo con ID: " + idBuscado);
	        }

	    }

	}

}
