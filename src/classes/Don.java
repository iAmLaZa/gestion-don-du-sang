package classes;

import interfaces.IDON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Don implements IDON {
    private static Connection con = BD.connect();
    int IDdonnateur,ID;
    String hospital,groupeSanguin,infermiereMedcin,typeDon;
    boolean passé,pasDeSacDeSang;
    LocalDate dateDeCollect,dateDexpiration;
    String maldadie;
    boolean instock;

    public Don(int IDdonnateur, int ID, String hospital, String groupeSanguin, String infermiereMedcin, String typeDon, boolean passé, boolean pasDeSacDeSang, LocalDate dateDeCollect, LocalDate dateDexpiration, String maldadie, boolean instock) {
        this.IDdonnateur = IDdonnateur;
        this.ID = ID;
        this.hospital = hospital;
        this.groupeSanguin = groupeSanguin;
        this.infermiereMedcin = infermiereMedcin;
        this.typeDon = typeDon;
        this.passé = passé;
        this.pasDeSacDeSang = pasDeSacDeSang;
        this.dateDeCollect = dateDeCollect;
        this.dateDexpiration = dateDexpiration;
        this.maldadie = maldadie;
        this.instock = instock;
    }



    public Don(int IDdonnateur, int ID, String hospital, String groupeSanguin, String infermiereMedcin, String typeDon, LocalDate dateDeCollect, String maldadie) {
        this.IDdonnateur = IDdonnateur;
        this.ID = ID;
        this.hospital = hospital;
        this.groupeSanguin = groupeSanguin;
        this.infermiereMedcin = infermiereMedcin;
        this.typeDon = typeDon;
        this.dateDeCollect = dateDeCollect;
        this.maldadie = maldadie;
    }

    public Don() {
    }

    public String getMaldadie() {
        return maldadie;
    }

    public void setMaldadie(String maldadie) {
        this.maldadie = maldadie;
    }

    public boolean isInstock() {
        return instock;
    }

    public void setInstock(boolean instock) {
        this.instock = instock;
    }

    public int getIDdonnateur() {
        return IDdonnateur;
    }

    public void setIDdonnateur(int IDdonnateur) {
        this.IDdonnateur = IDdonnateur;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getDateDexpiration() {
        return dateDexpiration;
    }

    public void setDateDexpiration(LocalDate dateDexpiration) {
        this.dateDexpiration = dateDexpiration;
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

    public String getInfermiereMedcin() {
        return infermiereMedcin;
    }

    public void setInfermiereMedcin(String infermiereMedcin) {
        this.infermiereMedcin = infermiereMedcin;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public boolean isPassé() {
        return passé;
    }

    public void setPassé(boolean passé) {
        this.passé = passé;
    }

    public boolean isPasDeSacDeSang() {
        return pasDeSacDeSang;
    }

    public void setPasDeSacDeSang(boolean pasDeSacDeSang) {
        this.pasDeSacDeSang = pasDeSacDeSang;
    }

    public LocalDate getDateDeCollect() {
        return dateDeCollect;
    }

    public void setDateDeCollect(LocalDate dateDeCollect) {
        this.dateDeCollect = dateDeCollect;
    }

    @Override
    public boolean newDon() throws SQLException {
        PreparedStatement pr=con.prepareStatement("insert into don(IDdonnateur,hospital,groupeSanguin,infermierMedcin" +
                ",typeDon,passe,pasDeSacDeSang,dateDeCollect,dateDexpiration,maladies,instock) values('"
                +getIDdonnateur()+"','"+getHospital()+"','"+getGroupeSanguin()+"','"+getInfermiereMedcin()+"','"+
                getTypeDon()+"',"+isPassé()+","+isPasDeSacDeSang()+",'"+getDateDeCollect()+"','"+getDateDexpiration()+"','"+getMaldadie()+"',"+isInstock()+")");
        return pr.execute();
    }

    @Override
    public ObservableList<Don> historique() throws SQLException {
        ObservableList<Don> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from don ");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new Don(rs.getInt("IDdonnateur"),rs.getInt("IDDon"),rs.getString("hospital"),rs.getString("groupeSanguin"),rs.getString("infermierMedcin"),rs.getString("typeDon"), rs.getDate("dateDeCollect").toLocalDate(),rs.getString("maladies")));
        return liste;
    }

    @Override
    public ObservableList<Don> historique_Donneur(int id) throws SQLException {
        ObservableList<Don> liste= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select * from don where IDdonnateur="+id);
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            liste.add(new Don(rs.getInt("IDdonnateur"),rs.getInt("IDDon"),rs.getString("hospital"),rs.getString("groupeSanguin"),rs.getString("infermierMedcin"),rs.getString("typeDon"), rs.getDate("dateDeCollect").toLocalDate(),rs.getString("maladies")));
        return liste;
    }
}
