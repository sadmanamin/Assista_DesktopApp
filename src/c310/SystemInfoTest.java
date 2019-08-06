/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// https://github.com/oshi/oshi/issues/359 
package c310;

//import oshi.SystemInfo;
import java.util.logging.Logger;
import oshi.SystemInfo;
//import oshi.SystemInfo;
//import org.slf4j.LoggerFactory;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

/**
 * The Class SystemInfoTest.
 *
 * @author dblock[at]dblock[dot]org
 */
public class SystemInfoTest {

    /**
     * The main method, demonstrating use of classes.
     *
     * @param args the arguments
     */
    public static int pid1 = 0, pid2 = 0,sz=0;

    public void startChecking() throws InterruptedException{
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
        ListProcesses lp = new ListProcesses();
        //System.out.println(lp.showList());

        lp.showList();
        //System.out.println(cpuUsage());
    }

    public int getLargePid() throws InterruptedException {
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        
        int cnt = 0;
        CentralProcessor processor = si.getHardware().getProcessor();
        int cpuNumber = processor.getLogicalProcessorCount();
        OSProcess process1,process2;
        long cpu1 = -1, cpu2 = -1, previousTime1 = -1, previousTime2 = -1, timeDifference;

        boolean processExists = true;
        System.out.println("sz = "+sz+" pid1 "+pid1+" pid2 "+pid2);
        if(sz==1) return pid1;
        
        while (cnt++ != 2) {
            process1 = os.getProcess(pid1);
            process2 = os.getProcess(pid2);

            ////////////////////////////////////
            long currentTime1 = process1.getKernelTime() + process1.getUserTime();
            //System.out.println("CurrentTime2 = " + currentTime1);

            if (previousTime1 != -1) {
			// If we have both a previous and a current time
                // we can calculate the CPU usage
                timeDifference = currentTime1 - previousTime1;
                cpu1 = (long) ((100d * (timeDifference / ((double) 1000))) / cpuNumber);
                //System.out.println(cpu1);
            }

            previousTime1 = currentTime1;

            
            ///////////////////////////////////////////////////////////////////
            
            
            long currentTime2 = process2.getKernelTime() + process2.getUserTime();
            //System.out.println("CurrentTime2 = " + currentTime2);

            if (previousTime2 != -1) {
			// If we have both a previous and a current time
                // we can calculate the CPU usage
                timeDifference = currentTime2 - previousTime2;
                cpu2 = (long) ((100d * (timeDifference / ((double) 1000))) / cpuNumber);
                //System.out.println(cpu2);
            }

            previousTime2 = currentTime2;

            Thread.sleep(1000);
            /*} else {
             processExists = false;
             }*/
        }
        
        if(cpu1>cpu2) return pid1;
        else return pid2;
    }

    public boolean cpuUsage() throws InterruptedException {
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        System.out.println("Before getting largePid");
        int cnt = 0, pid=getLargePid();
        System.out.println("After getting largePid "+pid);
        CentralProcessor processor = si.getHardware().getProcessor();
        int cpuNumber = processor.getLogicalProcessorCount();
        OSProcess process;
        long cpu = -1, previousTime = -1, timeDifference;

        boolean processExists = true;
        
        while (true) {
            process = os.getProcess(pid);
            //if (process != null) {
            // CPU
            try{
            long currentTime = process.getKernelTime() + process.getUserTime();
            System.out.println("CurrentTime = " + currentTime);

            if (previousTime != -1) {
			// If we have both a previous and a current time
                // we can calculate the CPU usage
                timeDifference = currentTime - previousTime;
                cpu = (long) ((100d * (timeDifference / ((double) 1000))) / cpuNumber);
                System.out.println(cpu);
            }

            previousTime = currentTime;

            if (process == null) {
                return true;
            } else if (cpu == 0) {
                if (process == null) {
                    return true;
                } else {
                    return true;
                }
            }

            Thread.sleep(1000);
            }
            catch(Exception e){
                return false;
            }
            /*} else {
             processExists = false;
             }*/
        }

    }

}
