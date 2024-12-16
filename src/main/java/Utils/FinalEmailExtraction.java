/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author User
 */

import images.MainJframe;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalEmailExtraction {
    
    public static void main(String[] args) throws IOException {
        Set<String> li = emailExtract("","");
    }

    public static Set<String> emailExtract(String text,String regex) throws IOException {
      // String url = "https://www.imf.org/external/np/exr/contacts/contacts.aspx";
       //  String url = "https://www.google.com/search?q=gym+kolkata+gmail.com+site+twitter.com&oq=fitness+pune+gmail.com";
       Set<String> validEmails = new HashSet<>();
        System.out.print("Going to fetch Email");
       
      try{
            String EMAIL_REGEX= regex;
                     
       Pattern pt = Pattern.compile(EMAIL_REGEX);
       Matcher mat = pt.matcher(text);
       while(mat.find()){
           validEmails.add(mat.group());
          // System.out.println(mat.group());
        }          
      }
       catch(Exception e){
           e.printStackTrace();
          // EmailExtraction(url);
       }
      System.out.println(validEmails);
       return (validEmails);
    }
}
