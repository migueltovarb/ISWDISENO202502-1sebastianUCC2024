package paqueteSistema;

public class Dueno {
	private String nombreCompleto;
	private String documento;
	private String telofono;
	
	public Dueno(String nombreCompleto, String documento, String telofono) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.documento = documento;
		this.telofono = telofono;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelofono() {
		return telofono;
	}

	public void setTelofono(String telofono) {
		this.telofono = telofono;
	}

	@Override
	public String toString() {
		return "Dueno [nombreCompleto=" + nombreCompleto + ", documento=" + documento + ", telofono=" + telofono + "]";
	}
	
	
	
	

}
