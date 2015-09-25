/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loguin.custom;

import javafx.scene.control.PasswordField;

/**
 *
 * @author PC
 */
public class PassTextField extends PasswordField {
   //se declara metodo para acceder y setear la propiedad SetPromptText en PassTextField
    public PassTextField() {
     //setemaos el valor string en la propiedad this para referirnos a todo objeto creado
        this.setPromptText("Introduzca solo numeros");

    }

    @Override
    //se Override replaceText metodo para reemplazar el texto si cumple una condicion
    public void replaceText(int start, int end, String text) {
        //si el texto son numeros del 0 al 9 o el texto esta vacio
        if (text.matches("[0-9]") || text.isEmpty()) {
           // se reemplaza el texto con los parametros 
            super.replaceText(start, end, text);

        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); 
    }

}
