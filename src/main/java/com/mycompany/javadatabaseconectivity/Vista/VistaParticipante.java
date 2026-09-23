/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabaseconectivity.Vista;

/**
 *
 * @author jessica urrego
 */

        

import Controlador.ControladorParticipante;
import Modelo.Clases.Participante;
import java.util.List;
import java.util.Scanner;

public class VistaParticipante {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ControladorParticipante controlador =
                new ControladorParticipante();

        int opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("   WEBINAR CORPORATIVO");
            System.out.println("==============================");
            System.out.println("1. Inscribir participante");
            System.out.println("2. Listar participantes");
            System.out.println("3. Buscar por empresa");
            System.out.println("4. Contar participantes");
            System.out.println("5. Eliminar inscripción");
            System.out.println("6. Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número.");
                opcion = 0;
            }

            switch (opcion) {

                case 1:

                    System.out.println("\n--- INSCRIBIR PARTICIPANTE ---");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();

                    System.out.print("Empresa: ");
                    String empresa = scanner.nextLine();

                    boolean inscrito =
                            controlador.inscribir(
                                    nombre,
                                    correo,
                                    empresa
                            );

                    if (inscrito) {
                        System.out.println(
                                "Participante inscrito correctamente."
                        );
                    }

                    break;

                case 2:

                    System.out.println("\n--- PARTICIPANTES ---");

                    List<Participante> participantes =
                            controlador.listar();

                    if (participantes.isEmpty()) {
                        System.out.println(
                                "No hay participantes inscritos."
                        );
                    } else {

                        for (Participante p : participantes) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 3:

                    System.out.println("\n--- BUSCAR POR EMPRESA ---");

                    System.out.print("Empresa: ");
                    String empresaBuscar = scanner.nextLine();

                    List<Participante> encontrados =
                            controlador.buscarPorEmpresa(
                                    empresaBuscar
                            );

                    if (encontrados.isEmpty()) {

                        System.out.println(
                                "No se encontraron participantes."
                        );

                    } else {

                        for (Participante p : encontrados) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 4:

                    int total = controlador.contar();

                    System.out.println(
                            "Total de participantes: " + total
                    );

                    break;

                case 5:

                    System.out.println("\n--- ELIMINAR INSCRIPCIÓN ---");

                    System.out.print("Ingrese el ID: ");

                    try {

                        int id = Integer.parseInt(scanner.nextLine());

                        boolean eliminado =
                                controlador.eliminar(id);

                        if (eliminado) {
                            System.out.println(
                                    "Participante eliminado correctamente."
                            );
                        } else {
                            System.out.println(
                                    "No se encontró un participante con ese ID."
                            );
                        }

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "El ID debe ser un número."
                        );
                    }

                    break;

                case 6:

                    System.out.println(
                            "Programa finalizado."
                    );

                    break;

                default:

                    System.out.println(
                            "Opción no válida."
                    );
            }

        } while (opcion != 6);

        scanner.close();
    }
}
    

