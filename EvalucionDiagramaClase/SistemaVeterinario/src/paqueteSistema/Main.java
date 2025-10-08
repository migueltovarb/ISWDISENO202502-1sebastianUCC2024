package paqueteSistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Dueno> duenos = new ArrayList<>();
    private static List<Mascota> mascotas = new ArrayList<>();
    private static List<ControlVeterinario> controles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== SISTEMA VETERINARIO ===");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Registrar mascota");
            System.out.println("3. Registrar control veterinario");
            System.out.println("4. Consultar historial médico de mascota");
            System.out.println("5. Generar resumen por mascota");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine().trim();

            if (!esNumero(entrada)) {
                System.out.println("Error: debe ingresar un número válido.");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    registrarDueno(sc);
                    break;
                case 2:
                    registrarMascota(sc);
                    break;
                case 3:
                    registrarControl(sc);
                    break;
                case 4:
                    consultarHistorial(sc);
                    break;
                case 5:
                    generarResumen(sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error: opción fuera de rango. Intente de nuevo.");
            }
        }
    }

    private static boolean esNumero(String texto) {
        if (texto.isEmpty()) return false;
        for (char c : texto.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static void registrarDueno(Scanner sc) {
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Documento: ");
        String documento = sc.nextLine().trim();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine().trim();

        if (nombre.isEmpty() || documento.isEmpty() || telefono.isEmpty()) {
            System.out.println("Error: todos los campos son obligatorios.");
            return;
        }

        for (Dueno d : duenos) {
            if (d.getDocumento().equals(documento)) {
                System.out.println("Error: el dueño ya está registrado.");
                return;
            }
        }

        Dueno dueno = new Dueno(nombre, documento, telefono);
        duenos.add(dueno);
        System.out.println("Dueño registrado correctamente.");
    }

    private static void registrarMascota(Scanner sc) {
        System.out.print("Documento del dueño: ");
        String doc = sc.nextLine().trim();
        Dueno dueno = buscarDueno(doc);
        if (dueno == null) {
            System.out.println("Error: dueño no encontrado.");
            return;
        }

        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Especie: ");
        String especie = sc.nextLine().trim();
        System.out.print("Edad: ");
        String edadTxt = sc.nextLine().trim();

        if (nombre.isEmpty() || especie.isEmpty() || edadTxt.isEmpty()) {
            System.out.println("Error: todos los campos son obligatorios.");
            return;
        }

        if (!esNumero(edadTxt)) {
            System.out.println("Error: la edad debe ser un número entero.");
            return;
        }

        int edad = Integer.parseInt(edadTxt);

        for (Mascota m : mascotas) {
            if (m.getDueno().getDocumento().equals(doc) && m.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Error: ya existe una mascota con ese nombre para este dueño.");
                return;
            }
        }

        Mascota mascota = new Mascota(nombre, especie, edad, dueno);
        mascotas.add(mascota);
        System.out.println("Mascota registrada correctamente.");
    }

    private static void registrarControl(Scanner sc) {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = sc.nextLine().trim();
        Mascota mascota = buscarMascota(nombreMascota);
        if (mascota == null) {
            System.out.println("Error: la mascota no existe.");
            return;
        }

        System.out.print("Tipo de control (VACUNA, CHEQUEO, DESPARACITACION): ");
        String tipoTxt = sc.nextLine().trim().toUpperCase();
        TipoControl tipo;
        try {
            tipo = TipoControl.valueOf(tipoTxt);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: tipo de control inválido.");
            return;
        }

        System.out.print("Observaciones: ");
        String obs = sc.nextLine().trim();
        if (obs.isEmpty()) {
            System.out.println("Error: las observaciones no pueden estar vacías.");
            return;
        }

        ControlVeterinario control = new ControlVeterinario(LocalDate.now(), tipo, obs);
        controles.add(control);
        System.out.println("Control registrado correctamente para " + mascota.getNombre());
    }

    private static void consultarHistorial(Scanner sc) {
        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine().trim();
        Mascota mascota = buscarMascota(nombre);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }

        System.out.println("Historial médico de " + mascota.getNombre() + ":");
        for (ControlVeterinario c : controles) {
            System.out.println("- " + c.getTipoControl() + " (" + c.getFecha() + "): " + c.getObservaciones());
        }
    }

    private static void generarResumen(Scanner sc) {
        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine().trim();
        Mascota mascota = buscarMascota(nombre);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }

        long cantidad = controles.stream().count();
        System.out.println("Resumen:");
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Especie: " + mascota.getEspecie());
        System.out.println("Edad: " + mascota.getEdad());
        System.out.println("Dueño: " + mascota.getDueno().getNombreCompleto());
        System.out.println("Controles registrados: " + cantidad);
    }

    private static Dueno buscarDueno(String documento) {
        for (Dueno d : duenos) {
            if (d.getDocumento().equals(documento)) return d;
        }
        return null;
    }

    private static Mascota buscarMascota(String nombre) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
    }
}
