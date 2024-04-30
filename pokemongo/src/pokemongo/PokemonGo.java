
package pokemongo;

import Menu_utils.OptionDuplicateException;
import Menu_utils.menudaw;
import fitxers.Caratula;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelo.EntrenadorDAO;
import modelo.entrenador;


public class PokemonGo {

    Scanner sc;
    EntrenadorDAO entrenadores;
   
    public static void main(String[] args) throws SQLException {
        PokemonGo app = new PokemonGo();
        app.run();
    }

    /* la ejecucion programa*/
    private void run() throws SQLException {
        
        boolean exit = false;
        mostrarLogo();
        menudaw menu = new menudaw("Manteniment Agenda ");
        addAllOptions(menu);
        //introDadesProva(/* */);
        int opcio;
        entrenadores = new EntrenadorDAO();
        //tractar opcio escollida bucle fins que donis sortir no acabi CASA
        do
        {
            //mostrar el menu i escollir opcio CASA
            opcio = menu.displayMenu();
            switch(opcio)
            {
                case 2:
                    altaEntrenador();
                    break;
                case 3:
                    borrarEntrenador();
                    break;
                case 4:
                    consultaEntrenador();
                    break;
                case 5:
                    cazarPokemon();
                    break;
                case 6:
                    listarMochila();
                    break;
                case 7:
                    listarTodosPokemons();
                    break;
                case 1: //Sortir
                    salir();
                    exit = true;
                    break;
            }
        }while(!exit);
 
    
}

    private void mostrarLogo()  {
        try {
            Caratula logo = new Caratula();
            
            List<String> portada = logo.recuperarDatos();
            
            for (String lineas : portada) {
                System.out.println(lineas);
            }
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error leyendo fichero " + ex.getMessage());
        }
        
        
        
    }
    
    
    private static void addAllOptions(menudaw menu) {
        try {
            menu.AddOption("Salir");
            menu.AddOption("Dar de alta entrenador");
            menu.AddOption("Dar de baja entrenador");
            menu.AddOption("Consultar entrenador");
            menu.AddOption("Cazar Pokemon");
            menu.AddOption("Listar Pokemons Cazados");
            menu.AddOption("Listar tipos Pokemon existentes en juego");
            
        } catch (OptionDuplicateException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * pide datos al usuario para poder dar de alta a un entrenador
     */
    private void altaEntrenador() {
        try {
            sc = new Scanner(System.in);
            int insertado ;
            System.out.println("Pon el nombre del nuevo entrenador");
            String nombre = sc.nextLine();
            System.out.println("Pon el password");
            String password = sc.nextLine();
            entrenador nuevo = new entrenador(nombre, password);
            //llamar al dao existeEntrenador
            //if.... 
            insertado = entrenadores.altaEntrenador(nuevo);
            if (insertado > 0)
            {
                System.out.println("Se ha insertado el nuevo entrenador ");
            }
            else
            {
                System.out.println("Error insertando entrenador");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL insertando entrenador" + ex.getMessage());
        }
        
    }

    private void borrarEntrenador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void ConsultaEntrenador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void consultaEntrenador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void cazarPokemon() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listarMochila() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void listarTodosPokemons() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void salir() {
        try {
            System.out.println("Te esperamos pronto de vuelta... ");
            entrenadores.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println("Error cerrar conexión " + ex.getMessage());
        }
    }
        
}