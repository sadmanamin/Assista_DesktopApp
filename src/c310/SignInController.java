package c310;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.derby.iapi.reference.ClassName;

public class SignInController {

    public static int UserID = 1;
    public static String Username = "";

    @FXML
    private Label changeLabel = new Label("");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button signUp;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    @FXML
    private Button signIn;

    @FXML
    private Button forgotPass;

    @FXML
    private Button fbLogin;
    
    @FXML
    void goToSignUp(ActionEvent event) throws IOException {
        Parent signIn = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signInScene = new Scene(signIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(signInScene);
        window.show();
        System.out.println("Done");
    }

    @FXML
    void goToForgotPass(ActionEvent event) throws IOException {
        Parent forget = FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));
        Scene forgetScene = new Scene(forget);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(forgetScene);
        window.show();
        System.out.println("Done");
    }

    @FXML
    void goToFBLogin(ActionEvent event) {
        System.out.println("hellu");
    }

    @FXML
    void signInChk(ActionEvent event) throws SQLException, IOException, Exception {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
        String query = "SELECT * FROM TEST.USERS WHERE USERNAME='" + username.getText() + "'";

        PreparedStatement pst = con.prepareStatement(query);
        System.out.println(query);
        //pst.setString(1, username.getText());
        //pst.setString(2, pass.getText());

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String passa = rs.getString(4);

            System.out.println(passa);
            if (Hash.check(pass.getText(), passa)) {
                UserID = Integer.parseInt(rs.getString(1));
                Username = rs.getString(2);
                query = "Insert into TEST.SESSION(USERID) values(" + UserID + ")";
                pst = con.prepareStatement(query);
                int rsa = pst.executeUpdate();
                //pst.setString(1, UserID);

                Thread.sleep(500);

                Parent dash;
                dash = FXMLLoader.load(getClass().getResource("dash.fxml"));
                Scene signInScene = new Scene(dash);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Assista");
                window.setScene(signInScene);
                window.show();
                System.out.println("Done");

            }
        } else {
            System.out.println("No match");
            changeLabel.setText("Username or pass didnt match");
        }
    }

    @FXML
    void initialize() {

    }
}
