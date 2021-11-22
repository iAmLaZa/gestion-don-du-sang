package classes;

import classes.DonneurRecepteur;
import interfaces.Idemande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class DemmandeDeDon implements Idemande {
    private static Connection con = BD.connect();
    int ID;
    String groupeSanguin,objectif,remarque,typeDon,etat;
    String recepteur;
    int idonneur;
    boolean pasDeSacs;
    LocalDate dateDemmande;

    public DemmandeDeDon(int ID, String groupeSanguin, String objectif, String remarque, String typeDon, String etat, String recepteur, int idonneur, boolean pasDeSacs, LocalDate dateDemmande) {
        this.ID = ID;
        this.groupeSanguin = groupeSanguin;
        this.objectif = objectif;
        this.remarque = remarque;
        this.typeDon = typeDon;
        this.etat = etat;
        this.recepteur = recepteur;
        this.idonneur = idonneur;
        this.pasDeSacs = pasDeSacs;
        this.dateDemmande = dateDemmande;
    }

    public DemmandeDeDon() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdonneur() {
        return idonneur;
    }

    public void setIdonneur(int idonneur) {
        this.idonneur = idonneur;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(String recepteur) {
        this.recepteur = recepteur;
    }

    public boolean isPasDeSacs() {
        return pasDeSacs;
    }

    public void setPasDeSacs(boolean pasDeSacs) {
        this.pasDeSacs = pasDeSacs;
    }

    public LocalDate getDateDemmande() {
        return dateDemmande;
    }

    public void setDateDemmande(LocalDate dateDemmande) {
        this.dateDemmande = dateDemmande;
    }

    @Override
    public boolean adddemande_tran()  {
        try {
            PreparedStatement pr=con.prepareStatement("insert into demdon(groupeSanguin,objectif,remarque,typeDon,etat,IDdonneur,recepteur,pasDeSacs,dateDemmande) values" +
                    "('"+getGroupeSanguin()+"','"+getObjectif()+"','"+getRemarque()+"','"+getTypeDon()+"','"+getEtat()+"',"+getIdonneur()+",'"+getRecepteur()+"',"+isPasDeSacs()+",'"+getDateDemmande()+"')");
                    return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return false;
    }

    @Override
    public boolean adddemande() throws SQLException {
        try {
            PreparedStatement pr=con.prepareStatement("insert into demdon(groupeSanguin,objectif,remarque,typeDon,etat,recepteur,pasDeSacs,dateDemmande) values" +
                    "('"+getGroupeSanguin()+"','"+getObjectif()+"','"+getRemarque()+"','"+getTypeDon()+"','"+getEtat()+"','"+getRecepteur()+"',"+isPasDeSacs()+",'"+getDateDemmande()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatedemande() throws SQLException {
        PreparedStatement pr=con.prepareStatement("update  demdon  set etat='"+getEtat()+"' where ID="+getID());
        return !pr.execute();
    }


    @Override
    public ObservableList<DemmandeDeDon> listedemande() throws SQLException {
        ObservableList<DemmandeDeDon> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from demdon where etat='Non_approuver'");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new DemmandeDeDon(rs.getInt("ID"),rs.getString("groupeSanguin"),rs.getString("objectif"),rs.getString("remarque"),rs.getString("typeDon"),rs.getString("etat"), rs.getString("recepteur"),rs.getInt("IDdonneur"),rs.getBoolean("pasDeSacs"),rs.getDate("dateDemmande").toLocalDate()));
        return liste;
    }
}
