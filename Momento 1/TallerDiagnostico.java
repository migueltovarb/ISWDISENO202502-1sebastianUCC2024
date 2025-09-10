package paquetePrimerProyecto;

import java.util.Scanner;

public class TallerDiagnostico {
    
    static final int DIAS_SEMANA = 5;
    static final int NUM_ESTUDIANTES = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] asistencia = new String[DIAS_SEMANA][NUM_ESTUDIANTES];
        String[] estudiantes = {"JUAN", "SEBASTIAN", "HECTOR", "TATIANA"};
        int opcion;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Registrar asistencia");
            System.out.println("2. Ver asistencia individual");
            System.out.println("3. Ver resumen general");
            System.out.println("4. Volver a registrar");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch(opcion) {
                case 1:
                    for(int d = 0; d < DIAS_SEMANA; d++) {
                        System.out.println("\n--- Día " + (d+1) + " ---");
                        for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                            System.out.print("Ingrese asistencia de " + estudiantes[e] + " (P/A): ");
                            String valor = sc.next().toUpperCase();
                            while(!valor.equals("P") && !valor.equals("A")) {
                                System.out.print("Valor inválido, ingrese P o A: ");
                                valor = sc.next().toUpperCase();
                            }
                            asistencia[d][e] = valor;
                        }
                    }

                    System.out.println("\n===== RESUMEN =====");
                    for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                        int totalP = 0;
                        for(int d = 0; d < DIAS_SEMANA; d++) {
                            if(asistencia[d][e].equals("P")) totalP++;
                        }
                        System.out.println(estudiantes[e] + " asistió " + totalP + " veces.");
                    }

                    System.out.println("\nEstudiantes que asistieron TODOS los días:");
                    boolean alguno = false;
                    for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                        int totalP = 0;
                        for(int d = 0; d < DIAS_SEMANA; d++) {
                            if(asistencia[d][e].equals("P")) totalP++;
                        }
                        if(totalP == DIAS_SEMANA) {
                            System.out.println(estudiantes[e]);
                            alguno = true;
                        }
                    }
                    if(!alguno) System.out.println("Ninguno");

                    int maxAusencias = -1;
                    for(int d = 0; d < DIAS_SEMANA; d++) {
                        int ausencias = 0;
                        for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                            if(asistencia[d][e].equals("A")) ausencias++;
                        }
                        if(ausencias > maxAusencias) maxAusencias = ausencias;
                    }
                    System.out.println("\nDías con mayor número de ausencias:");
                    for(int d = 0; d < DIAS_SEMANA; d++) {
                        int ausencias = 0;
                        for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                            if(asistencia[d][e].equals("A")) ausencias++;
                        }
                        if(ausencias == maxAusencias) {
                            System.out.println("Día " + (d+1) + " con " + ausencias + " ausencias.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nombre del estudiante: ");
                    String nombre = sc.next().toUpperCase();
                    int idx = -1;
                    for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                        if(estudiantes[e].equals(nombre)) {
                            idx = e;
                            break;
                        }
                    }
                    if(idx == -1) {
                        System.out.println("Estudiante no encontrado.");
                    } else {
                        System.out.println("Asistencia de " + estudiantes[idx] + ":");
                        for(int d = 0; d < DIAS_SEMANA; d++) {
                            System.out.println("Día " + (d+1) + ": " + (asistencia[d][idx] != null ? asistencia[d][idx] : "-"));
                        }
                    }
                    break;

                case 3:
                    System.out.println("===== Asistencia General =====");
                    for(int d = 0; d < DIAS_SEMANA; d++) {
                        System.out.print("Día " + (d+1) + ": ");
                        for(int e = 0; e < NUM_ESTUDIANTES; e++) {
                            System.out.print(estudiantes[e] + "=" + (asistencia[d][e] != null ? asistencia[d][e] : "-") + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 4:
                    asistencia = new String[DIAS_SEMANA][NUM_ESTUDIANTES];
                    System.out.println("Los registros han sido reiniciados.");
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while(opcion != 5);

        sc.close();
    }
}
