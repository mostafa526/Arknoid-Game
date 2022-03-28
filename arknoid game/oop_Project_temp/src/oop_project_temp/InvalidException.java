/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_project_temp;

/**
 *
 * @author tride
 */
public class InvalidException extends Exception {
    String Message;
    public InvalidException(String Message){
        super(Message);
    }
}
