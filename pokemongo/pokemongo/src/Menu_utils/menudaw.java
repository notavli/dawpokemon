/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu_utils;

import Menu_utils.OptionDuplicateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author isard
 */
public class menudaw {
    private String titul;
    private List<String> items;

    public menudaw(String titul) {
        this.titul = titul;
        this.items= new ArrayList<String> ();
        
    }
    public void AddOption (String opcion) throws OptionDuplicateException{
        if(!(items.contains(opcion))){
            items.add(opcion);
            System.out.println("opcion afegida");
        }
        else{
            throw new OptionDuplicateException(opcion);
        }
            
    }
    public void AddOptionWithRepetition(String opcion){ 
        items.add(opcion);
            System.out.println("Opció afegida -> " + opcion);
    }
    
    public void addOptionsByIndex(String opcion, int pos)throws IndexOutOfBoundsException{
        //verificar que pos esta dintre de les que toquen
        //llançar la excepcio cap a dalt
        items.add(pos, opcion);
        System.out.println("Opció afegida -> " + opcion);
    }
        public boolean removeOption(String opcion) {
        return items.remove(opcion);
    }
        public String RemoveOptionByIndex(int pos) throws IndexOutOfBoundsException
        {
           return items.remove(pos);
        }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("********************").append("\n");
        sb.append(titul).append("\n");
        sb.append("********************").append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append((i+1)).append("-").
                    append(items.get(i)).append("\n");
        }
        sb.append("********************").append("\n");
        return sb.toString();
    }
    
    /**
     * et demana que escolleixis una opció, i la retornarà, 
     * sempre i quan sigui una opció valida, 
     * sinó és vàlida seguiré demanant 
     * la opció dintre de les opcions vàlides. Aquest mètode normalment anirà després d’haver cridat al DisplayMenú
     * @return opcion escogida
     */
    public int chooseOption()
    {
        int option;
        
        Scanner sc = new Scanner(System.in);
        do{
        System.out.print("Escoge opcion (1-" + items.size() + "): ");
         option = sc.nextInt();
        }while(option <1 || option > items.size());
        return option;
    }
    
    public int displayMenu()
    {
        // mostrar el menu 
        System.out.println(this.toString());
        //demanar la opció
        int opcio=this.chooseOption();
        //retornar
        return opcio;
        
    }
    public int numberOptions(){
        return items.size();
    }
    
}
