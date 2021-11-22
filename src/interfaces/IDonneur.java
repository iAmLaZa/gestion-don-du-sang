package interfaces;

import classes.DonneurRecepteur;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IDonneur {
    public DonneurRecepteur selectdonneur(int ID) throws SQLException;
    public boolean newdonneur() throws SQLException;
    public boolean update() throws SQLException;
    ObservableList<String> codeDonneur() throws SQLException;
    public String grpsan(int ID) throws SQLException;
}
