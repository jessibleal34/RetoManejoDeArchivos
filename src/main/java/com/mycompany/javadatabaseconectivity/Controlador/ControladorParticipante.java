/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabaseconectivity.Controlador;

/**
 *
 * @author jessica urrego
 */


import Modelo.Clases.Participante;
import Modelo.Persistencia.Operaciones;
import java.util.List;

public class ControladorParticipante {

    private Operaciones operaciones;

    public ControladorParticipante() {
        operaciones = new Operaciones();
    }

    public boolean inscribir(String nombre, String correo, String empresa) {

        if (nombre == null || nombre.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || empresa == null || empresa.trim().isEmpty()) {

            System.out.println("Todos los campos son obligatorios.");
            return false;
        }

        Participante participante = new Participante(
                nombre.trim(),
                correo.trim(),
                empresa.trim()
        );

        return operaciones.inscribir(participante);
    }

    public List<Participante> listar() {
        return operaciones.listar();
    }

    public List<Participante> buscarPorEmpresa(String empresa) {

        if (empresa == null || empresa.trim().isEmpty()) {
            System.out.println("La empresa es obligatoria.");
            return List.of();
        }

        return operaciones.buscarPorEmpresa(empresa.trim());
    }

    public int contar() {
        return operaciones.contar();
    }

    public boolean eliminar(int id) {
        return operaciones.eliminar(id);
    }
}
    

