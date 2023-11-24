package ejercicio;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class EscritorSinCabecera extends ObjectOutputStream {

	public EscritorSinCabecera(OutputStream salida) throws IOException {
		super(salida);
	}
	
	@Override
	protected void writeStreamHeader() throws IOException {
		// Sin cabecera
	}
}