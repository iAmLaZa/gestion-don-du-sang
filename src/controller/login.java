package controller;

import classes.DonneurRecepteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import manipulation.outils;
import sample.BD;
import java.io.IOException;
import java.sql.*;

public class login {
    private static Connection con = BD.connect();
    private static Statement statement;
    private static ResultSet rs = null;
    static {
        try {
            statement = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private Label error;
    @FXML
    private TextField usernametxt;
    @FXML
    private PasswordField passwordtxt;






    int count=0;
    @FXML
    Button btnlogin;
    public void adddonneur(ActionEvent actionEvent) throws IOException {
        outils.loadw(actionEvent,"Add Donneur","/sample/newdonneur.fxml");
    }

    public void getlogin(ActionEvent actionEvent) throws SQLException, IOException {
        error.setText("");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");

        String username = usernametxt.getText();
        String password = passwordtxt.getText();


        String sql = "select * from user";

        rs = statement.executeQuery(sql);


        while (rs.next()) {
            if (!rs.getString("email").equals(username)) {
                error.setText("user n'existe pas");
            } else {
                sql = "select * from user where email='"+username+"'";

                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    if (!rs.getString("password").equals(password)) {
                        count++;
                        if(count==3){

                            btnlogin.setDisable(true);
                            error.setText("redémarrer application");
                            usernametxt.setText("");

                        }
                            error.setText("password incorret");
                            passwordtxt.setText("");

                    } else {
                        if(rs.getString("typeuser").equals("admin")){
                            outils.loadp(actionEvent, "Accueil_Admin", "/sample/Admin.fxml");
                        }else{
                            DonneurRecepteur donneur=new DonneurRecepteur("","",rs.getInt("ID"));
                            donneur=donneur.selectdonneur(rs.getInt("ID"));
                            donneur.setEmail(username);
                            donneur.setMDP(password);
                            donneur.setID(rs.getInt("ID"));
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/Donneur.fxml"));
                            Parent root= loader.load();
                            Donneur d=loader.getController();
                            d.showinformation(donneur);
                            Scene scene=new Scene(root);
                            Stage stage=new Stage();
                            stage.setScene(scene);
                            stage.setTitle("Accueil_Donneur");
                            Stage s =(Stage) btnlogin.getScene().getWindow();
                            s.close();
                            stage.show();

                        }



                    }
                }
            }
        }
    }
}
