/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class UrlBuilding {
    static ArrayList<String> Encodedurls = new ArrayList<>();
    static ArrayList<String> urls = new ArrayList<>();
    public static void main(String[] args) throws UnsupportedEncodingException{

       // generated("gmail@.com","facebook.com","D:\\info.csv");

//        for(String url : urls){
//            String command = "cmd /c start chrome " + url;
//            try{
//                Runtime.getRuntime().exec(command);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }


    public static ArrayList<String> generateEncoded(String domain , String site) throws UnsupportedEncodingException{
        String absolute = "D:\\info.csv";
        String line;
        ArrayList<String> keyWord = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(absolute))){
            while ((line = br.readLine()) != null) {
                // Use split method to break the line into columns
                String[] columns = line.split(",");

                // Process columns
                keyWord.add(columns[0]);
                city.add(columns[1]);
                // Move to the next line after printing all columns
            }
            System.out.println(keyWord);
            System.out.println(city);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int start =0;
        for(int i=0;i< keyWord.size();i++){
            String url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+site+"+site;
            String encoded = URLEncoder.encode(url1,"UTF-8");

            encoded = "https://www.google.com/search?q="+encoded+"&sca_esv=9267af3241730e66&ei=0YusZu2KMeu94-EP2ci14AE"+"&start="+start+"&sourceid=chrome&ie=UTF-8";
            Encodedurls.add(encoded);
            System.out.println(encoded);
            //  urls.add(url1);
        }

        return Encodedurls;

    }

   

    public ArrayList<String> generatedWithoutCsvGoogle(String topChooser ,String domain, String site, String key, String loc) {
        ArrayList<String> keyWord = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();
        
                // Process columns
                keyWord.add(key);
                city.add(loc);
                // Move to the next line after printing all columns
            
            System.out.println(keyWord);
            System.out.println(city);
        


        for(int i=0;i< keyWord.size();i++){
            int start=0 ;
            String url1="";
            if(topChooser.equals("Phone")){
                domain=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else if(topChooser.equals("Email")){
                //domain+=" emails ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else{
                domain+=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            String encoded;   // = URLEncoder.encode(url1,"UTF-8");
            for(;start<=180;start+=10){
                encoded = "https://www.google.com/search?q="+url1+"&start="+start;
                urls.add(encoded); 
                System.out.println(encoded);
            }

            //  urls.add(url1);
        }

        return urls;
 // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 public ArrayList<String> generatedWithoutCsvBing(String topChooser ,String domain, String site, String key, String loc) {
        ArrayList<String> keyWord = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();
        
                // Process columns
                keyWord.add(key);
                city.add(loc);
                // Move to the next line after printing all columns
            
            System.out.println(keyWord);
            System.out.println(city);
        


        for(int i=0;i< keyWord.size();i++){
            int start=1 ;
            String url1="";
            if(topChooser.equals("Phone")){
                domain=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else if(topChooser.equals("Email")){
                //domain+=" emails ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else{
                domain+=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            String encoded;   // = URLEncoder.encode(url1,"UTF-8");
            for(;start<=91;start+=10){
                encoded = "https://www.bing.com/search?q="+url1+"&first="+start;
                urls.add(encoded);
                System.out.println(encoded);
            }

            //  urls.add(url1);
        }

        return urls;
 // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
 public ArrayList<String> generatedWithoutCsvYahoo(String topChooser ,String domain, String site, String key, String loc) {
        ArrayList<String> keyWord = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();
        
                // Process columns
                keyWord.add(key);
                city.add(loc);
                // Move to the next line after printing all columns
            
            System.out.println(keyWord);
            System.out.println(city);
        


        for(int i=0;i< keyWord.size();i++){
            int start=1 ;
            String url1="";
            if(topChooser.equals("Phone")){
                domain=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else if(topChooser.equals("Email")){
                //domain+=" emails ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            else{
                domain+=" phone or mobile or contact ";
                url1 =keyWord.get(i)+"+"+city.get(i)+"+"+domain+"+"+"site:"+site;
            }
            String encoded;   // = URLEncoder.encode(url1,"UTF-8");
            for(;start<=91;start+=10){
                encoded = "https://search.yahoo.com/search?p="+url1+"&b="+start;
                urls.add(encoded);
                System.out.println(encoded);
            }

            //  urls.add(url1);
        }

        return urls;
 // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
