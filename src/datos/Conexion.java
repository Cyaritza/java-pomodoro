/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author caroR
 */
public class Conexion {
    
    // Datos de la base de datos
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/conexioninterfazv1"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234P&ters!n"; 
    
    //Constructor
    public Conexion(){
        
    }
    
    // metodo que devuelve la conexion
    public Connection conector() {
        
        Connection conexion = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // establezco conexion
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion establecida.");
            
            
            
        }catch (ClassNotFoundException cnfe) {
            System.out.println("Error: No se encontro el driver JDBC");
            
        } catch (SQLException sqle) {
            System.out.println("Error al conectar con la base de datos");
         
        }
        
        return conexion;
    }
}
