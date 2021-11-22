package interfaces;

import classes.RDV;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public interface IRDV {
    public boolean newRDV() throws SQLException;
    public boolean deleteRDV() throws SQLException;
    public ObservableList<RDV> listerdv(LocalDate date) throws SQLException;
    public ObservableList<RDV> listerdv_all() throws SQLException;
    public RDV rdv_donneur(int id);
}
