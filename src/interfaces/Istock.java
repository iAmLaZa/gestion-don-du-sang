package interfaces;

import classes.stock;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface Istock {
    public ObservableList<stock> stock() throws SQLException;
}
