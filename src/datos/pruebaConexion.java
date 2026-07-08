/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import java.sql.Connection;


/**
 *
 * @author caroR
 */
public class pruebaConexion {
    
    public static void main(String[] args){
        
        Conexion miConexion = new Conexion();
        Connection conexionPrincipal = miConexion.conector();
        
        if(conexionPrincipal != null){
            System.out.println("Conexion lista para usarse");
        }else {
            System.out.println("La conexion fallo.");
        }
    } 
}
