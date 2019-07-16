/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListProcesses {

    public int toInt(String s) {
        String tmp[] = s.split(",");
        s = "";
        for (int c = 0; c < tmp.length; c++) {
            s = s + tmp[c];
        }

        return Integer.parseInt(s);
    }

    public void showList() {
        int c = 1;
        try {
            String line;
            HashMap<String, Integer> taskList = new HashMap<String, Integer>();
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            line = input.readLine();
            line = input.readLine();
            line = input.readLine();
            
            while ((line = input.readLine()) != null) {
               System.out.println((c++)+" "+line); //<-- Parse data here.
                String[] listOftask = line.split(" ");
                //System.out.println(listOftask.length);
                String name = listOftask[0];
                int mem = toInt(listOftask[listOftask.length - 2]);
//                System.out.println(name + " " + mem);
                
                taskList.put(name, mem);
            }
            //System.out.println(taskList.get("System"));
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
