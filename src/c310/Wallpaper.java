/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;// import JNA Platform and 5.3.2
import com.sun.jna.win32.*;
import java.util.HashMap;
//import com.sun.jna.Library;
import static com.sun.jna.Library.OPTION_FUNCTION_MAPPER;
import static com.sun.jna.Library.OPTION_TYPE_MAPPER;
import com.sun.jna.Native;

public class Wallpaper {

    FileChooser fileChooser = new FileChooser();

    public static Stage curStage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button goBack;

    @FXML
    private ImageView selectedImage = new ImageView();

    @FXML
    private URL location;

    @FXML
    private Rectangle dragNdrop;

    @FXML
    private Button browse;

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
    void goToBrowse(ActionEvent event) {
        String path;
        try {
            File selectedFile = fileChooser.showOpenDialog(null);
            InputStream is = null;
            if (selectedFile == null) {
                // handle cancellation properly
            } else {
                Image image = new Image(selectedFile.toURI().toString());
                //System.out.println(path);
                selectedImage.setImage(image);
                
                path = selectedFile.getAbsolutePath();
                System.out.println(path);
                WallpaperChanger.change(path);
                
                
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Person", "test", "1234");
                String query = "Insert into TEST.WALLPAPER(USERID,IMAGE) values(?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, SignInController.UserID);
                is = new FileInputStream(selectedFile);
                pst.setBinaryStream(2, is);
                int rs = pst.executeUpdate();
                
                if(rs>0) System.out.println("Done Putting Image");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    

    @FXML
    void setOnDragDropped(DragEvent event) {

    }

    @FXML
    void setOnDragExit(DragEvent event) {

    }

    @FXML
    void setOnDragOver(DragEvent event) {

    }

    @FXML
    void initialize() {

    }
    
    /*public interface SPI extends StdCallLibrary {
 
         //from MSDN article
         long SPI_SETDESKWALLPAPER = 20;
         long SPIF_UPDATEINIFILE = 0x01;
         long SPIF_SENDWININICHANGE = 0x02;
         
         SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<String, Object>() { //String was changed from Object
             {
                 put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                 put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
             }
         });
 
         boolean SystemParametersInfo(
                 UINT_PTR uiAction,
                 UINT_PTR uiParam,
                 String pvParam,
                 UINT_PTR fWinIni
         );
     }*/
}
