package c310;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgetController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Button backToDash;

    @FXML
    private URL location;

    @FXML
    private TextField teacher;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private Label lblStatus;
    
    public static String usernameS;
    
    @FXML
    void goToDash(ActionEvent event) throws IOException {
        Parent signIn = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene signInScene = new Scene(signIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(signInScene);
        window.show();
    }

    @FXML
    void UpdatePass(ActionEvent event) {
        try {

            //System.out.println(usern);
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
            String query = "SELECT * FROM TEST.USERS WHERE USERNAME='" + username.getText() + "'";
            usernameS=username.getText();

            PreparedStatement pst = con.prepareStatement(query);
            System.out.println(query);
            //pst.setString(1, username.getText());
            //pst.setString(2, pass.getText());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Parent signIn = FXMLLoader.load(getClass().getResource("UpdatePass.fxml"));
                Scene signInScene = new Scene(signIn);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Give new Password");
                window.setScene(signInScene);
                window.show();
            } else {
                lblStatus.setText("info doesn't match!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void initialize() {

    }
}
