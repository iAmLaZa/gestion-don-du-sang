package controller;

import classes.DonneurRecepteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class newdonneur implements Initializable {

    @FXML
    TextField nom,prenom,adresse,email,password,tel,age;
    @FXML
    ChoiceBox<String> grpsn,gendre;
    @FXML
    Button add;


    public void addonneur() throws SQLException {
        DonneurRecepteur d=new DonneurRecepteur(email.getText(),password.getText(),0,nom.getText(),
                prenom.getText(),adresse.getText(),
                tel.getText(),gendre.getValue(),grpsn.getValue(),"Donneur",Integer.valueOf(age.getText()));
        d.newdonneur();
        Stage s =(Stage) add.getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList l= FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
        grpsn.setItems(l);
        ObservableList ll= FXCollections.observableArrayList("Homme","Femme");
        gendre.setItems(ll);
    }
}
