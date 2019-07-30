/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdatePassController{

    
    @FXML
    private Label lblStatus;

    @FXML
    private TextField txNewPass;

    @FXML
    void update(ActionEvent event) throws SQLException, Exception {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
        String query = "SELECT * FROM TEST.PASSTABLE WHERE USERNAME='" + ForgetController.usernameS + "'+-";
        String pass = txNewPass.getText();

        PreparedStatement pst = con.prepareStatement(query);
        System.out.println(query);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            if (Hash.check(rs.getString(3), pass)) {

                query = "Update TEST.PASSTABLE set PASSWORD='" + Hash.getSaltedHash(pass) + "' WHERE USERNAME='" + ForgetController.usernameS + "'";
                pst = con.prepareStatement(query);
                int rsa = pst.executeUpdate();
                //pst.setString(1, UserID);

                Thread.sleep(500);
                if (rsa != 0) {
                    Parent dash;
                    dash = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                    Scene signInScene = new Scene(dash);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Assista");
                    window.setScene(signInScene);
                    window.show();
                    System.out.println("Done");
                }

            }
        } else {
            System.out.println("No match");
            
        }
    }

}
