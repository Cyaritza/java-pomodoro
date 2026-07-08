/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caroR
 */
public class UsuarioDao2 {
    
     // metodo validar usuario
    public boolean validarUsuario(String nombreUsuario, String contrasenia, String tipo){
        
        boolean valido = false;
        
        Conexion conexionBD = new Conexion();
        Connection conexionPrincipal = conexionBD.conector();        
        
        String sql = null;

        if (tipo.equals("alumno")) {
            sql = "SELECT * FROM alumnos WHERE nombre_usuario = ? AND contrasena = ?";
        } else if (tipo.equals("profesor")) {
            sql = "SELECT * FROM profesores WHERE nombre_usuario = ? AND contrasena = ?";
        } else {
            System.out.println("No existe la tabla");
            return false;
        }
       
        try{
            
            // para ejecutar la sentencia con parametros de entrada
            PreparedStatement ps = conexionPrincipal.prepareStatement(sql);
            
            // sustitimos valores 
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasenia);
                     
            ResultSet rs = ps.executeQuery();
            
            // avanzamos por los registros (true hay registros, false no hay mas registros)
            if(rs.next()){
                valido = true; // se encontro el usuario
            }
            
            // cerramos conexion, preparedStatement y el resultset
            rs.close();
            ps.close();
            conexionPrincipal.close();
            
            
        }catch(SQLException sqle){
            System.out.println("Error al validar al usuario.");
            System.out.println(sqle.getMessage());
        }
        
        return valido;
        
    }
    
        // Registrar profesor
    public boolean registrarProfesor(String nombreUsuario, String contrasena) {
        boolean registrado = false;
        Conexion conexionBD = new Conexion();
        Connection conexionPrincipal = conexionBD.conector();

        String sql = "INSERT INTO profesores (nombre_usuario, contrasena) VALUES (?, ?)";

        try {
            if (usuarioExiste(nombreUsuario, "profesores")) {
                System.out.println("El profesor " + nombreUsuario + " ya está registrado");
                return false;
            }

            PreparedStatement ps = conexionPrincipal.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                registrado = true;
            }

            ps.close();
            conexionPrincipal.close();

        } catch (SQLException sqle) {
            System.out.println("Error al registrar profesor");
            System.out.println(sqle.getMessage());
        }

        return registrado;
    }

    // Método auxiliar para comprobar existencia en tabla específica
    public boolean usuarioExiste(String nombreUsuario, String tipo) {
        boolean existe = false;
        Conexion conexionBD = new Conexion();
        Connection conexionPrincipal = conexionBD.conector();

        
        String sql = null;
        
        if (tipo.equals("alumno")){
            sql = "SELECT COUNT(*) FROM alumnos WHERE nombre_usuario = ?";
        }
        else if (tipo.equals("profesor")){
            sql = "SELECT COUNT(*) FROM profesores  WHERE nombre_usuario = ?";
        }else{
            System.out.println("Error de tabla");
              
        }

        
        try {
            PreparedStatement ps = conexionPrincipal.prepareStatement(sql);
            ps.setString(1, nombreUsuario);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int cantidad = rs.getInt(1);

            if (cantidad > 0) {
                existe = true;
            }

            rs.close();
            ps.close();
            conexionPrincipal.close();

        } catch (SQLException sqle) {
            System.out.println("Error al comprobar existencia en tabla " + tipo);
            System.out.println(sqle.getMessage());
        }

        return existe;
    }
}
