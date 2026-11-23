package cl.agencia.turismo.app;

import cl.agencia.turismo.model.Persona;
import cl.agencia.turismo.service.GestorPersonas;
import cl.agencia.turismo.util.LectorCSV;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase principal. Carga las personas desde un archivo CSV y ofrece un menú por consola
 *
 * @author Elizabeth Pettersen
 */
public class Main {
    public static final String RUTA_POR_DEFECTO = "data/personas.csv";

    public static void main(String[] args) {
        System.out.println(":::::::::: Llanquihue Tour - Gestión de Personas ::::::::::\n");

        String ruta = args.length > 0 ? args[0] : RUTA_POR_DEFECTO;
        GestorPersonas gestorPersonas = new GestorPersonas();
        LectorCSV lectorCSV = new LectorCSV();

        try {
            for (Persona p : lectorCSV.leer(ruta)) {
                if (!gestorPersonas.agregar(p)) {
                    System.out.println("Registro duplicado omitido (rut " + p.getRut() + ")");
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo '" + ruta + "': " + e.getMessage());
            return;
        }

        System.out.println("Registros cargados: " + gestorPersonas.total());
        if (!lectorCSV.getErrores().isEmpty()) {
            System.out.println("Registros con errores (omitidos):");
            for (String error : lectorCSV.getErrores()) {
                System.out.println(" - " + error);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, ingrese un número");
                continue;
            } catch (NoSuchElementException e) {
                break;
            }
            switch (opcion) {
                case 1:
                    mostrar(gestorPersonas.getPersonas());
                    break;
                case 2:
                    System.out.println("Tipo (empleado / guia / cliente): ");
                    mostrar(gestorPersonas.filtrarPorTipo(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println("Comuna: ");
                    mostrar(gestorPersonas.filtrarPorComuna(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println("Nombre o apellido: ");
                    mostrar(gestorPersonas.buscarPorNombre(scanner.nextLine()));
                    break;
                case 5:
                    System.out.println("Rut (ej. 12.345.678-9): ");
                    Persona p = gestorPersonas.buscarPorRut(scanner.nextLine());
                    System.out.println(p != null ? p : "No existe una persona con ese rut");
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Listar todos");
        System.out.println("2. Filtrar por tipo");
        System.out.println("3. Filtrar por comuna");
        System.out.println("4. Buscar por nombre");
        System.out.println("5. Buscar por rut");
        System.out.println("0. Salir.");
        System.out.println("Opción: ");
    }

    private static void mostrar(List<Persona> lista) {
        if (lista.isEmpty()) {
            System.out.println("Sin resultados");
            return;
        }
        for (Persona p : lista) {
            System.out.println(p + "\n");
        }
        System.out.println("(" + lista.size() + " resultado(s))");
    }
}