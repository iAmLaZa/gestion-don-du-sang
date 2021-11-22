package interfaces;

import classes.DemmandeDeDon;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Idemande {
    public boolean adddemande_tran() throws SQLException;
    public boolean adddemande() throws SQLException;
    public boolean updatedemande() throws SQLException;
    public ObservableList<DemmandeDeDon> listedemande() throws SQLException;
}
