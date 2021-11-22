package classes;

import interfaces.IDonneur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonneurRecepteur extends User  implements IDonneur {
    private static Connection con = BD.connect();
    String nom,prenom,adress,numTel,genre,groupeSanguin,remarque;
    int age;

    public DonneurRecepteur(String email, String MDP, int ID, String nom, String prenom, String adress, String numTel, String genre, String groupeSanguin, String remarque, int age) {
        super(email, MDP, ID);
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.numTel = numTel;
        this.genre = genre;
        this.groupeSanguin = groupeSanguin;
        this.remarque = remarque;
        this.age = age;
    }

    public DonneurRecepteur(String email, String MDP, int ID) {
        super(email, MDP, ID);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public DonneurRecepteur selectdonneur(int ID) throws SQLException {
        PreparedStatement pr=con.prepareStatement("select * from donneur where ID="+ID);
        ResultSet rs=pr.executeQuery();
        if(rs.next()){
            DonneurRecepteur dn=new DonneurRecepteur("","",rs.getInt("ID"));
            dn.setNom(rs.getString("nom"));
            dn.setPrenom(rs.getString("prenom"));
            dn.setAdress(rs.getString("adresse"));
            dn.setNumTel(rs.getString("numtel"));
            dn.setGenre(rs.getString("genre"));
            dn.setGroupeSanguin(rs.getString("grpsn"));
            dn.setRemarque(rs.getString("remarque"));
            dn.setAge(rs.getInt("age"));
            return dn;

        }
        return null;
    }

    @Override
    public boolean newdonneur() throws SQLException {
        PreparedStatement pr=con.prepareStatement("insert into user(email,password,typeuser) values ('"+getEmail()+"','"+getMDP()+"','"+getRemarque()+"')");
        pr.execute();
         return pr.execute("insert into donneur values(last_insert_id(),'"+getNom()+"','"
                +getPrenom()+"','"+getAdress()+"','"+getNumTel()+"','"+getGenre()+"','"+getGroupeSanguin()+"','"+getRemarque()+"',"+getAge()+")");

    }

    @Override
    public boolean update() throws SQLException {
        PreparedStatement pr=con.prepareStatement("update user set email='"+getEmail()+"' where ID="+getID());
        pr.execute();
        return pr.execute("update donneur set nom='"+getNom()+"',prenom='"
                +getPrenom()+"',adresse='"+getAdress()+"',numtel='"+getNumTel()+"',genre='"+getGenre()+"',grpsn='"+getGroupeSanguin()+"',remarque='"+getRemarque()+"',age="+getAge()+" where ID="+getID());
    }

    @Override
    public ObservableList<String> codeDonneur() throws SQLException {
        ObservableList<String> list= FXCollections.observableArrayList();
        PreparedStatement pr=con.prepareStatement("select ID from donneur");
        ResultSet rs=pr.executeQuery();
        while(rs.next())
            list.add(rs.getString("ID"));
        return list;
    }

    @Override
    public String grpsan(int ID) throws SQLException {
        PreparedStatement pr=con.prepareStatement("select grpsn from donneur where ID="+ID);
        ResultSet rs=pr.executeQuery();
        if(rs.next()) return rs.getString("grpsn");
        return null;
    }
}
