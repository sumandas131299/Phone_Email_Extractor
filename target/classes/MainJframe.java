/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package images;

import static Utils.FinalEmailExtraction.emailExtract;
import static Utils.FinalPhoneExtraction.phoneExtract;
import Utils.UrlBuilding;

import Utils.MacAds;
import Utils.Scrap;
import Utils.phone;
import com.mycompany.extract.TextAreaOutputStreamSmtp;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author User
 */
public class MainJframe extends javax.swing.JFrame {
        
    static String path;
    ArrayList<String> urls = new ArrayList();
    static int UrlInitialSize;
    static int UrlEditSize;
    static Boolean StopBoolean =false;
    HashMap<String,String> validPhoneList=new HashMap();
    Set<String> validEmailList=new HashSet();
    static  UrlBuilding obj = new UrlBuilding();
    /**
     * Creates new form main
     */
    public MainJframe() {
        initComponents();
            taOutputStream = new TextAreaOutputStreamSmtp(jTextArea1);
        System.setOut(new PrintStream(taOutputStream));
       // System.setErr(new PrintStream(taOutputStream));
        System.out.println("Start program after");
       
         javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        jLabel7.setText("Url Processed : "+(UrlInitialSize - UrlEditSize ) + "   Urls Left : "+ UrlEditSize  );
            jLabel7.setFont(new Font("Sans Serif", Font.PLAIN, 16));
            jLabel7.setForeground(Color.BLACK);  // Set text color to white
           // jLabel6.setOpaque(true);             // Make the background color visible
        jLabel8.setText("Phone Number Count : "+(validPhoneList.size()));
            jLabel8.setFont(new Font("Sans Serif", Font.PLAIN, 16));
            jLabel8.setForeground(Color.BLACK);    
            
            jLabel9.setText("Email Count : "+(validEmailList.size()));
            jLabel9.setFont(new Font("Sans Serif", Font.PLAIN, 16));
            jLabel9.setForeground(Color.BLACK);    
            
            
              }
    });
    // Start the timer
    timer.start();

    
     javax.swing.Timer timer1 = new javax.swing.Timer(60000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshUi();
             }
    });
    // Start the timer
    timer1.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(620, 590));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Email Domain : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 28));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 540, 110));

        jLabel2.setText("Site :  ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 60, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "facebook.com", "instagram.com", "twitter.com", "Enter Custom Value" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 120, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "gmail.com", "yahoo.com", "outlook.com", "All", "Enter Custom Value" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 120, 30));

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 580, 140, 50));

        jLabel3.setText("Phone Numbers");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 120, 40));

        jLabel4.setText("Email Ids");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 60, 40));

        jLabel5.setText("KeyWord : ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fitness", "Gym", "Bike", "Cosmetics", "Enter Custom Value" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 120, 30));

        jLabel6.setText("Location :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kolkata", "Bangalore", "Hyderabad", "Enter Custom Value" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, 30));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextArea2CaretUpdate(evt);
            }
        });
        jScrollPane4.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 250, 250));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextArea3PropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 290, 250));

        jButton3.setText("Urls");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, 60));

        jButton4.setText("Export");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 160, 50));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 260, -1));

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));

        jButton5.setText("Stop");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, -1, 50));

        jButton6.setText("Clear Lists");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, 120, 20));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Email", "Phone", "Both" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 90, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   public  void startProcess() throws IOException{
           Scrap sc = new Scrap();
         String topChooser = (String) jComboBox6.getSelectedItem();
         Set<String> tempPhoneList=new HashSet();
         Set<String> tempEmail=new HashSet();
       
        
        int macLimit=0;
        String EMAIL_REGEX = "";
        String email = (String) jComboBox2.getSelectedItem();
          if(email.equals("All")){
                     EMAIL_REGEX   = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
               }else if(email.equals("gmail.com")){
                   EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@(gmail\\.com)";
               }else if(email.equals("yahoo.com")){
                   EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@(yahoo\\.com)";
               }else if(email.equals("outlook.com")){
                   EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@(outlook\\.com)";
               }
               else{
                   EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@("+email+"\\.com)";
               }              
        try {
            UrlEditSize=urls.size();
            int size = urls.size();
            for(int i=0;i<size;i++){
                String text="";
                if(StopBoolean){
                    StopBoolean=false;
                    break;
                }
                if(i==macLimit){
                    MacAds.ChangeMac();
                    Thread.sleep(20000);
                    macLimit+=10;
                }
                 try{
                     text = sc.Scrap(urls.get(i));
                     System.out.println(text);
                     
                     
                     if(topChooser.equals("Phone")){
                         tempPhoneList = phoneExtract(text); 
                     }else if(topChooser.equals("Email")){
                         tempEmail=emailExtract(text,EMAIL_REGEX) ;
                     }else{
                         tempPhoneList = phoneExtract(text); 
                         tempEmail=emailExtract(text,EMAIL_REGEX) ;
                     }
                       
                 }catch(Exception e){
                     jTextArea1.append(e.getMessage());
                     i--;
                     if(!tempPhoneList.isEmpty()) tempPhoneList.clear();
                     if(!tempEmail.isEmpty()) tempPhoneList.clear();
                    
                     continue;
                 }
           // System.out.println(tempPhoneList);
           HashMap<String,String> hm = new HashMap();
            if(!tempPhoneList.isEmpty()){
                hm=phone.Format(new ArrayList(tempPhoneList));
                validPhoneList.putAll(hm);
                for(String num : hm.keySet()) jTextArea2.append(num+"\n");
                tempPhoneList.clear();
            }
            if(!tempEmail.isEmpty()){
                validEmailList.addAll(tempEmail);
                for(String eml : tempEmail) jTextArea3.append(eml+"\n");
                tempEmail.clear();
            }
            //Thread.sleep(1000);
           if(text!=""){
               System.out.println((i+1) +" : No. Url finished"+"\n");
           UrlEditSize--;
           }
            
            
             if(StopBoolean){
                    StopBoolean=false;
                    break;
                }
            }
            
            // used rate limits in FinalEmailExtarction and FinalPhoneExtraction
            
        } catch (InterruptedException ex) {
            jTextArea1.append(ex.getMessage());
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
//        DefaultListModel<String> modelnum = new DefaultListModel();
//        DefaultListModel<String> modelemail = new DefaultListModel();
//                
//        if(!validPhoneList.isEmpty()){
//            for(String num : validPhoneList){
//                
//                modelnum.addElement(num);
//                       
//            }
//            jList1.setModel(modelnum);
//        }
//        
//        if(!validEmailList.isEmpty()){
//            for(String emails : validEmailList){
//                
//                modelemail.addElement(emails);
//                       
//            }
//            jList2.setModel(modelemail);
//        }
        System.out.println("All urls. done");
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //objects
    // MainJframe obj = new MainJframe();
            new Thread(() -> {
        try {
              System.out.println("Process Started Please Wait. \n");
            startProcess();
          
        } catch (Exception ex) {
            Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Finished All urls.");
            UrlEditSize=0;
        }).start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
         String selectedItem = (String) jComboBox2.getSelectedItem();
        if ("Enter Custom Value".equals(selectedItem)) {
            jComboBox2.setEditable(true);
            jComboBox2.setSelectedItem("");
            jComboBox2.revalidate();
            jComboBox2.repaint();
            jComboBox2.revalidate();
            jComboBox2.repaint();
 // Start editing immediately
        } else {
            jComboBox2.setEditable(false);
        }
        
 
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

           public String getJcomboBoxEmail(){
              String email = (String) jComboBox2.getSelectedItem();
              return email;
}
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        refreshUi();
        if(!urls.isEmpty()) urls.clear();
        
        String topChooser = (String) jComboBox6.getSelectedItem();
        String EmailDomain = (String) jComboBox2.getSelectedItem();
        String Site = (String) jComboBox1.getSelectedItem();
        jTextArea1.setText("");
        taOutputStream = new TextAreaOutputStreamSmtp(jTextArea1);
        System.setOut(new PrintStream(taOutputStream));
        jTextArea1.append(Site+EmailDomain+"\n");
        System.out.println("Going to fetch urls");
       
        if(path!=null){
            urls = obj.generatedWithoutCsvGoogle(topChooser , EmailDomain ,Site,(String) jComboBox3.getSelectedItem(),(String) jComboBox4.getSelectedItem());
        }
        else
            urls = obj.generatedWithoutCsvGoogle(topChooser , EmailDomain ,Site,(String) jComboBox3.getSelectedItem(),(String) jComboBox4.getSelectedItem());
       
        jTextArea1.append(urls.size() +"\n");
        UrlInitialSize = urls.size();
        UrlEditSize=urls.size();
        this.refreshUi();
        jTextArea1.repaint();;
        jTextArea1.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextArea2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextArea2CaretUpdate
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextArea2CaretUpdate

    private void jTextArea3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextArea3PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea3PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
         String selectedItem = (String) jComboBox1.getSelectedItem();
        if ("Enter Custom Value".equals(selectedItem)) {
            jComboBox1.setEditable(true);
            jComboBox1.setSelectedItem("");
            jComboBox1.revalidate();
            jComboBox1.repaint();
            jComboBox1.revalidate();
            jComboBox1.repaint();
 // Start editing immediately
        } else {
            jComboBox1.setEditable(false);
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
            String selectedItem = (String) jComboBox3.getSelectedItem();
        if ("Enter Custom Value".equals(selectedItem)) {
            jComboBox3.setEditable(true);
            jComboBox3.setSelectedItem("");
            jComboBox3.revalidate();
            jComboBox3.repaint();
            jComboBox3.revalidate();
            jComboBox3.repaint();
 // Start editing immediately
        } else {
            jComboBox3.setEditable(false);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        String selectedItem = (String) jComboBox4.getSelectedItem();
        if ("Enter Custom Value".equals(selectedItem)) {
            jComboBox4.setEditable(true);
            jComboBox4.setSelectedItem("");
            jComboBox4.revalidate();
            jComboBox4.repaint();
            jComboBox4.revalidate();
            jComboBox4.repaint();
 // Start editing immediately
        } else {
            jComboBox4.setEditable(false);
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private String randomInvoice() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
          String code = "";
            Random random = new Random();
            
             // Generates 10 digits
            code+= (char) ('a' + random.nextInt(26));
            code+= (char) ('a' + random.nextInt(26));
            code+= String.valueOf(1000 + random.nextInt(10));
            code+= (char) ('a' + random.nextInt(26));
            code+= (char) ('a' + random.nextInt(26));
            code+=String.valueOf(4000+ random.nextInt(10)) ;
//          code=String.valueOf(LocalTime.now());
//          code=code.substring(0, 8).replaceAll(":", " ");
            System.out.println("Saved FileName : "+code);

            return code;
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
   
   String selectedItem = (String) jComboBox6.getSelectedItem();
    String downloadPath = System.getProperty("user.home") + "/Downloads/"+randomInvoice()+".csv";

    if ("Phone".equals(selectedItem)) {
        if (!validPhoneList.isEmpty()) {
            writeCsvFile(downloadPath, "CountryCode,PhoneNumber\n", validPhoneList);
        }
    } else if ("Email".equals(selectedItem)) {
        if (!validEmailList.isEmpty()) {
            writeCsvFileEmail(downloadPath, "Email\n", new ArrayList<>(validEmailList));
        }
    } else if ("Both".equals(selectedItem)) {
        if (!validPhoneList.isEmpty() && !validEmailList.isEmpty()) {
            writeCsvFileForBoth(downloadPath, validPhoneList, new ArrayList<>(validEmailList));
        }
    }

    JOptionPane.showMessageDialog(this, "File downloaded successfully to " + downloadPath);
   
    }//GEN-LAST:event_jButton4ActionPerformed

    private void writeCsvFile(String filePath, String header, HashMap<String, String> dataMap) {
    try (FileWriter writer = new FileWriter(filePath)) {
        writer.append(header);
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            writer.append(entry.getValue()).append(",").append(entry.getKey()).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error writing the file!");
    }
}

private void writeCsvFileEmail(String filePath, String header, List< String> dataMap) {
    try (FileWriter writer = new FileWriter(filePath)) {
        writer.append(header);
        for (String entry : dataMap) {
            writer.append(entry).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error writing the file!");
    }
}

private void writeCsvFileForBoth(String filePath, HashMap<String, String> phoneMap, List<String> emailList) {
    try (FileWriter writer = new FileWriter(filePath)) {
        writer.append("CountryCode,PhoneNumber,Email\n");
        int maxSize = Math.max(phoneMap.size(), emailList.size());
        List<String> phoneNumbers = new ArrayList<>(phoneMap.keySet());

        for (int i = 0; i < maxSize; i++) {
            String phoneNumber = i < phoneNumbers.size() ? phoneNumbers.get(i) : "";
            String email = i < emailList.size() ? emailList.get(i) : "";
            String code = phoneNumber.isEmpty() ? "" : phoneMap.get(phoneNumbers.get(i));
            writer.append(code).append(",").append(phoneNumber).append(",").append(email).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error writing the file!");
    }
}
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
            int response = JOptionPane.showConfirmDialog(
            this, 
            " Press Yess For 1 minute Pause \n press no For Stop",
            "Confirm", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
    );

    // Check the user's response
    if (response == JOptionPane.YES_OPTION) {
        // Code to execute if the user confirms the action
        System.out.println("Action Pausesd for a minute.");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainJframe.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    } else if(response==JOptionPane.NO_OPTION) {
        // Code to execute if the user cancels the action
        StopBoolean = true;
        jTextArea1.setText("");
         taOutputStream = new TextAreaOutputStreamSmtp(jTextArea1);
        System.setOut(new PrintStream(taOutputStream));
        jTextArea1.append("Action stopped.\n");
       // System.out.println("Action stopped.");
        
    }else{
         System.out.println("Action running.");
    }
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
          int response = JOptionPane.showConfirmDialog(
            this, 
            " Are you sure to clear all List \n  i:   Export data before Clearing ",
            "Confirm", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
    );

    // Check the user's response
    if (response == JOptionPane.YES_OPTION) {
        // Code to execute if the user confirms the action
       
        jTextArea2.setText("");
        jTextArea3.setText("");
         taOutputStream = new TextAreaOutputStreamSmtp(jTextArea1);
        System.setOut(new PrintStream(taOutputStream)); 
        System.out.println("Lists-Cleared.");
        validPhoneList.clear();
        validEmailList.clear();

    } else {
        // Code to execute if the user cancels the action
        
         System.out.println("Export Data if you need.");
        
    }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    public void refreshUi(){
        this.repaint();
        this.revalidate();
        jButton1.repaint();
        jButton3.repaint();
        jButton1.revalidate();
        jButton3.revalidate();
        System.out.println("Repainted");
    }
    
    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
        String topChooser = (String) jComboBox6.getSelectedItem();
        if(topChooser.equals("Phone")){
            jComboBox2.setEnabled(false);
        }else{
            jComboBox2.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJframe().setVisible(true);
                
            }
        });
    }
    
    TextAreaOutputStreamSmtp taOutputStream;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables


}
