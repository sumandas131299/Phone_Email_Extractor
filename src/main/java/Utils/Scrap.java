package Utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author User
 */

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrap {
    static List<String> proxies ;
  static{
      try{
    proxies = ProxyUtils.getValidProxies();
        }catch(IOException e){
                e.printStackTrace();
        }
    }
    static int proxyIndex=0;
    
    public static void main(String[] args) throws IOException {

    }
    
    public static String Scrap(String url) throws IOException {
        //  String url = "https://www.imf.org/external/np/exr/contacts/contacts.aspx";
       // String url = "https://www.google.com/search?q=fitness+pune+gmail.com+site+facebook.com";
       
        String text="";
        try {
            while(proxyIndex<proxies.size()){
                if(isHttpsProxy(proxies.get(proxyIndex))){
                 String[] parts = proxies.get(proxyIndex).split(":");
        if (parts.length != 2) {
           continue; // Invalid proxy format
        }

        String ip = parts[0];
        int port;

        try {
            port = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            continue; // Invalid port number
        }
        
            try{
                 System.out.println("Scraping : "+url);
                 Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
                  Document doc = Jsoup.connect(url).proxy(proxy).get();
            text = doc.wholeText();
            text+=doc.body().text();
            }
            catch(Exception e){
                proxies.remove(proxyIndex);
                e.printStackTrace();
                Thread.sleep(5000);
               // System.out.println("Working on same url in exception "+url);
                continue;
            }
                if(text!="")
                break;
                else
                continue;
            }
                else proxyIndex++;
            if(proxyIndex>=proxies.size()){
                System.out.println("No more proxies. Fetching new List inside Scrap");
                proxies= ProxyUtils.getValidProxies();
                proxyIndex=0;
            }
        
          }

        } catch (Exception e) {
            System.out.println("Exception");
           // PhoneExtraction(url);
        }
    return text;
    }
    
     public static boolean isHttpsProxy(String proxyAddress) {
        String[] parts = proxyAddress.split(":");
        if (parts.length != 2) {
            return false; // Invalid proxy format
        }

        String ip = parts[0];
        int port;

        try {
            port = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false; // Invalid port number
        }

        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            URL url = new URL("https://www.google.com"); // Test URL
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//            connection.setRequestMethod("GET");
//
//            int responseCode = connection.getResponseCode();
//            return responseCode == 200;
            Document doc = Jsoup.connect(url.toString())
                    .proxy(proxy)
                    .timeout(5000)
                    .get();
            return doc != null;


        } catch (IOException e) {
            return false; // If any error occurs, consider it as not supporting HTTPS
        }
    }

}