public class Factura {
    String id;
    String descripcion;
    int cantidad;
    double precioUnidad;

    public Factura(String id, String descripcion, int cantidad, double precioUnidad){
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double totalCompra(){
        return cantidad * precioUnidad;
    }


}
