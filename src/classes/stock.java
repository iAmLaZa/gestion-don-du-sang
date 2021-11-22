package classes;

import interfaces.Istock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class stock implements Istock {
    private static Connection con = BD.connect();
    int IDDon,IDDonneur;
    String hospital,groupeSanguin,typeDon;
    LocalDate dateDeCollect,dateDexpiration;
    int nbjour;

    public stock(int IDDon, int IDDonneur, String hospital, String groupeSanguin, String typeDon, LocalDate dateDeCollect, LocalDate dateDexpiration, int nbjour) {
        this.IDDon = IDDon;
        this.IDDonneur = IDDonneur;
        this.hospital = hospital;
        this.groupeSanguin = groupeSanguin;
        this.typeDon = typeDon;
        this.dateDeCollect = dateDeCollect;
        this.dateDexpiration = dateDexpiration;
        this.nbjour = nbjour;
    }

    public stock() {
    }

    public int getIDDon() {
        return IDDon;
    }

    public void setIDDon(int IDDon) {
        this.IDDon = IDDon;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        stock.con = con;
    }

    public int getIDDonneur() {
        return IDDonneur;
    }

    public void setIDDonneur(int IDDonneur) {
        this.IDDonneur = IDDonneur;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public LocalDate getDateDeCollect() {
        return dateDeCollect;
    }

    public void setDateDeCollect(LocalDate dateDeCollect) {
        this.dateDeCollect = dateDeCollect;
    }

    public LocalDate getDateDexpiration() {
        return dateDexpiration;
    }

    public void setDateDexpiration(LocalDate dateDexpiration) {
        this.dateDexpiration = dateDexpiration;
    }

    public int getNbjour() {
        return nbjour;
    }

    public void setNbjour(int nbjour) {
        this.nbjour = nbjour;
    }

    @Override
    public ObservableList<stock> stock() throws SQLException {
        ObservableList<stock> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from stock where dateDexpiration >='"+LocalDate.now()+"'");
        ResultSet rs=pr.executeQuery();
        while(rs.next()) {
            setNbjour((int) ChronoUnit.DAYS.between(LocalDate.now(),rs.getDate("dateDexpiration").toLocalDate()));

            liste.add(new stock(rs.getInt("IDDon"), rs.getInt("IDdonnateur"), rs.getString("hospital"), rs.getString("groupeSanguin"), rs.getString("typeDon"), rs.getDate("dateDeCollect").toLocalDate(), rs.getDate("dateDexpiration").toLocalDate(),getNbjour()));
        }
        return liste;
    }
}
