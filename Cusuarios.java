/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysql3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author econo
 */
public class Cusuarios {
 int id;
    String nombre;
    String correo_electronico;
    String telefono;
    String contrasena;
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
public void InsertarUsuario(JTextField paramNombre, JTextField paramCorreo, JTextField paramTelefono, JTextField paramContrasena) {
    try {
        setNombre(paramNombre.getText());
        setCorreo_electronico(paramCorreo.getText());
        setTelefono(paramTelefono.getText());
        setContrasena(paramContrasena.getText());
    
        Cconexion objetoConexion = new Cconexion();

        String consulta = "INSERT INTO usuarios (nombre, correo_electronico, telefono, contrasena) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = objetoConexion.establecerConexion();
             CallableStatement cs = conexion.prepareCall(consulta)) {
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo_electronico());
            cs.setString(3, getTelefono());
            cs.setString(4, getContrasena());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
        }
    } catch (Exception ex) {
        // Manejo de errores generales fuera del bloque de conexión
        ex.printStackTrace(); // Puedes imprimir el error en la consola para fines de depuración
    }
}

 public void MostrarUsuarios(JTable paramTablaTotalUsuarios){
    
    Cconexion objetoConexion = new Cconexion();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
    paramTablaTotalUsuarios.setRowSorter(OrdenarTabla);
    
    String sql = "";
    
    modelo.addColumn("Id");
    modelo.addColumn("Nombres");
    modelo.addColumn("Correo");
    modelo.addColumn("Telefono");
    modelo.addColumn("Contraseña");
    paramTablaTotalUsuarios.setModel(modelo); 
    
    sql = "select * from usuarios;";
    
    String[] datos = new String[5]; // Cambio el tamaño del arreglo a 5
    Statement st;
    
    try {
        st = objetoConexion.establecerConexion().createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);
            
            modelo.addRow(datos);
        }
        
        paramTablaTotalUsuarios.setModel(modelo);
        
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
}

    public void ModificarUsuario(JTextField paramId, JTextField paramNombre, JTextField paramCorreo, JTextField paramTelefono, JTextField paramContrasena) {
    try {
        Cconexion objetoConexion = new Cconexion();
        
        setId(Integer.parseInt(paramId.getText()));
        setNombre(paramNombre.getText());
        setCorreo_electronico(paramCorreo.getText());
        setTelefono(paramTelefono.getText());
        setContrasena(paramContrasena.getText());

        String consulta = "UPDATE usuarios.usuarios SET usuarios.nombre = ?, usuarios.correo_electronico = ?, usuarios.telefono = ?, contrasena = ? WHERE id = ?";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo_electronico());
            cs.setString(3, getTelefono());
            cs.setString(4, getContrasena());
            cs.setInt(5, getId()); // Suponiendo que 'getId()' devuelve el ID del usuario que deseas modificar
            
            int filasAfectadas = cs.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Modificación exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario con el ID especificado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario, error: " + e.toString());
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error al modificar el usuario: " + ex.toString());
    }
}
     public void EliminarUsuario(JTextField paramId, JTextField Textnombre, JTextField Textcorreo, JTextField Texttelefono, JTextField Textcontrasena){
    
        setId(Integer.parseInt(paramId.getText()));
        
        Cconexion objetoConexion = new Cconexion();
        
        String consulta = "DELETE FROM usuarios WHERE usuarios.id=?;";
        
        try {
             CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
             cs.setInt(1, getId());
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"Se eliminó correctamente el usuario");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
        }
        
    }
    
    
}

  

    

