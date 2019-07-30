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
    
    private int getPid(String[] s){
        for(int c=1;c<s.length;c++){
            if(s[c].length()>0) return toInt(s[c]);
        }
        return -1;
    }

    public void showList() {
        int c = 1;
        int mx1=0,mx2=0,pid=0;
        System.out.println("Entered ListProcess");
        try {
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            System.out.println(111111);
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line = input.readLine();
            line = input.readLine();
            line = input.readLine();

            //System.out.println(22222);
            while ((line = input.readLine()) != null) {
                
                //System.out.println(333);
                String[] listOftask = line.split(" ");
                String name = listOftask[0];
               if(name.equals("python.exe")){
                   SystemInfoTest.sz++;
                   System.out.println((c++)+" "+line);
               } //<-- Parse data here.
                
                //System.out.println(listOftask[1]);
                int mem = toInt(listOftask[listOftask.length - 2]);
                
                if(name.equals("python.exe") && mx1<mem){
                    mx2=mx1;
                    SystemInfoTest.pid2=SystemInfoTest.pid1;
                    SystemInfoTest.pid1 = getPid(listOftask);
                    
                    mx1=mem;
                }
                else if(name.equals("python.exe") && mx2<mem){
                    SystemInfoTest.pid2 = getPid(listOftask);
                    mx2=mem;
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        //return pid;
    }
    
    
}
