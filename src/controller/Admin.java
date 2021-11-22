package controller;

import classes.*;
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

public class Admin  implements Initializable {
    Evenement evn=new Evenement();
    Don don=new Don();
    RDV rdv=new RDV();
    Transaction trn=new Transaction();
    DonneurRecepteur dr=new DonneurRecepteur("","",0);
    DemmandeDeDon demmande=new DemmandeDeDon();
    stock stock=new stock();
    int i;
    @FXML
    Label l;
    @FXML
    AnchorPane anchorevenement,gestionstock,gestiondons,dons,demande,transaction,historique;
    public void gestiondons(){
        gestiondons.setVisible(true);
        gestionstock.setVisible(false);
        l.setVisible(false);
    }
    public void gestionstock(){
        gestiondons.setVisible(false);
        gestionstock.setVisible(true);
        l.setVisible(false);

        try {
            listestock=stock.stock();
            tablestock.setItems(listestock);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    TableView<stock> tablestock;
    @FXML
    TableColumn<stock,Integer> iddonstock;
    @FXML
    TableColumn<stock,Integer> iddonneurstock;
    @FXML
    TableColumn<stock,String> hospitalstock;
    @FXML
    TableColumn<stock,String> grpsnstock;
    @FXML
    TableColumn<stock,Integer> nbjourstock;
    @FXML
    TableColumn<stock,String> typestock;
    @FXML
    TableColumn<stock,LocalDate> datecstock;
    @FXML
    TableColumn<stock,LocalDate> dateexpstock;

    ObservableList<stock> listestock;
    @FXML
    TextField searchstock;
    public void searchstock(){
        outils.searchglobale(searchstock,listestock,tablestock);
    }


    public void historique(){
        anchorevenement.setVisible(false);
        dons.setVisible(false);
        demande.setVisible(false);
        transaction.setVisible(false);
        historique.setVisible(true);

        try {
            listehis=don.historique();
            tablehis.setItems(listehis);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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



    public void transaction(){
        anchorevenement.setVisible(false);
        dons.setVisible(false);
        demande.setVisible(false);
        transaction.setVisible(true);
        historique.setVisible(false);

        try {
            listetrn=trn.listetransaction();
            tabletrn.setItems(listetrn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    TableView<Transaction> tabletrn;
    @FXML
    TableColumn<Transaction,Integer> idtrn;
    @FXML
    TableColumn<Transaction,Integer> iddtrn;
    @FXML
    TableColumn<Transaction,String> typetrn;
    @FXML
    TableColumn<Transaction,Integer> iddonneurtrn;
    @FXML
    TableColumn<Transaction,String> recepteurtrn;
    @FXML
    TableColumn<Transaction,LocalDate> datetrn;

    ObservableList<Transaction> listetrn;

    @FXML
    TextField searchtrn;

    public void searchtrn(){
        outils.searchglobale(searchtrn,listetrn,tabletrn);
    }

    public void demande(){
        anchorevenement.setVisible(false);
        dons.setVisible(false);
        demande.setVisible(true);
        transaction.setVisible(false);
        historique.setVisible(false);
    }
    @FXML
    TextField recepteur,remarque,objective;
    @FXML
    DatePicker datedemande;
    @FXML
    ChoiceBox<String> grpsang,typedon,idonneur;
    @FXML
    CheckBox sacdemande;

    public void newdemande(){
        idonneur.setVisible(true);
        recepteur.setVisible(true);
        remarque.setVisible(true);
        objective.setVisible(true);
        datedemande.setVisible(true);
        grpsang.setVisible(true);
        typedon.setVisible(true);
        sacdemande.setVisible(true);
        adddemande.setVisible(true);
    }
    public void adddemande() throws SQLException {
        demmande=new DemmandeDeDon();
        demmande.setDateDemmande(datedemande.getValue());
        demmande.setTypeDon(typedon.getValue());
        demmande.setRecepteur(recepteur.getText());
        demmande.setObjectif(objective.getText());
        demmande.setGroupeSanguin(grpsang.getValue());
        demmande.setRemarque(remarque.getText());
        if(sacdemande.isSelected()){demmande.setPasDeSacs(true);}
        else {demmande.setPasDeSacs(true);}


        if(idonneur.getValue().equals("")){
            demmande.setEtat("Non_approuver");
            demmande.adddemande();
            demmande.setID(evn.idevn());
            listedemande.add(demmande);

        }else{

            if(grpsang.getValue().equals(dr.grpsan(Integer.valueOf(idonneur.getValue())))){
                demmande.setIdonneur(Integer.valueOf(idonneur.getValue()));
                demmande.setEtat("approuver");
                demmande.adddemande_tran();
                demmande.setID(evn.idevn());
                listedemande.add(demmande);
            }else{
                outils.showerroronmessage("erruer","deference de  sang :\n"+dr.grpsan(Integer.valueOf(idonneur.getValue())));
            }

        }

        idonneur.setVisible(false);
        recepteur.setText("");
        recepteur.setVisible(false);
        remarque.setText("");
        remarque.setVisible(false);
        objective.setText("");
        objective.setVisible(false);
        datedemande.setVisible(false);
        grpsang.setVisible(false);
        typedon.setVisible(false);
        sacdemande.setVisible(false);
        adddemande.setVisible(false);
    }

    public void approuver(){
        demmande=tabledemande.getSelectionModel().getSelectedItem();
        i=listedemande.indexOf(demmande);
        validerdemmande.setVisible(true);
        if(demmande.getIdonneur()==0)
            idonneur.setVisible(true);
        else idonneur.setVisible(false);
    }
    public void validerdemmande() throws SQLException {
        if((idonneur.isVisible()) && !(idonneur.getValue().equals(""))){
            demmande.setIdonneur(Integer.valueOf(idonneur.getValue()));
            if(demmande.getGroupeSanguin().equals(dr.grpsan(Integer.valueOf(idonneur.getValue())))){
                trn=new Transaction(demmande.getID(),demmande.getTypeDon(),demmande.getRecepteur(),demmande.getIdonneur(),LocalDate.now());
                trn.newtrn();
                outils.showconfirmationmessage("add_transaction","transaction ajouter");
                demmande.setEtat("approuver");
                demmande.updatedemande();
                listedemande.remove(i);
            }else{
                outils.showerroronmessage("erruer","deference de  sang :\n"+dr.grpsan(Integer.valueOf(idonneur.getValue())));
            }
            idonneur.setVisible(false);
        }else{
            trn=new Transaction(demmande.getID(),demmande.getTypeDon(),demmande.getRecepteur(),demmande.getIdonneur(),LocalDate.now());
            trn.newtrn();
            outils.showconfirmationmessage("add_transaction","transaction ajouter");
            demmande.setEtat("approuver");
            demmande.updatedemande();
            listedemande.remove(i);
        }
        validerdemmande.setVisible(false);
    }

    @FXML
    TableView<DemmandeDeDon> tabledemande;
    @FXML
    TableColumn<DemmandeDeDon,Integer> idemande;
    @FXML
    TableColumn<DemmandeDeDon,Integer> idoneurtable;
    @FXML
    TableColumn<DemmandeDeDon,String> recepteurtable;
    @FXML
    TableColumn<DemmandeDeDon,String> objectiftable;
    @FXML
    TableColumn<DemmandeDeDon,String> remarquetable;
    @FXML
    TableColumn<DemmandeDeDon,String> typedontable;
    @FXML
    TableColumn<DemmandeDeDon,Boolean> sactable;
    @FXML
    TableColumn<DemmandeDeDon,LocalDate> datedemandetable;
    @FXML
    TableColumn<DemmandeDeDon,String> grpsntable;

    ObservableList<DemmandeDeDon> listedemande;

    @FXML
    TextField searchdemande;

    public void searchdemande(){
        outils.searchglobale(searchdemande,listedemande,tabledemande);
    }




    public void evenement(){
        anchorevenement.setVisible(true);
        dons.setVisible(false);
        demande.setVisible(false);
        transaction.setVisible(false);
        historique.setVisible(false);
    }
    @FXML
    ChoiceBox<String> type;
    @FXML
    TextField nom;
    @FXML
    DatePicker date;

    @FXML
    TableView<Evenement> tableevenement;
    @FXML
    TableColumn<Evenement,Integer> idtable;
    @FXML
    TableColumn<Evenement,String> typetable;
    @FXML
    TableColumn<Evenement,String> nomtable;
    @FXML
    TableColumn<Evenement, LocalDate> datetable;
    ObservableList<Evenement> listeevenement;

    @FXML
    TextField searchevn;
    public void searchevn(){
        outils.searchglobale(searchevn,listeevenement,tableevenement);
    }
    public void addevn(){
        addevn.setVisible(false);
        confirmer.setVisible(true);
        type.setVisible(true);
        date.setVisible(true);
        nom.setVisible(true);
    }
    public void confirmer() throws SQLException {
        evn.setDateEvenement(date.getValue());
        evn.setNomEtablissement(nom.getText());
        evn.setType(type.getValue());
        evn.newEVNT();
        evn.setID(evn.idevn());
        listeevenement.add(evn);
        addevn.setVisible(true);
        confirmer.setVisible(false);
        type.setVisible(false);
        date.setVisible(false);
        nom.setText("");
        nom.setVisible(false);
        outils.showconfirmationmessage("add_evenement","evenement ajouter");
    }

    public void dons(){
        anchorevenement.setVisible(false);
        dons.setVisible(true);
        demande.setVisible(false);
        transaction.setVisible(false);
        historique.setVisible(false);
    }
    @FXML
    TableView<RDV> tablerdv;
    @FXML
    TableColumn<RDV,Integer> idrdv;
    @FXML
    TableColumn<RDV,Integer> iddonrdv;
    @FXML
    TableColumn<RDV,String> typedonrdv;
    @FXML
    TableColumn<RDV, String> grpsnrdv;
    @FXML
    TableColumn<RDV, LocalDate> datedonrdv;
    ObservableList<RDV> listerdv;

    @FXML
    TextField searchrdv,medcin,hospital;
    @FXML
    CheckBox sac,passe,vhc,vih,vhb,sy;
    @FXML
    DatePicker rdvpardate;
    public void rdvpardate(){
        try {
            listerdv=rdv.listerdv(rdvpardate.getValue());
            tablerdv.setItems(listerdv);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void searchrdv(){
        outils.searchglobale(searchrdv,listerdv,tablerdv);
    }
    public void selectrdv(){
        rdv=tablerdv.getSelectionModel().getSelectedItem();
        i=listerdv.indexOf(rdv);
        validerdon.setVisible(true);
        medcin.setVisible(true);
        hospital.setVisible(true);
        sac.setVisible(true);
        passe.setVisible(true);

    }
    public void mala(){
        vhc.setVisible(true);
        vih.setVisible(true);
        vhb.setVisible(true);
        sy.setVisible(true);
    }
    public void validerdon() throws SQLException {
        don.setIDdonnateur(rdv.getIDdonneur());
        don.setDateDeCollect(LocalDate.now());
        don.setDateDexpiration(LocalDate.now().plusDays(42));
        don.setHospital(hospital.getText());
        don.setInfermiereMedcin(medcin.getText());
        don.setTypeDon(rdv.getTypeDon());
        don.setGroupeSanguin(rdv.getGroupeSanguin());
        if(passe.isSelected())
            don.setPassé(true);
        else don.setPassé(false);
        if(sac.isSelected())
            don.setPasDeSacDeSang(true);
        else
            don.setPasDeSacDeSang(false);
        if(!(vhc.isSelected() || vhb.isSelected() || vih.isSelected() || sy.isSelected()) ) {don.setInstock(true);don.setMaldadie("saint");}
            else{
                don.setInstock(false);
                StringBuffer ml=new StringBuffer();
                if(vhc.isSelected()) ml.append("vhc/");
                if(vhb.isSelected()) ml.append("vhb/");
                if(vih.isSelected()) ml.append("vih/");
                if(sy.isSelected()) ml.append("syphilis/");
                don.setMaldadie(ml.toString());
        }


        don.newDon();
        rdv.deleteRDV();
        listerdv.remove(i);
        validerdon.setVisible(false);
        medcin.setText("");
        medcin.setVisible(false);
        hospital.setText("");
        hospital.setVisible(false);
        sac.setVisible(false);
        passe.setVisible(false);
        vhc.setVisible(false);
        vih.setVisible(false);
        vhb.setVisible(false);
        sy.setVisible(false);

    }
    @FXML
    Button out,confirmer,addevn,validerdon,adddemande,validerdemmande;
    public void logout(ActionEvent actionEvent) throws IOException {
        outils.loadp(actionEvent, "Log_IN", "/sample/sample.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList l= FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
        grpsang.setItems(l);
        ObservableList lll= FXCollections.observableArrayList("sang","concentrés De Globules Rouges","plasma","plaquettes");
        typedon.setItems(lll);
        ObservableList ll= FXCollections.observableArrayList("Bureau regional","Camps Militaire","Camps Policier","Ecole","Universite");
        type.setItems(ll);

        try {
            idonneur.setItems(dr.codeDonneur());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idtable.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("ID"));
        typetable.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        nomtable.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nomEtablissement"));
        datetable.setCellValueFactory(new PropertyValueFactory<Evenement, LocalDate>("dateEvenement"));

        try {
            listeevenement=evn.listeEvenements();
            tableevenement.setItems(listeevenement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idrdv.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("ID"));
        iddonrdv.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("IDdonneur"));
        typedonrdv.setCellValueFactory(new PropertyValueFactory<RDV, String>("typeDon"));
        grpsnrdv.setCellValueFactory(new PropertyValueFactory<RDV, String>("groupeSanguin"));
        datedonrdv.setCellValueFactory(new PropertyValueFactory<RDV, LocalDate>("dateDon"));

        try {
            listerdv=rdv.listerdv_all();
            tablerdv.setItems(listerdv);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        idemande.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, Integer>("ID"));
        idoneurtable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, Integer>("idonneur"));
        recepteurtable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, String>("recepteur"));
        remarquetable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, String>("remarque"));
        objectiftable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, String>("objectif"));
        typedontable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, String>("typeDon"));
        sactable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, Boolean>("pasDeSacs"));
        datedemandetable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, LocalDate>("dateDemmande"));
        grpsntable.setCellValueFactory(new PropertyValueFactory<DemmandeDeDon, String>("groupeSanguin"));


        try {
            listedemande=demmande.listedemande();
            tabledemande.setItems(listedemande);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idtrn.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("IDtransaction"));
        iddtrn.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("IDdemmande"));
        typetrn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("typeDon"));
        iddonneurtrn.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("IDdonneur"));
        recepteurtrn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("IDrecepteur"));
        datetrn.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("dateTransaction"));

        try {
            listetrn=trn.listetransaction();
            tabletrn.setItems(listetrn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        iddonhis.setCellValueFactory(new PropertyValueFactory<Don, Integer>("ID"));
        iddonneurhis.setCellValueFactory(new PropertyValueFactory<Don, Integer>("IDdonnateur"));
        typehis.setCellValueFactory(new PropertyValueFactory<Don, String>("typeDon"));
        medhis.setCellValueFactory(new PropertyValueFactory<Don, String>("infermiereMedcin"));
        grpsnhis.setCellValueFactory(new PropertyValueFactory<Don, String>("groupeSanguin"));
        hospitalhis.setCellValueFactory(new PropertyValueFactory<Don, String>("hospital"));
        maladiehis.setCellValueFactory(new PropertyValueFactory<Don, String>("maldadie"));
        datehis.setCellValueFactory(new PropertyValueFactory<Don, LocalDate>("dateDeCollect"));

        try {
            listehis=don.historique();
            tablehis.setItems(listehis);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        iddonstock.setCellValueFactory(new PropertyValueFactory<stock, Integer>("IDDon"));
        iddonneurstock.setCellValueFactory(new PropertyValueFactory<stock, Integer>("IDDonneur"));
        typestock.setCellValueFactory(new PropertyValueFactory<stock, String>("typeDon"));
        nbjourstock.setCellValueFactory(new PropertyValueFactory<stock, Integer>("nbjour"));
        grpsnstock.setCellValueFactory(new PropertyValueFactory<stock, String>("groupeSanguin"));
        hospitalstock.setCellValueFactory(new PropertyValueFactory<stock, String>("hospital"));
        datecstock.setCellValueFactory(new PropertyValueFactory<stock, LocalDate>("dateDeCollect"));
        dateexpstock.setCellValueFactory(new PropertyValueFactory<stock, LocalDate>("dateDexpiration"));

        try {
            listestock=stock.stock();
            tablestock.setItems(listestock);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
