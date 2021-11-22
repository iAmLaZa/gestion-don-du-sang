package interfaces;

import classes.Evenement;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IEVN {
    public boolean newEVNT() throws SQLException;
    public ObservableList<Evenement> listeEvenements() throws SQLException;
}
