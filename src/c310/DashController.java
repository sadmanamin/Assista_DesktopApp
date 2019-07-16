/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.stage.Stage;

public class DashController {
   

    public static Stage window;

    @FXML
    private ResourceBundle resources;
    
    
    @FXML
    private Button logOutBtn;

    @FXML
    private URL location;

    @FXML
    private Button wallChng;

    @FXML
    private Button taskManager;

    @FXML
    private Button quickAccess;

    @FXML
    void goToSignIn(ActionEvent event) throws SQLException, IOException, InterruptedException {
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
        String query = "DELETE FROM TEST.SESSION where USERID="+SignInController.UserID;
        PreparedStatement pst = con.prepareStatement(query);
        int rs = pst.executeUpdate();
        
        Thread.sleep(500);
        
        Parent signIn = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene signInScene = new Scene(signIn);
        Stage windows = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windows.setTitle("Assista");
        windows.setScene(signInScene);
        windows.show();
        System.out.println("Logout Done");
    }

    @FXML
    void goToQuickAccess(ActionEvent event) {

    }

    @FXML
    void goToTaskScreen(ActionEvent event) throws IOException {
        Parent task = FXMLLoader.load(getClass().getResource("TaskManager.fxml"));
        Scene wallPScene = new Scene(task);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(wallPScene);
        //Wallpaper.curStage = window;
        window.show();
    }

    @FXML
    void goToWallpaperScreen(ActionEvent event) throws IOException {

        Parent wallP = FXMLLoader.load(getClass().getResource("wallpaper.fxml"));
        Scene wallPScene = new Scene(wallP);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(wallPScene);
        //Wallpaper.curStage = window;
        window.show();
    }

    @FXML
    void initialize() {

    }
}
