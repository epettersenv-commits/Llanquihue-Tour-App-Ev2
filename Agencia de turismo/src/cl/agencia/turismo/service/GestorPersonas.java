package cl.agencia.turismo.service;

import cl.agencia.turismo.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Administra la colección de personas y ofrece búsquedas y filtros
 *
 * @author Elizabeth Pettersen
 */
public class GestorPersonas {
    private final List<Persona> personas = new ArrayList<>();

    /**
     * Agrega una persona si su rut no está registrado
     *
     * @param p persona a agregar
     * @return true si se agregó, false si el rut ya existía
     */
    public boolean agregar(Persona p) {
        if (buscarPorRut(p.getRut()) != null) {
            return false;
        }
        personas.add(p);
        return true;
    }

    public List<Persona> getPersonas() {
        return new ArrayList<>(personas);
    }

    public int total() {
        return personas.size();
    }

    public Persona buscarPorRut(String rut) {
        for (Persona p : personas) {
            if (p.getRut().equalsIgnoreCase(rut.trim())) {
                return p;
            }
        }
        return null;
    }

    public List<Persona> buscarPorNombre(String texto) {
        List<Persona> resultado = new ArrayList<>();
        String t = texto.toLowerCase().trim();
        for (Persona p : personas) {
            String completo = (p.getNombre() + " " + p.getApellido()).toLowerCase();
            if (completo.contains(t)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Filtra por tipo de persona: empleado, guía o cliente
     */
    public List<Persona> filtrarPorTipo(String tipo) {
        List<Persona> resultado = new ArrayList<>();
        String t = tipo.toLowerCase().trim();
        for (Persona p : personas) {
            if ((t.equals("empleado") && p instanceof Empleado) || (t.equals("guia") && p instanceof GuiaTuristico)
                    || (t.equals("cliente") && p instanceof Cliente)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Filtra por la comuna de la dirección (usa la composición Persona -> Dirección)
     */
    public List<Persona> filtrarPorComuna(String comuna) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getDireccion().getComuna().equalsIgnoreCase(comuna.trim())) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
