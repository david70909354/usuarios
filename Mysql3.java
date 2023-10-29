/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mysql3;

/**
 *
 * @author econo
 */
public class Mysql3 {

    public static void main(String[] args) {
        datos objetoFormulario=new datos();
        objetoFormulario.setVisible(true);
        Cconexion objetoConexion = new Cconexion(); 
        objetoConexion.establecerConexion(); 
    }
}
