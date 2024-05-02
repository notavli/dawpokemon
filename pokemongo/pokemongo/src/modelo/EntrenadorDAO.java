/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import basedatos.dbconnect;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import modelo.entrenador;


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
    public boolean existeEntrenador(String name) throws SQLException
    {
   if (conn_principal!=null)
        {
            Statement stmt = conn_principal.createStatement();
            String query = "Select id, name, password"
                    + " from entrenadors where UPPER(name)"
                    + " = '" + name.toUpperCase() + "'";
            
            String query2 = "Select count(*) "
                    + " from entrenadors where UPPER(name)"
                    + " = '" + name.toUpperCase() + "'";
            
            System.out.println(query2);
            ResultSet cursor = stmt.executeQuery(query2);
            if (cursor.next())
            {
                int registros = cursor.getInt(1);
                if (registros==1)
                {
                    return true;    
                }
                else
                {
                    return false;
                }
                
            }
            else
            {
                return false;
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
    public entrenador esborrarEntrenador(String name) throws SQLException 
    {
        if (conn_principal!=null)
        {
           Statement stmt = conn_principal.createStatement();
           String query ="DELETE FROM entrenadors WHERE NOMBRE="+name+"";
            System.out.println(query);
              int filasAfectadas = stmt.executeUpdate(query);
            
            if (filasAfectadas == 0) {
                    entrenador = null; // No se encontró el entrenador para borrar
                }
        }
       
         return entrenador;
       
    }
           

    
    
    /**
     * retorna tots els entrenadors de la base de dades o null si no n'hi ha cap
     * @return 
     */
    public List<entrenador> totsEntrenadors() throws SQLException
    {
          List<entrenador> entrenadors = new ArrayList<>();

    if(conn_principal !=null){
             Statement stmt = conn_principal.createStatement();
       
        String query = "SELECT * FROM entrenadors";
        ResultSet rs = stmt.executeQuery(query);
      
        while (rs.next()) {
            String nom = rs.getString("name");
            String contrasenya = rs.getString("contraseña");
            entrenador entrenador = new entrenador(nom, contrasenya);
            entrenadors.add(entrenador);
   
    
        }
    }
    
        return entrenadors; // Retorna la lista de entrenadores recuperada
    }
        
    
    
    
    /**
     * retorna un objecte entrenador amb els atributs informats que tingui el name.
     * Si no existei retorna null
     * @param name 
     */

    public entrenador devolverEntrenador(String name) throws SQLException{
    
        
    
 if(conn_principal!=null){
     Statement stmt= conn_principal.createStatement();
     
 
         String query = "Select id, name, password"
                    + " from entrenadors where UPPER(name)"
                    + " = '" + name.toUpperCase() + "'";
      ResultSet rs = stmt.executeQuery(query);
        if (rs.next()){
            String nom = rs.getString("nombre");
            String contrasenya = rs.getString("contrasenya");
             int id = rs.getInt("id");
            return new entrenador(id,nom, contrasenya);
        }
        else{
            return null;
        }
 }
        else{
            return null; 
        }
 
    
}    
    
    
    public void cerrarConexion() throws SQLException
    {
        conn_principal.close();
    }
    
}
