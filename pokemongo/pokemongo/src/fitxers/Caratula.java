/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitxers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isard
 */
public class Caratula {
    
    /*atributs necessaris per llegir un fitxer*/
      File ruta_archivo;
    FileReader reader;
     BufferedReader fichero;
    
    
    /*constructor necessari*/
 //els 2 constructors  (un sense el boolean i el altre amb el boolean
    public Caratula() throws FileNotFoundException
    {
        ruta_archivo = new File("ficheros/logo.pok");
        reader = new FileReader(ruta_archivo);
        fichero = new BufferedReader(reader);

    }
    /*recuperarDatos*/
    public List<String> recuperarDatos(/* */) throws IOException
    {
         List<String> datos = new ArrayList<>();
            String linea;
             do
        {
            linea = fichero.readLine();
            if (linea!=null)
            {
                datos.add(linea);
            }
        }while(linea!=null);
        this.cerrarFicheros();
            
        return datos;
        //abrir fichero
        //recuperar linea a linea y guardarlo en un arraylist<String>
        //retornar el arrayList<String>
        //no sout!!!
    
    }
    public String leerLinea() throws IOException
    {
        return fichero.readLine();
    }
      public void cerrarFicheros() throws IOException
    {
        reader.close();
        fichero.close();
    }
}
