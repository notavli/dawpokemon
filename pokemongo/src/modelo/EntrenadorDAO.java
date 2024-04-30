/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import basedatos.dbconnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;


public class EntrenadorDAO {
     Connection conn_principal;

    public EntrenadorDAO() throws SQLException {
        conn_principal = dbconnect.getConnection(); //estara oberta fins al final
    }
    
    
    public int altaEntrenador(entrenador trainer) throws SQLException
    {

        int rows=0;
        if (conn_principal!=null)
        {
            //realitzo insert
           //llamar al metodo existeEntrenador y si da cierto no hago el insert
            
           String query = "INSERT INTO ENTRENADORS  "
                        + "(name, password) "
                        + " VALUES"
                        + " (?,?)"; //id no informat perque es autoincremental
            //verificacio
            System.out.println(query); //s'ha de treure quant funcioni 
            
            PreparedStatement preparedQuery = conn_principal.prepareStatement(query);
            
            preparedQuery.setString(1, trainer.getName());
            preparedQuery.setString(2,trainer.getContraseña());
            rows = preparedQuery.executeUpdate();   
        }
        
        return rows;
    }
    
    
    /**
     * devuelve cierto si ya exsite un entrenador en base de datos con ese nombre
     * false si no existe
     * @param name
     * @return 
     */
    public boolean existeEntrenador(String name)
    {
        Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
   try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_basededatos", "usuario", "contraseña");
        String query = "SELECT COUNT(*) FROM entrenadores WHERE nombre = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return false;
}
    
    
    
    /**
     * Borrar entrenador amb el nom
     * Primer el te que recuperar, si existeix
     * i despres borrar-lo, pero retornant el objecte entrenador informat
     * si no existia, retorna null 
     * @param name
     * @return 
     */
    public entrenador esborrarEntrenador(String name)
    {
           Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_basededatos", "usuario", "contraseña");
        String query = "SELECT * FROM entrenadores WHERE nombre = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            // Se encontró un entrenador con el nombre dado, recuperar sus atributos
            String nom = rs.getString("nombre");
            String contrasenya = rs.getString("contrasenya");
            // Eliminar al entrenador de la base de datos
            String deleteQuery = "DELETE FROM entrenadores WHERE nombre = ?";
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setString(1, name);
            stmt.executeUpdate();
            // Crear y devolver el objeto Entrenador con sus atributos informados
            return new Entrenador(nom, contrasenya);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return null;

    }

    
    
    /**
     * retorna tots els entrenadors de la base de dades o null si no n'hi ha cap
     * @return 
     */
    public List<entrenador> totsEntrenadors()
    {
          List<entrenador> entrenadors = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_basededatos", "usuario", "contraseña");
        String query = "SELECT * FROM entrenadors";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nombre");
            String contrasenya = rs.getString("contrasenya");
            entrenador entrenador = new entrenador(nom, contrasenya);
            entrenadors.add(entrenador);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (entrenadors.isEmpty()) {
        return null; // Retorna null si no hay entrenadores en la base de datos
    } else {
        return entrenadors; // Retorna la lista de entrenadores recuperada
    }
        
    }
    
    
    /**
     * retorna un objecte entrenador amb els atributs informats que tingui el name.
     * Si no existei retorna null
     * @param name 
     */
    public entrenador devolverEntrenador(String name)
    {
        
 Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_basededatos", "usuario", "contraseña");
        String query = "SELECT * FROM entrenadors WHERE nombre = ?";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            String nom = rs.getString("nombre");
            String contrasenya = rs.getString("contrasenya");
            return new Entrenador(nom, contrasenya);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return null;
}    
    
    
    public void cerrarConexion() throws SQLException
    {
        conn_principal.close();
    }
    
}
