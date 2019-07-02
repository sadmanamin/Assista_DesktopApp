/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;


import com.sun.jna.platform.win32.WinDef.UINT_PTR;// import JNA Platform and 5.3.2
import com.sun.jna.win32.*;
import java.util.HashMap;
import com.sun.jna.Library;
import com.sun.jna.Native;
 
public class WallpaperChanger {
 
     public interface SPI extends StdCallLibrary {
 
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
     }
 
 
 
 
    public static void change(String path) {
        //supply your own path instead of using this one
        //String path = "C:\\Users\\Sadman\\Desktop\\stmartin-0001.jpg";
 
        SPI.INSTANCE.SystemParametersInfo(
                new UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new UINT_PTR(0),
                path,
                new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }
 
 
}