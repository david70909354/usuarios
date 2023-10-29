package com.mycompany.mysql3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Cconexion {
    private Connection conectar = null;
    private String usuario = "root";
    private String password = "70909354";
    private String bd = "usuarios";
    private String ip = "localhost";
    private String puerto = "3306";
    private String url = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(url, usuario, password);
            JOptionPane.showMessageDialog(null, "Conexión exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de clase: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return conectar;
    }

    public void cerrarConexion() {
        if (conectar != null) {
            try {
                conectar.close();
                JOptionPane.showMessageDialog(null, "Conexión cerrada");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
           
        }
    }
}
