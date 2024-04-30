/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu_utils;

/**
 *
 * @author isard
 */
public class OptionDuplicateException extends Exception {

    public OptionDuplicateException(String message) {
        super("opcion ya existida  "+message);
    }

    
    
}
