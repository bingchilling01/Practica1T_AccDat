package ejercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Se implementa la interfaz Serializable para poder leer y escribir en ficheros binarios
public class Prestamo implements Serializable { 
	
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
	
}
