/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
   
public class CalculatriceController implements Initializable { 
    

 @FXML
    private TextField Calculatrice;
    String op="";
    long number1;
    long number2; 
    @FXML
    private Button btnlc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Number(ActionEvent ae) {
        String no=((Button)ae.getSource()).getText();
        Calculatrice.setText(Calculatrice.getText()+no);
    }
    public void calculate(long n1,long n2,String op ){
        switch(op){
        case"+": Calculatrice.setText(n1+n2+"");break;
        case"-": Calculatrice.setText(n1-n2+"");break;
        case"*": Calculatrice.setText(n1*n2+"");break;
        case"/":
        if(n2==0){
    Calculatrice.setText("0");break;
    }
        Calculatrice.setText((double)n1/n2+"");break;
        
    }
    }

    @FXML
    private void Operation(ActionEvent ae) {
String operation=((Button)ae.getSource()).getText();
if(!operation.equals("="))
{
if(!op.equals("")){
    return;
}
op=operation;
number1=Long.parseLong(Calculatrice.getText());
Calculatrice.setText("");
    } 
    else{
    if(op.equals("")){
        return;
    }
    
    //Calculatrice.setText("");
    number2=Long.parseLong(Calculatrice.getText());
    calculate(number1,number2,op);
    op="";
}} 

    @FXML
    private void RevenirLc(ActionEvent event) {
         try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource(""
                    + "OffreFrontFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}