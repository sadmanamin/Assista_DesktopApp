package c310;

import java.io.IOException;
import java.util.regex.*;  
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label changeLabel = new Label("");
    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private Button signIn;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button fbLogin;

    @FXML
    void goToFBLogin(ActionEvent event) {

    }

    @FXML
    void goToSignIn(ActionEvent event) throws IOException {

        Parent signIn = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene signInScene = new Scene(signIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(signInScene);
        window.show();
        System.out.println("Done");

    }

    @FXML
    void submitSignUp(ActionEvent event) throws SQLException, IOException, Exception {

        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
        
        if(pass.getText().length()<7){
            changeLabel.setText("Password must contain 7 or more letter");
        }

        else if (!checkPassword()) {
            System.out.println("Hellu from Pass");
            changeLabel.setText("Password doesnt match");
        }
        
        else if(!Pattern.matches(email.getText(), "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
            changeLabel.setText("Not a correct Email format");
        }
        
        else {
            if (checkEmail(email.getText(), username.getText())) {

                String query = "Insert into TEST.USERS(USERNAME,EMAIL,PASS) values(?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, username.getText());
                pst.setString(2, email.getText());
                pst.setString(3, Hash.getSaltedHash(pass.getText()));

                int rs = pst.executeUpdate();
                System.out.println(rs);
                if (rs != 0) {
                    changeLabel.setText("Registration done");
                    Thread.sleep(1500);
                    Parent signIn = FXMLLoader.load(getClass().getResource("dash.fxml"));
                    Scene signInScene = new Scene(signIn);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("Assista");
                    window.setScene(signInScene);
                    window.show();

                } else {
                    System.out.println("not done");
                    changeLabel.setText("Registration not done");
                }
            } else {
                System.out.println("Hellu from U or E");
                changeLabel.setText("Username or Email exists");
            }
        }
    }

    

    boolean checkEmail(String email, String uname) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
        String query = "SELECT * FROM TEST.USERS WHERE USERNAME='" + uname + "' or EMAIL='" + email + "'";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        return !rs.next();
    }

    boolean checkPassword() {
        return pass.getText().equals(confirmPass.getText());
    }

}
