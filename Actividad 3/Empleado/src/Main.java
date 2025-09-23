import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Empleado empleado1 = new Empleado(1, "Juan", "Patiño", 2000);

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== MENÚ EMPLEADO =====");
            System.out.println("1. Ver información del empleado");
            System.out.println("2. Ver nombre completo");
            System.out.println("3. Ver salario anual");
            System.out.println("4. Aumentar salario");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(empleado1);
                    break;
                case 2:
                    System.out.println("Nombre completo: " + empleado1.getCompletoNombre());
                    break;
                case 3:
                    System.out.println("Salario anual: " + empleado1.salarioAnual());
                    break;
                case 4:
                    System.out.print("Ingresa el porcentaje de aumento: ");
                    int porcentaje = sc.nextInt();
                    int nuevoSalario = empleado1.aumentoSalarial(porcentaje);
                    System.out.println("El nuevo salario es: " + nuevoSalario);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        sc.close();
    }
}
