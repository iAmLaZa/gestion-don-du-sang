package interfaces;

import classes.Don;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IDON {
    public boolean newDon() throws SQLException;
    public ObservableList<Don> historique() throws SQLException;
    public ObservableList<Don> historique_Donneur(int id) throws SQLException;
}
