package c310;

import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TaskManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField minutevalue;

    @FXML
    private TextField secondValue;

    @FXML
    private BottomNavigationButton sleepBtn;

    @FXML
    private BottomNavigationButton runningTasks;

    @FXML
    void callRunningTasks(ActionEvent event) {
        ListProcesses lists = new ListProcesses();
        lists.showList();
    }

    @FXML
    void callShutdwon(ActionEvent event) {
        
    }

    
}