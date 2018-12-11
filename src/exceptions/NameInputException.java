/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Morten K. Jensen
 */
public class NameInputException extends Exception{
    
    public NameInputException() {
        super("You cant input an ','!");
    }
}
