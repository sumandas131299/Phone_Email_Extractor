/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.extract;

import Utils.ProxyUtils;
import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Extract {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
        
        public void run(){
           App frame= new App();
           ImageIcon icon = new ImageIcon("C:\\Users\\User\\Documents\\NetBeansProjects\\Extract\\src\\main\\java\\images\\GT.png");
           frame.setIconImage(icon.getImage());
           frame.setVisible(true);
            
     //   ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//
//        // Schedule the task to run every 5 minutes (600 seconds)
       //executor.scheduleAtFixedRate(ProxyUtils::refreshProxies, 0, 90000, TimeUnit.MILLISECONDS);
        
        
        }
        });
        System.out.println("Hello World!");
    }
}
