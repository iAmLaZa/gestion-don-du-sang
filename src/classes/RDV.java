package classes;

import interfaces.IRDV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RDV implements IRDV {
    private static Connection con = BD.connect();
    int ID,IDdonneur;
    String typeDon,groupeSanguin;
    LocalDate dateDon;

    public RDV(int ID, int IDdonneur, String typeDon, String groupeSanguin, LocalDate dateDon) {
        this.ID = ID;
        this.IDdonneur = IDdonneur;
        this.typeDon = typeDon;
        this.groupeSanguin = groupeSanguin;
        this.dateDon = dateDon;
    }

    public RDV() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDdonneur() {
        return IDdonneur;
    }

    public void setIDdonneur(int IDdonneur) {
        this.IDdonneur = IDdonneur;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public LocalDate getDateDon() {
        return dateDon;
    }

    public void setDateDon(LocalDate dateDon) {
        this.dateDon = dateDon;
    }

    @Override
    public boolean newRDV() {

        try {
            PreparedStatement pr = con.prepareStatement("insert into RDV(IDonneur,typededons,grpsn,datedon) values("
                    +getIDdonneur()+",'"+getTypeDon()+"','"+getGroupeSanguin()+"','"+getDateDon()+"')");
            return pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRDV() throws SQLException {
        PreparedStatement pr=con.prepareStatement("delete from rdv where IDRDV="+getID());
        return !pr.execute();
    }

    @Override
    public ObservableList<RDV> listerdv(LocalDate date) throws SQLException {
        ObservableList<RDV> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from rdv where datedon='"+date+"'");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new RDV(rs.getInt("IDRDV"),rs.getInt("IDonneur"),rs.getString("typededons"),rs.getString("grpsn"),rs.getDate("datedon").toLocalDate()));
        return liste;
    }

    @Override
    public ObservableList<RDV> listerdv_all() throws SQLException {
        ObservableList<RDV> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from rdv");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new RDV(rs.getInt("IDRDV"),rs.getInt("IDonneur"),rs.getString("typededons"),rs.getString("grpsn"),rs.getDate("datedon").toLocalDate()));
        return liste;
    }

    @Override
    public RDV rdv_donneur(int id) {
        try {
            PreparedStatement pr=con.prepareStatement("select * from rdv where IDonneur="+id);
            ResultSet rs=pr.executeQuery();
            if(rs.next())
                return (new RDV(rs.getInt("IDRDV"),rs.getInt("IDonneur"),rs.getString("typededons"),rs.getString("grpsn"),rs.getDate("datedon").toLocalDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
