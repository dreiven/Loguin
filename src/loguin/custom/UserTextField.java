/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loguin.custom;

import javafx.scene.control.TextField;

/**
 *
 * @author PC
 */
public class UserTextField extends TextField {
    
    //se crea metodo para setear promptext en el objeto UserTextField
    public UserTextField (){
    //accedemos al objeto con this y seteamos SetPromptText
    this.setPromptText("Introduzca Letras");
    
    }

    @Override
    //se Override metodo para reemplazar el texto si no cumple unas condiciones
    public void replaceText(int start, int end, String text) {
        //si el texto concuerda con letras mayus y minus o el texto esta vacio 
       if (text.matches("[a-zA-Z]")|| text.isEmpty() ){
       //reemplazamos el texto con los tres parametros
       super.replaceText(start, end, text);
       
       
       }
    }

    @Override
    // se Override metodo replaceSelection
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); 
    }

    
    
    
    
    
    
    
}
