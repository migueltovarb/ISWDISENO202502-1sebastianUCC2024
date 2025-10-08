package paqueteSistema;

import java.time.LocalDate;

public class ControlVeterinario {
	private LocalDate fecha;
	private TipoControl tipoControl;
	private String observaciones;
	
	public ControlVeterinario(LocalDate fecha, TipoControl tipoControl, String observaciones) {
		super();
		this.fecha = fecha;
		this.tipoControl = tipoControl;
		this.observaciones = observaciones;
	}


	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;
	}

	public TipoControl getTipoControl() {
		return tipoControl;
	}

	public void setTipoControl(TipoControl tipoControl) {
		this.tipoControl = tipoControl;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "ControlVeterinario [fecha=" + fecha + ", tipoControl=" + tipoControl + ", observaciones="
				+ observaciones + "]";
	}


	

	
}
