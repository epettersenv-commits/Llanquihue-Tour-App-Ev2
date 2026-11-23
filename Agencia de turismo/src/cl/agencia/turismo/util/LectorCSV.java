package cl.agencia.turismo.util;

import cl.agencia.turismo.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee un archivo CSV (separado por ';' y crea los objetos Persona)
 * Formato: tipo;rut;nombre;apellido;teléfono;email;calle;número;comuna;región;dato1;dato2
 * Empleado: cargo;sueldoBase | Guía: idioma;especialidad | Cliente: nacionalidad;tipoTour
 *
 * @author Elizabeth Pettersen
 */
public class LectorCSV {
    private final List<String> errores = new ArrayList<>();

    public List<Persona> leer(String rutaArchivo) throws IOException {
        List<Persona> personas = new ArrayList<>();
        errores.clear();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = bufferedReader.readLine()) != null) {
                numeroLinea++;
                if (linea.isBlank() || linea.startsWith("#")) {
                    continue;
                }
                try {
                    personas.add(convertir(linea));
                } catch (IllegalArgumentException e) {
                    errores.add("Línea " + numeroLinea + ": " + e.getMessage());
                }
            }
        }
        return personas;
    }

    private Persona convertir(String linea) {
        String[] c = linea.split(";");
        if (c.length != 12) {
            throw new IllegalArgumentException("Se esperaban 12 campos y hay " + c.length);
        }
        int numero;
        try {
            numero = Integer.parseInt(c[7].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número de dirección no numérico: " + c[7]);
        }
        Direccion direccion = new Direccion(c[6], numero, c[8], c[9]);

        switch (c[0].trim().toUpperCase()) {
            case "EMPLEADO":
                int sueldo;
                try {
                    sueldo = Integer.parseInt(c[11].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Sueldo no numérico: " + c[11]);
                }
                return new Empleado(c[1], c[2], c[3], c[4], c[5], direccion, c[10], sueldo);
            case "GUIA":
                return new GuiaTuristico(c[1], c[2], c[3], c[4], c[5], direccion, c[10], c[11]);
            case "CLIENTE":
                return new Cliente(c[1], c[2], c[3], c[4], c[5], direccion, c[10], c[11]);
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + c[0]);
        }
    }

    /**
     * @return los mensajes de error de la última lectura
     */
    public List<String> getErrores() {
        return errores;
    }
}
