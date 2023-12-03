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
		return "--------------------------------------------------------------------\n" + 
				   "Préstamo con ID: " + this.idPrestamo + "\n" +
				   "ID del libro prestado: " + this.idLibro + "\n" +
				   "Usuario que ha tomado el libro: " + this.usuario + "\n" + 
				   "Fecha del préstamo: " + this.fechaPrestamo + "\n" + 
				   "Fecha de devolución: " + this.fechaDevolucion + "\n" + 
				   "--------------------------------------------------------------------\n";
	}
	
	public static void nuevoPrestamo() {
		
		int idNuevo = ES.leeEntero("ID del préstamo: ");
		int idLibro = ES.leeEntero("ID del libro prestado: ");
		String usuario = ES.leeCadena("Nombre del usuario que ha tomado prestado el libro: ");
		String fechaPrestamo = ES.leeCadena("Fecha del préstamo (Si es hoy, pon HOY): ");
		if(fechaPrestamo.equalsIgnoreCase("hoy")) {
			GestorFicherosTexto.escribirFicheroPrestamos(new Prestamo(idNuevo, idLibro, usuario));
		} else {
			GestorFicherosTexto.escribirFicheroPrestamos(new Prestamo(idNuevo, idLibro, usuario, fechaPrestamo));
		}
		
	}

}
