/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

// Custom Exception to be thrown if the user input name is invalid. 

public class NameInputException extends Exception{
    
    public NameInputException() {
        super("You cant input an ','!");
    }
}
