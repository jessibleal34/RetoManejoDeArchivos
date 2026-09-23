/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabaseconectivity.Modelo.Persistencia;

/**
 *
 * @author jessica urrego
 */



import com.mycompany.javadatabaseconectivity.Modelo.Clases.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class Operaciones {

    // 1. INSCRIBIR PARTICIPANTE
    public boolean inscribir(Participante participante) {

        String sqlBuscar = "SELECT idparticipante FROM participantes WHERE correo = ?";
        String sqlInsertar = "INSERT INTO participantes (nombre, correo, empresa) VALUES (?, ?, ?)";

        Connection conexion = null;

        try {
            conexion = ConexionDB2.conectar();

            // Iniciamos la transacción
            conexion.setAutoCommit(false);

            // Primero verificamos si el correo ya existe
            try (PreparedStatement psBuscar = conexion.prepareStatement(sqlBuscar)) {

                psBuscar.setString(1, participante.getCorreo());

                try (ResultSet rs = psBuscar.executeQuery()) {

                    if (rs.next()) {
                        System.out.println("El correo ya está registrado.");
                        conexion.rollback();
                        return false;
                    }
                }
            }

            // Si no existe, insertamos
            try (PreparedStatement psInsertar = conexion.prepareStatement(sqlInsertar)) {

                psInsertar.setString(1, participante.getNombre());
                psInsertar.setString(2, participante.getCorreo());
                psInsertar.setString(3, participante.getEmpresa());

                int filas = psInsertar.executeUpdate();

                if (filas > 0) {
                    conexion.commit();
                    return true;
                } else {
                    conexion.rollback();
                    return false;
                }
            }

        } catch (SQLException e) {

            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }

            System.out.println("Error al inscribir: " + e.getMessage());
            return false;

        } finally {

            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    // 2. LISTAR TODOS
    public List<Participante> listar() {

        List<Participante> lista = new ArrayList<>();

        String sql = "SELECT idparticipante, nombre, correo, empresa "
                + "FROM participantes";

        try (
                Connection conexion = ConexionDB2.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Participante participante = new Participante(
                        rs.getInt("idparticipante"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("empresa")
                );

                lista.add(participante);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar participantes: "
                    + e.getMessage());
        }

        return lista;
    }

    // 3. BUSCAR POR EMPRESA
    public List<Participante> buscarPorEmpresa(String empresa) {

        List<Participante> lista = new ArrayList<>();

        String sql = "SELECT idparticipante, nombre, correo, empresa "
                + "FROM participantes WHERE empresa = ?";

        try (
                Connection conexion = ConexionDB2.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empresa);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Participante participante = new Participante(
                            rs.getInt("idparticipante"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("empresa")
                    );

                    lista.add(participante);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar por empresa: "
                    + e.getMessage());
        }

        return lista;
    }

    // 4. CONTAR PARTICIPANTES
    public int contar() {

        String sql = "SELECT COUNT(*) AS total FROM participantes";

        try (
                Connection conexion = ConexionDB2.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println("Error al contar participantes: "
                    + e.getMessage());
        }

        return 0;
    }

    // 5. ELIMINAR POR ID
    public boolean eliminar(int id) {

        String sql = "DELETE FROM participantes WHERE idparticipante = ?";

        try (
                Connection conexion = ConexionDB2.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}