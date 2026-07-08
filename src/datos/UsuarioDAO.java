/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author caroR
 */
public class UsuarioDAO {
    
    // metodo validar usuario
    public boolean validarUsuario(String nombreUsuario, String contrasenia, String tipo){
        
        boolean valido = false;
        
        Conexion conexionBD = new Conexion();
        Connection conexionPrincipal = conexionBD.conector();        
        
        // sentencia sql que usaremos en PreparedStatement
        String sql = "SELECT * FROM usuariosinterfaz "
                + "WHERE nombreUsuario = ? AND contrasenia = ? AND tipo = ?";
            
        try{
            
            // para ejecutar la sentencia con parametros de entrada
            PreparedStatement ps = conexionPrincipal.prepareStatement(sql);
            
            // sustitimos valores 
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasenia);
            ps.setString(3, tipo);
            
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
    
    // Método para registrar un nuevo usuario en la base de datos
    public boolean registrarUsuario(String nombreUsuario, String contrasenia, String tipo) {
        boolean registrado = false;

        // Obtenemos la conexión desde la clase Conexion
        Conexion conexionBD = new Conexion();
        Connection conexionPrincipal = conexionBD.conector();

        // Sentencia SQL con placeholders
        String sql = "INSERT INTO usuariosinterfaz (nombreUsuario, contrasenia, tipo) VALUES (?, ?, ?)";

        // sentencia para buscar en la bd si ya existe un usuario con mismo nombre
        String sqlValidar = "SELECT COUNT(*) "
                + "FROM usuariosinterfaz "
                + "WHERE nombreUsuario = ?";
        
        try {
            // validamos el nombreUsuario
           
            if (usuarioExiste(nombreUsuario)){
                System.out.println("El usuario: " + nombreUsuario + " ya esta registrado");
                return false;
            
            }else{
                PreparedStatement ps = conexionPrincipal.prepareStatement(sql);
                
                // Sustituimos los valores
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasenia);
            ps.setString(3, tipo.toLowerCase()); // aseguramos minúsculas para coincidir con ENUM
            
            
            // Ejecutamos la inserción
            int filas = ps.executeUpdate();

            // Si se insertó al menos una fila, el registro fue exitoso
            if (filas > 0) {
                registrado = true;
            }

            // cerramos conexion, preparedStatement
            ps.close();
            conexionPrincipal.close();
            
            }    
        } catch (SQLException sqle) {
            System.out.println("Error al registrar al usuario");
            System.out.println(sqle.getMessage());
        }

        return registrado;
    }
    
    // Método auxiliar para comprobar si ya existe un usuario con ese nombre
public boolean usuarioExiste(String nombreUsuario) {
    boolean existe = false;

    Conexion conexionBD = new Conexion();
    Connection conexionPrincipal = conexionBD.conector();

    String sql = "SELECT COUNT(*) FROM usuariosinterfaz WHERE nombreUsuario = ?";

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
        System.out.println("Error al comprobar existencia de usuario");
        System.out.println(sqle.getMessage());
    }
    return existe;
}
}
