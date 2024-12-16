/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author User
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

import java.io.IOException;
import java.util.Random;

public class MacAds {

    public static void main(String[] args) {
        ChangeMac();
    }

    public static void ChangeMac(){
        try {
           // Scanner sc = new Scanner(System.in);
            System.out.println("Changing New MacAddress");
            String code = "";
            Random random = new Random();
            
            code = String.valueOf(random.nextInt(10))+"A"; // Generates 10 digits
            code+=String.valueOf(10000+ random.nextInt(10)) ;
            code+=String.valueOf(50000+ random.nextInt(10)) ;
                        System.out.println("New MacAddress"+code);

            // Command to open Command Prompt as administrator and run the commands
            String command = "powershell.exe -Command \"Start-Process cmd -ArgumentList '/c, netsh interface set interface \"Wi-Fi\" admin=disable & timeout /t 5 /nobreak > nul & reg add \"HKEY_LOCAL_MACHINE\\SYSTEM\\CurrentControlSet\\Control\\Class\\{4d36e972-e325-11ce-bfc1-08002be10318}\\0016\" /v NetworkAddress /d "+code+" /f & timeout /t 5 /nobreak > nul & netsh interface set interface \"Wi-Fi\" admin=enable & echo Done! & pause' -Verb RunAs\"";

            // Execute the command
            executeCommand(command);

            System.out.println("MacAddress is successfully changed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
