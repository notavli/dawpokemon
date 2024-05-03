
package pokemongo;

import Menu_utils.OptionDuplicateException;
import Menu_utils.menudaw;
import basedatos.dbconnect;
import fitxers.Caratula;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private void run()  {
               try {
            
            mostrarLogo();
            dbconnect.loadDriver();
            entrenadores = new EntrenadorDAO();
            //boolean user_valid = validar_usuari();
            boolean user_valid = true;
            if (user_valid)
            {
                juego_valido();
            }
 
        } catch (SQLException ex) {
            System.out.println("Hay un error SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    
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
             menu.AddOption("Listar entrenadores");
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
        try {
            sc = new Scanner(System.in);
            System.out.println("Pon el nombre del entrenador a borrar");
            String nombre = sc.nextLine();
            entrenador borrado = entrenadores.esborrarEntrenador(nombre);
            if (borrado!=null)
            {
                System.out.println("Borrado " + borrado);
            }
            else
            {
                System.out.println("El entrenador " + nombre + " no existe en BD");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
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
    private void todosEntrenadores() {
        try {
            //demanar coses a usuari
            //no h'hi
            
            //interacció DAO
            List<entrenador> todos = entrenadores.totsEntrenadors();
            
            System.out.println("Todos los entrenadores pokemon... ");
            //informar usuari
            for (entrenador trainer : todos) {
                System.out.println(trainer);
            }
            System.out.println("Numero de entrenadores... "+ todos.size());
            
            
        } catch (SQLException ex) {
            System.out.println("Error sql" + ex.getSQLState() + " - " + ex.getMessage());
        }
    }
    private boolean validar_usuari() {
            sc = new Scanner(System.in);
            try {
            System.out.print("pon el usuario: ");
            String nombre = sc.nextLine();
            System.out.print("Pon el password: ");
            String contrasenya = sc.nextLine();

            if (entrenadores.existeEntrenador(nombre))
            { //existeix
                entrenador existe = entrenadores.devolverEntrenador(nombre);
                if (existe.getContraseña().equals(contrasenya))
                {
                    System.out.println("Bienvenido de nuevo " + nombre);
                    return true;
                }
                else
                {
                    System.out.println("Password incorrecto no puedes entrar");
                    return false;
                }
            }
            else
            {   
                //si no existeix
                entrenador alta = new entrenador(nombre, contrasenya);
                entrenadores.altaEntrenador(alta);
                System.out.println("Usuario nuevo dado de alta");
                return true;
                
            }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }       
    }

          private void juego_valido() {
        boolean exit = true;
         menudaw menu = new menudaw("Pokemon go ");
                 addAllOptions(menu); 
                 //introDadesProva(/* */);
                 int opcio;
                 
                 //tractar opcio escollida bucle fins que donis sortir no acabi CASA
                 do
                 {
                     //mostrar el menu i escollir opcio CASA
                     opcio = menu.displayMenu();
                     switch(opcio)
                     {
                         case 1: //Sortir
                             salir();
                             exit = true;
                             break;                   
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
                             todosEntrenadores();
                             break;
                         case 6:
                             cazarPokemon();
                             break;
                         case 7:
                             listarMochila();
                             break;
                         case 8:
                             listarTodosPokemons();
                             break;

                     } 
                 }while(!exit); 
    }
        
}


         