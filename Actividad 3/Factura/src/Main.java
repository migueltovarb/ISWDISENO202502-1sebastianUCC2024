

public class Main {
    public static void main(String[] args) {
        Factura factura1 = new Factura("3","Leche",5,2500);
        Factura factura2 = new Factura("5","Pan", 6, 250);

        System.out.println("Descripcion del producto: " + factura1.getDescripcion());
        System.out.println("Cantidad del producto: " + factura1.getCantidad());
        System.out.println("Precio por unidad: " + factura1.getPrecioUnidad());
        System.out.println("Total de la compra: " + factura1.totalCompra());







    }
}