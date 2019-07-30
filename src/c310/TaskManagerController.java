package c310;

import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
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

public class TaskManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button backToDash;

    @FXML
    private TextField minutevalue;
    
    

    @FXML
    private TextField secondValue;

    @FXML
    private Button shutdownBtn;

    @FXML
    private Button jupyterNotebook;
    
    @FXML
    void goToDash(ActionEvent event) throws IOException {
        Parent signIn = FXMLLoader.load(getClass().getResource("dash.fxml"));
        Scene signInScene = new Scene(signIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Assista");
        window.setScene(signInScene);
        window.show();
    }

    @FXML
    void callCancel(ActionEvent event) throws IOException {
        Runtime.getRuntime().exec("shutdown -a");
    }

    @FXML
    void callShutdown(ActionEvent event) throws IOException {
        shutDown_PC(Integer.parseInt(minutevalue.getText()), Integer.parseInt(secondValue.getText()));
    }

    @FXML
    void checkJupyter(ActionEvent event) throws InterruptedException, IOException {
        SystemInfoTest sys = new SystemInfoTest();
        
        try{
        sys.startChecking();
        }
        catch(Exception e){
            
        }
        System.out.println("Done Checking");
        if(sys.sz!=0){
            System.out.println("Inside Sys.sz");
            statusLabel.setText("PC will be turned off after Jupyter Notebook completes its task");
            if(sys.cpuUsage()){
                shutDown_PC(5, 30);
            }
        }
        
        else{
            statusLabel.setText("No python file running at Jupyter Notebook");
        }
        
    }

    void shutDown_PC(int min, int sec) throws IOException {
        int s = min * 60 + sec;
        Runtime.getRuntime().exec("shutdown -s -t " + s);
    }
}
