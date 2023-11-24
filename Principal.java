package ejercicio;

public class Principal {
	
	private static GestorFicheros gestorFicheros = new GestorFicheros();

	public static void main(String[] args) {
		boolean salir = false;
		while (!salir) {
			mostrarMenu();
			int opcion = ES.leeEntero("Seleccione una opción: ");
			switch (opcion) {
			case 1:
				// Gestionar libros
				gestionarLibros();
				break;
			case 2:
				// Gestionar autores
				gestionarAutores();
				break;
			case 3:
				// Gestionar préstamos
				gestionarPrestamos();
				break;
			case 4:
				// Exportar/Importar datos con XML
				gestionarExportImportXML();
				break;
			case 5:
				salir = true;
				break;
			default:
				ES.msgErrln(errorOpcion);
			}
		}
	}
	
	private final static String errorOpcion = "Opción no válida. Por favor, intente de nuevo.";

	private static void mostrarMenu() {
		ES.msgln("Bienvenido al Sistema de Gestión de Biblioteca");
		ES.msgln("1. Gestionar Libros");
		ES.msgln("2. Gestionar Autores");
		ES.msgln("3. Gestionar Préstamos");
		ES.msgln("4. Exportar/Importar Datos (XML)");
		ES.msgln("5. Salir");
	}
	
	private static void mostrarSubMenu(String tipo) {
		ES.msgln("Bienvenido a la Gestión de " + tipo);
		ES.msgln("1. Insertar nuevo " + tipo);
		if(tipo.equalsIgnoreCase("autor")) {
			ES.msgln("2. Listar todos los " + tipo + "es");
		} else {
			ES.msgln("2. Listar todos los " + tipo + "s");
		}
		ES.msgln("3. Modificar un " + tipo);
		ES.msgln("4. Eliminar un " + tipo);
		ES.msgln("5. Salir al menú principal");
	}

	private static void gestionarLibros() {
		boolean salirMenuLibros = false;
		while(!salirMenuLibros) {
			mostrarSubMenu("libro");
			int opcionLibros = ES.leeEntero("Seleccione una opción: ");
			switch (opcionLibros) {
			case 1:
				
				break;
				
			case 2: 
				
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				salirMenuLibros = true;
				break;
			
			default:
				ES.msgErrln(errorOpcion);
			}
		}
		
		
	}

	private static void gestionarAutores() {
		boolean salirMenuAutores = false;
		while(!salirMenuAutores) {
			mostrarSubMenu("autor");
			int opcionAutor = ES.leeEntero("Seleccione una opción: ");
			switch (opcionAutor) {
			case 1:
				
				break;
				
			case 2: 
				
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				salirMenuAutores = true;
				break;
			
			default:
				ES.msgErrln(errorOpcion);
			}
		}
	}

	private static void gestionarPrestamos() {
		boolean salirMenuPrestamos = false;
		while(!salirMenuPrestamos) {
			mostrarSubMenu("préstamo");
			int opcionPrestamos = ES.leeEntero("Seleccione una opción: ");
			switch (opcionPrestamos) {
			case 1:
				
				break;
				
			case 2: 
				
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				salirMenuPrestamos = true;
				break;
			
			default:
				ES.msgErrln(errorOpcion);
			}
		}
	}

	private static void gestionarExportImportXML() {
		// Implementar lógica para exportar/importar datos con XML
	}
	// Otros métodos según sea necesario
}