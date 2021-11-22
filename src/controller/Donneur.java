package controller;

import classes.Don;
import classes.DonneurRecepteur;
import classes.RDV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manipulation.outils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Donneur implements Initializable {
    DonneurRecepteur dn;
    RDV r=new RDV();
    public void showinformation(DonneurRecepteur d){

        dn=d;
        grpsn.setText(dn.getGroupeSanguin());
        idonneur.setText(String.valueOf(dn.getID()));
        //date.setValue(LocalDate.now());
        nom.setText(dn.getNom());
        prenom.setText(dn.getPrenom());
        adresse.setText(dn.getAdress());
        email.setText(dn.getEmail());
        tel.setText(dn.getNumTel());
        age.setText(String.valueOf(dn.getAge()));
        grpsnupdate.setValue(dn.getGroupeSanguin());
        gendre.setValue(dn.getGenre());

        try {
            listehis=don.historique_Donneur(dn.getID());
            tablehis.setItems(listehis);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //prendre un nouveau rdv
        r=r.rdv_donneur(dn.getID());
        if(r==null){

                if ((listehis.size()>0) &&(listehis.get(listehis.size()-1).getDateDeCollect().plusDays(23).isAfter( LocalDate.now()))){

                    prdon.setVisible(true);
                    date.setValue(listehis.get(listehis.size()-1).getDateDeCollect().plusDays(23));
                    idonneur.setVisible(false);
                    type.setVisible(false);
                    grpsn.setVisible(false);
                }else{
                    annuler.setVisible(false);
                    confirmer.setVisible(true);
                    r=new RDV();
                }



        }else{
            confirmer.setVisible(false);
            prdv.setVisible(true);
            type.setValue(r.getTypeDon());
            date.setValue(r.getDateDon());
            annuler.setVisible(true);
        }

    }
    @FXML
    AnchorPane anchorrdv,anchorliste;
    @FXML
    Label l,idonneur,grpsn,prdv,prdon;
    @FXML
    ChoiceBox<String> type;
    @FXML
    DatePicker date;
    Don don=new Don();

    public void listedons(){
        l.setVisible(false);
        anchorliste.setVisible(true);
        anchorrdv.setVisible(false);


    }
    public void rdv(){
        l.setVisible(false);
        anchorliste.setVisible(false);
        anchorrdv.setVisible(true);
    }
    @FXML
    Button confirmer,annuler;
    public void annuler() throws SQLException {
        r.deleteRDV();
        annuler.setVisible(false);
        confirmer.setVisible(true);
        r=new RDV();
        date.setValue(LocalDate.now());
        type.setValue("sang");
        prdv.setVisible(false);
    }

    public void confirmer() throws SQLException {
        RDV rdv=new RDV();
        rdv.setDateDon(date.getValue());
        rdv.setIDdonneur(dn.getID());
        rdv.setGroupeSanguin(grpsn.getText());
        rdv.setTypeDon(type.getValue());
        rdv.newRDV();
        date.setValue(LocalDate.now());
        confirmer.setVisible(false);
        prdv.setVisible(true);
        annuler.setVisible(true);
    }
    @FXML
    TextField nom,prenom,adresse,email,tel,age;
    @FXML
    ChoiceBox<String> grpsnupdate,gendre;
    public void update() throws SQLException {
        dn=new DonneurRecepteur(email.getText(),dn.getMDP(),dn.getID(),nom.getText(),
                prenom.getText(),adresse.getText(),
                tel.getText(),gendre.getValue(),grpsnupdate.getValue(),"Donneur",Integer.valueOf(age.getText()));
                dn.update();
                grpsn.setText(grpsnupdate.getValue());
    }

    @FXML
    TableView<Don> tablehis;
    @FXML
    TableColumn<Don,Integer> iddonhis;
    @FXML
    TableColumn<Don,Integer> iddonneurhis;
    @FXML
    TableColumn<Don,String> hospitalhis;
    @FXML
    TableColumn<Don,String> grpsnhis;
    @FXML
    TableColumn<Don,String> medhis;
    @FXML
    TableColumn<Don,String> typehis;
    @FXML
    TableColumn<Don,LocalDate> datehis;
    @FXML
    TableColumn<Don,String> maladiehis;

    ObservableList<Don> listehis;

    @FXML
    TextField searchhis;
    public void searchhis(){
        outils.searchglobale(searchhis,listehis,tablehis);
    }



    @FXML
    Button out;
    public void logout(ActionEvent actionEvent) throws IOException {
        outils.loadp(actionEvent, "Log_IN", "/sample/sample.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList l= FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
        grpsnupdate.setItems(l);
        ObservableList lll= FXCollections.observableArrayList("Homme","Femme","106");
        gendre.setItems(lll);




        ObservableList ll= FXCollections.observableArrayList("sang","concentrés De Globules Rouges","plasma","plaquettes");
        type.setItems(ll);


        iddonhis.setCellValueFactory(new PropertyValueFactory<Don, Integer>("ID"));
        iddonneurhis.setCellValueFactory(new PropertyValueFactory<Don, Integer>("IDdonnateur"));
        typehis.setCellValueFactory(new PropertyValueFactory<Don, String>("typeDon"));
        medhis.setCellValueFactory(new PropertyValueFactory<Don, String>("infermiereMedcin"));
        grpsnhis.setCellValueFactory(new PropertyValueFactory<Don, String>("groupeSanguin"));
        hospitalhis.setCellValueFactory(new PropertyValueFactory<Don, String>("hospital"));
        maladiehis.setCellValueFactory(new PropertyValueFactory<Don, String>("maldadie"));
        datehis.setCellValueFactory(new PropertyValueFactory<Don, LocalDate>("dateDeCollect"));



    }
}
