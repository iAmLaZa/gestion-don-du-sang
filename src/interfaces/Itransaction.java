package interfaces;


import classes.Transaction;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Itransaction {
    public boolean newtrn() throws SQLException;
    public ObservableList<Transaction> listetransaction() throws SQLException;
}
