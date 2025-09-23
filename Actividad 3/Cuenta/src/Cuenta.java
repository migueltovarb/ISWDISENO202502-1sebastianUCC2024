public class Cuenta {
    String id;
    String nombre;
    int balance = 0;

    public Cuenta(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Cuenta(String id, String nombre, int balance){
        this.id = id;
        this.nombre = nombre;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBalance() {
        return balance;
    }

    public int credito(int cantidad){
        balance = balance + cantidad;
        return balance;
    }

    public int debito(int cantidad){
        if(cantidad <= balance){
            this.balance -= cantidad;
        } else {
            System.out.println("Fondos insuficientes");
        }
        return balance;
    }

    public int trasferir(Cuenta cuenta,int cantidad){
        if(cantidad <= balance){
            this.balance -= cantidad;
            cuenta.credito(cantidad);
        } else {
            System.out.println("Fondos insuficientes");
        }
        return balance;
    }
}
