/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loguin;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 *
 * @author WorksTation
 */
public class FXMLDocumentController implements Initializable, ControlledScreen {

    @FXML
    //se declara variable tipo Label
    private Label lbl_dpt;
    @FXML
    //se declara variable tipo Label
    private Label label;

    @FXML
    //se declara variable tipo TextField
    private TextField user;

    @FXML
    //se declara variable tipo String
    private String texto = "usuario";
    @FXML
    //se declara variable tipo Button
    private Button btn_salir;
    @FXML
    //se declara variable tipo Button
    private Button btn_aceptar;
    @FXML
    //se declara variable tipo PasswordField
    private PasswordField Pf_pass;
    @FXML
    //se declara variable tipo Stage
    private Stage stage;
    @FXML
    //se declara variable tipo Stage
    private Stage ventana;
    @FXML
    //se declara variable tipo Label
    private Label lbl_user;

    @FXML
    //se declara variable tipo Label
    private Label lbl_pass;
    @FXML
   // se declara variable tipo scene
    private Scene scene1;

    @FXML
    //se declara variable choicebox
    private ChoiceBox chkbox;

    
    @FXML
    //se declara metodo con excepciones para capturar la accion de pulsar el boton 
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //se declara variables string para capturar el user y el password que introduce el usuario
        String usuario = user.getText();
        String pass = Pf_pass.getText();
        //comienza bloque try/catch
        try {

            //seteamos la propiedad  text de label con un string
            lbl_dpt.setText("Loading....");
            //instanciamos una nueva conexion con driver Mysql
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //creamos el objeto conexion de tipo Connection para utilizar el driver instanciado Mysql
            Connection conexion = null;
            //guardamos la url donde esta la bbdd en una variable String
            String url = "jdbc:mysql://localhost:3306/Control";
            //Se guarda en una variable String el usuario para acceder a la bbdd
            String user = "root";
            String password = "";
            //agregamos al objeto conexion la url el usuario y el password a utilizar en la bbdd
            conexion = DriverManager.getConnection(url, user, password);
            //creamos el objeto sentencia de tipo Statement 
            Statement sentencia = conexion.createStatement();
            //creamos objeto result tipo ResultSet para guardar la query a la bbdd, la cual comprueba que este el user y password enviados por el usuario
            ResultSet result = sentencia.executeQuery("SELECT * FROM Control.usuarios WHERE Nombre = '" + usuario + "'AND Password =" + pass);
            //si la query result tiene valor entonces se activa el bucle
            if (result.next()) {
                FXMLLoader loader2 = new FXMLLoader(Loguin.class.getResource("form2.fxml"));
                Parent ventana = FXMLLoader.load(getClass().getResource("form2.fxml"));
                Scene scene = new Scene(ventana);
                Stage stage = new Stage();

                stage.setTitle("Congratulations");
                stage.setScene(scene);
                stage.show();
                usuario = (String) lbl_user.getText();
                lbl_user.setText(usuario);
                lbl_pass.setText(pass);
                //mostramos mensaje por pantalla de usuario aceptado
                System.out.println("User Acepted");
            } else {
                //se muestra en la label 
                label.setText("Usuario no encontrado");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    //se crea metodo para capturar la accion en el boton reset
    private void resetButtonAction() throws Throwable {
        String list[]= {"1","2","3","5"};       
        //se muestra mensaje por consola
        System.out.println("You Reset el usuario");
        //se setean la propiedad Text a ""
        user.setText("");
        Pf_pass.setText("");
        //se setea la propiedad text de la label lbl_dpt con un nuevo string 
        lbl_dpt.setText("Reset...");
        
    }

    @FXML
    //se crea metodo para realizar accion de salir de la aplicacion a traves del btn_salir
    private void salirbuttonAction() {
        //se setea el texto en la label
        lbl_dpt.setText("Saliendo...");
        //se accede al stage actual y se linkea con el btn_salir la scene
        Stage stage1 = (Stage) btn_salir.getScene().getWindow();
       //accedemos al objeto stage1 y le damos el metodo para cerrar 
        stage1.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    //seimplementa la interfaz para controlar las distintas pantallas
    public void setScreenParent(Screen screenPage) {
        Screen myController;
        myController = screenPage;
    }

    @FXML
    //se implementa metodo para el cambio del choicebox coincida con un texto especifico en la label 
    private void cambioDept(){
        //se declara final un array de strings con valores para mostrar
        final String[] departamentos = new String []{"Ventas","Compras","Publicidad","Direccion"};
        //se accede a la propiedad tooltip del chkbox
        chkbox.setTooltip(new Tooltip("Seleccione el departamento"));
//        chkbox.setItems(FXCollections.observableArrayList("01","02",new Separator(),"03","54"));
        //accedemos a getselectioModel y al index de los elementos y añadimos un listener para almaceanr cuando cambie el numero de index
        chkbox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>(){

        @Override
        //se overrridea metodo para el cambio de texto en la label a partir del metodo changed del Listener
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            //accedemos a la propiedad setText de la label y le pasamos el numero de index anterior y el nuevo valor
            lbl_dpt.setText(departamentos[newValue.intValue()]);
        }});} 

}
