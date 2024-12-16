/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package com.mycompany.extract;


import static Utils.FinalEmailExtraction.emailExtract;
import static Utils.FinalPhoneExtraction.phoneExtract;
import Utils.MacAds;
import Utils.Scrap;
import Utils.UrlBuilding;
import Utils.phone;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class App extends javax.swing.JFrame {
     
    static String path;
    ArrayList<String> urls = new ArrayList();
    static int UrlInitialSize;
    static int UrlEditSize;
    static Boolean StopBoolean =false;
    HashMap<String,String> validPhoneList=new HashMap();
    Set<String> validEmailList=new HashSet();
    static  UrlBuilding obj = new UrlBuilding();
     TextAreaOutputStreamSmtp taOutputStream;

    /**
     * Creates new form App
     */
    public App() {
         initComponents();
         
         jButton1.setBackground(Color.WHITE);
         jButton3.setBackground(Color.WHITE);
         jButton4.setBackground(Color.WHITE);
         jButton5.setBackground(Color.WHITE);
         jButton6.setBackground(Color.WHITE);
         
         jTextArea4.setEditable(false);
         jTextArea5.setEditable(false);
         
         taOutputStream = new TextAreaOutputStreamSmtp(jTextArea1);
        System.setOut(new PrintStream(taOutputStream));
       // System.setErr(new PrintStream(taOutputStream));
        System.out.println("Start program after");
       
         javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        jLabel7.setText("Url Processed : "+(UrlInitialSize - UrlEditSize ) + "   Urls Left : "+ UrlEditSize  );
            jMenu4.setFont(new Font("Sans Serif", Font.BOLD, 18));
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

    public void refreshUi(){
        this.repaint();
        this.revalidate();
        jButton1.repaint();
        jButton3.repaint();
        jButton1.revalidate();
        jButton3.revalidate();
        System.out.println("Repainted");
    }
    
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
                     String test = "If you like, you can repeat the search with the omitted results included.";
                     
                     if(text.contains(test)){
                        System.out.println("Text macted and stopBoolean");
                         StopBoolean=true;
                     }
                     
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
                for(String num : hm.keySet()){
                    jTextArea4.append(num+"\n");
                    jTextArea4.setCaretPosition(jTextArea4.getDocument().getLength());
                }
                tempPhoneList.clear();
            }
            if(!tempEmail.isEmpty()){
                validEmailList.addAll(tempEmail);
                for(String eml : tempEmail) {
                    jTextArea5.append(eml+"\n");
                    jTextArea5.setCaretPosition(jTextArea5.getDocument().getLength());
                }
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
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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
      public String getJcomboBoxEmail(){
              String email = (String) jComboBox2.getSelectedItem();
              return email;
      }
      
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jDialog1 = new javax.swing.JDialog();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane(jTextArea4);
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane(jTextArea5);
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jMenu2.setText("File");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenuItem6.setText("jMenuItem6");

        jMenuItem7.setText("jMenuItem7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setMaximumSize(new java.awt.Dimension(690, 720));
        setMinimumSize(new java.awt.Dimension(690, 720));
        setPreferredSize(new java.awt.Dimension(690, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Email Domain : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 150, 28));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 620, 110));

        jLabel2.setText("Site :  ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 110, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "facebook.com", "instagram.com", "twitter.com", "indeed.com", "LinkedIn.com", "amazon.com", "justdial.com", "flipkart.com", "pinterest.com", "youtube.com", "googlemaps.com", "googlemybusiness.com", "Enter Custom Value" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 190, 30));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "gmail.com", "yahoo.com", "outlook.com", "All", "Enter Custom Value" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 190, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play.png"))); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, 50));

        jLabel3.setText("Phone Numbers");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 110, 40));

        jLabel4.setText("Email Ids");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 170, 40));

        jLabel5.setText("KeyWord : ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 110, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fitness", "Gym", "Bike", "Cosmetics", "Enter Custom Value" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 190, 30));

        jLabel6.setText("Location :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 100, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kolkata", "Bangalore", "Hyderabad", "Enter Custom Value" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/www.png"))); // NOI18N
        jButton3.setText("Urls");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/export.png"))); // NOI18N
        jButton4.setText("Export");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 120, 50));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 250, -1));

        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 240, -1));

        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 600, 190, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hand.png"))); // NOI18N
        jButton5.setText("Stop");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 120, 50));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/broom.png"))); // NOI18N
        jButton6.setText("Clear Lists");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 130, 50));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Email", "Phone", "Both" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 190, 30));

        jLabel10.setText("Select Type:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 110, -1));

        jLabel12.setText("Search Engine");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 100, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Google", "Bing", "Yahoo" }));
        getContentPane().add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 30));

        jTextArea4.setColumns(20);
        jScrollPane3.setViewportView(jTextArea4);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 300, 210));

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 310, 210));
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 680));

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jMenu1.setText("                                         ");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");
        jMenu1.add(jCheckBoxMenuItem1);

        menuBar.add(jMenu1);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GT.png"))); // NOI18N
        menuBar.add(jMenu5);

        jMenu4.setText("GT Extractor");
        menuBar.add(jMenu4);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //objects
        // MainJframe obj = new MainJframe();
        new Thread(() -> {
            try {
                System.out.println("Process Started Please Wait. \n");
                startProcess();

            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Finished All urls.");
            UrlEditSize=0;
        }).start();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        path=(String) jComboBox7.getSelectedItem();
        try {
            if(path.equals("Bing")){
                urls = obj.generatedWithoutCsvBing(topChooser , EmailDomain ,Site,(String) jComboBox3.getSelectedItem(),(String) jComboBox4.getSelectedItem());

            }else if(path.equals("Yahoo")){
            urls = obj.generatedWithoutCsvYahoo(topChooser , EmailDomain ,Site,(String) jComboBox3.getSelectedItem(),(String) jComboBox4.getSelectedItem());
}
            else
            urls = obj.generatedWithoutCsvGoogle(topChooser , EmailDomain ,Site,(String) jComboBox3.getSelectedItem(),(String) jComboBox4.getSelectedItem());
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTextArea1.append(urls.size() +"\n");
        UrlInitialSize = urls.size();
        UrlEditSize=urls.size();
        this.refreshUi();
        jTextArea1.repaint();;
        jTextArea1.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

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
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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

            jTextArea4.setText("");
            jTextArea5.setText("");
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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
