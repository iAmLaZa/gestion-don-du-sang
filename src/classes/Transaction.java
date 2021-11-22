package classes;

import interfaces.Itransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Transaction implements Itransaction {
    int IDtransaction,IDdemmande;
    String typeDon,IDrecepteur;
    int IDdonneur;
    LocalDate dateTransaction;

    public Transaction(int IDtransaction, int IDdemmande, String typeDon, LocalDate dateTransaction, int donner, String recepteur) {
        this.IDtransaction = IDtransaction;
        this.IDdemmande = IDdemmande;
        this.typeDon = typeDon;
        this.dateTransaction = dateTransaction;
        this.IDdonneur = donner;
        this.IDrecepteur = recepteur;
    }

    public Transaction(int IDdemmande, String typeDon, String IDrecepteur, int IDdonneur, LocalDate dateTransaction) {
        this.IDdemmande = IDdemmande;
        this.typeDon = typeDon;
        this.IDrecepteur = IDrecepteur;
        this.IDdonneur = IDdonneur;
        this.dateTransaction = dateTransaction;
    }

    public Transaction() {
    }

    public int getIDtransaction() {
        return IDtransaction;
    }

    public void setIDtransaction(int IDtransaction) {
        this.IDtransaction = IDtransaction;
    }

    public int getIDdemmande() {
        return IDdemmande;
    }

    public void setIDdemmande(int IDdemmande) {
        this.IDdemmande = IDdemmande;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public String getIDrecepteur() {
        return IDrecepteur;
    }

    public void setIDrecepteur(String IDrecepteur) {
        this.IDrecepteur = IDrecepteur;
    }

    public int getIDdonneur() {
        return IDdonneur;
    }

    public void setIDdonneur(int IDdonneur) {
        this.IDdonneur = IDdonneur;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDate dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
    private static Connection con = BD.connect();
    @Override
    public boolean newtrn() throws SQLException {
        try {
            PreparedStatement pr=con.prepareStatement("insert into transaction(IDdemmande,typeDon,IDdonneur,recepteur,dateTransaction) values" +
                    "("+getIDdemmande()+",'"+getTypeDon()+"',"+getIDdonneur()+",'"+getIDrecepteur()+"','"+getDateTransaction()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ObservableList<Transaction> listetransaction() throws SQLException {
        ObservableList<Transaction> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from transaction ");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new Transaction(rs.getInt("IDtransaction"),rs.getInt("IDdemmande"),rs.getString("typeDon"),rs.getDate("dateTransaction").toLocalDate(),rs.getInt("IDdonneur"),rs.getString("recepteur")));
        return liste;
    }
}
