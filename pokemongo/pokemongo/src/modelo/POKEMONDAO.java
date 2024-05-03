/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import basedatos.dbconnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class POKEMONDAO {
    Connection conn_principal;//para poder connectarse

    public POKEMONDAO() throws SQLException {
        conn_principal=dbconnect.getConnection();
    }
    
    public List<POKEMON>totspokemon() throws SQLException{
         List<POKEMON> pokemons = new ArrayList<>();

    if(conn_principal !=null){
             Statement stmt = conn_principal.createStatement();
       
        String query = "SELECT * FROM entrenadors";
        ResultSet rs = stmt.executeQuery(query);
      
        while (rs.next()) {
            int podeks =rs.getInt(query);
            String nom = rs.getString("name");
            String tipus = rs.getString("contraseña");
            POKEMON pokemon = new POKEMON(podeks,nom, tipus);
            pokemons.add(pokemon);
   
    
        }
    }
    
        return pokemons ; // Retorna la lista de pokemons recuperada
    }
   public POKEMON  getPokemon() throws SQLException{
       Random ran = new Random();
       List <POKEMON>pokemon=totspokemon();
       ran =pokemon.size();
       
        return false;
   }
        
    
}
