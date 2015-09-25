package loguin;


import javafx.stage.Screen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public interface ControlledScreen {
     //This method will allow the injection of the Parent ScreenPane
     public void setScreenParent(Screen screenPage);
}
