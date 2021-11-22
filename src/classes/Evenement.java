package classes;

import interfaces.IEVN;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Evenement implements IEVN {
    private static Connection con = BD.connect();
    int ID;
    String type,nomEtablissement;
    LocalDate dateEvenement;

    public Evenement(int ID, String type, String nomEtablissement, LocalDate dateEvenement) {
        this.ID = ID;
        this.type = type;
        this.nomEtablissement = nomEtablissement;
        this.dateEvenement = dateEvenement;
    }

    public Evenement() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public LocalDate getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(LocalDate dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    @Override
    public boolean newEVNT() throws SQLException {
        PreparedStatement pr=con.prepareStatement("insert into evenement(type,nom,dateEv) values('"
                +getType()+"','"+getNomEtablissement()+"','"+getDateEvenement()+"')");
        return pr.execute();
    }

    @Override
    public ObservableList<Evenement> listeEvenements() throws SQLException {
        ObservableList<Evenement> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select *from evenement");
        ResultSet rs=pr.executeQuery();
        while(rs.next()){
            liste.add(new Evenement(rs.getInt("IDEVNT"),rs.getString("type"),rs.getString("nom"),rs.getDate("dateEv").toLocalDate()));
        }
        return liste;
    }
    public int idevn() throws SQLException {
        try {
            PreparedStatement pr = con.prepareStatement(" select last_insert_id();");
            ResultSet rs=pr.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
