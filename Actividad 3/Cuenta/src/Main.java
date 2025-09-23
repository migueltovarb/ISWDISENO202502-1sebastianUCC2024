import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear dos cuentas de ejemplo
        Cuenta cuenta1 = new Cuenta("001", "Juan", 1000);
        Cuenta cuenta2 = new Cuenta("002", "Sebas", 500);

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== MENÚ DE CUENTAS =====");
            System.out.println("1. Ver información de cuentas");
            System.out.println("2. Depositar dinero (credito)");
            System.out.println("3. Retirar dinero (debito)");
            System.out.println("4. Transferir dinero");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Información de cuentas ---");
                    System.out.println("Cuenta 1 -> ID: " + cuenta1.getId() + ", Nombre: " + cuenta1.getNombre() + ", Balance: " + cuenta1.getBalance());
                    System.out.println("Cuenta 2 -> ID: " + cuenta2.getId() + ", Nombre: " + cuenta2.getNombre() + ", Balance: " + cuenta2.getBalance());
                    break;

                case 2:
                    System.out.print("Ingresa la cuenta (1 o 2): ");
                    int c1 = sc.nextInt();
                    System.out.print("Cantidad a depositar: ");
                    int deposito = sc.nextInt();
                    if (c1 == 1) {
                        int nuevoBalance = cuenta1.credito(deposito);
                        System.out.println("Depósito realizado. Nuevo balance de cuenta 1: " + nuevoBalance);
                    } else {
                        int nuevoBalance = cuenta2.credito(deposito);
                        System.out.println("Depósito realizado. Nuevo balance de cuenta 2: " + nuevoBalance);
                    }
                    break;

                case 3:
                    System.out.print("Ingresa la cuenta (1 o 2): ");
                    int c2 = sc.nextInt();
                    System.out.print("Cantidad a retirar: ");
                    int retiro = sc.nextInt();
                    if (c2 == 1) {
                        int nuevoBalance = cuenta1.debito(retiro);
                        System.out.println("Retiro realizado. Nuevo balance de cuenta 1: " + nuevoBalance);
                    } else {
                        int nuevoBalance = cuenta2.debito(retiro);
                        System.out.println("Retiro realizado. Nuevo balance de cuenta 2: " + nuevoBalance);
                    }
                    break;

                case 4:
                    System.out.print("Transferir de cuenta (1 o 2): ");
                    int origen = sc.nextInt();
                    System.out.print("Hacia cuenta (1 o 2): ");
                    int destino = sc.nextInt();
                    System.out.print("Cantidad a transferir: ");
                    int monto = sc.nextInt();

                    if (origen == 1 && destino == 2) {
                        int nuevoBalance = cuenta1.trasferir(cuenta2, monto);
                        System.out.println("Transferencia realizada. Nuevo balance de cuenta 1: " + nuevoBalance);
                    } else if (origen == 2 && destino == 1) {
                        int nuevoBalance = cuenta2.trasferir(cuenta1, monto);
                        System.out.println("Transferencia realizada. Nuevo balance de cuenta 2: " + nuevoBalance);
                    } else {
                        System.out.println("Transferencia no válida.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        sc.close();
    }
}
