/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author User
 */


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProxyUtils {

    private static List<String> validproxies = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        validproxies = validateProxies(fetchProxies());


        //To get the valid proxies use the following functions  //-->> validateProxies(fetchProxies()); //

        System.out.println(validproxies);
        System.out.println(validproxies.size());
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//
//        // Schedule the task to run every 5 minutes (600 seconds)
//        executor.scheduleAtFixedRate(ProxyUtil::refreshProxies, 0, 90000, TimeUnit.MILLISECONDS);
    }



    public static void refreshProxies() {
        try {
            
            List<String> fetchedProxies = fetchProxies();
            List<String> newValidProxies = validateProxies(fetchedProxies);

            // Synchronize access to the shared resource validProxies
            synchronized (validproxies) {
                validproxies = newValidProxies; // Replace the old list with the new valid proxies
            }

            // For testing, print the valid proxies and their count
            //System.out.println("New Proxies Fetched : " + validproxies);
            System.out.println("Validated Proxies: " + validproxies.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String PROXY_SCRAPE_URL = "https://api.proxyscrape.com/v3/free-proxy-list/get?request=displayproxies&proxy_format=ipport&format=text";


    //Fetching proxies from the API
    public static List<String> fetchProxies() throws IOException {
        List<String> proxyList = new ArrayList<>();

        // Create a URL object with the target URL
        URL url = new URL(PROXY_SCRAPE_URL);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // Set connection timeout to 5 seconds
        connection.setReadTimeout(5000); // Set read timeout to 5 seconds

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                proxyList.add(inputLine.trim());
                //System.out.println(inputLine.trim());
            }
           // System.out.println("New Fetched proxyList Size"+ proxyList.size());
        } catch (IOException e) {
            throw new IOException("Error fetching proxies: " + e.getMessage());
        } finally {
            connection.disconnect();
        }

        return proxyList;
    }

    // Validate proxies using a thread pool of 1000 threads
    public static List<String> validateProxies(List<String> proxies) {
        List<String> validProxies = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1000); // Adjust the thread pool size as needed

        try{
            for (String proxyAddress : proxies) {
            executorService.execute(() -> {
                if (isHttpsProxy(proxyAddress)) {
                   // System.out.println("Valid proxy address: " + proxyAddress);
                    synchronized (validProxies) {
                        validProxies.add(proxyAddress);
                    }
                }else{
                  //  System.out.println("InValid proxy address: " + proxyAddress);
                }
                
            });
        }
       // System.out.println("valid proxyList Size: " + validProxies.size());
        // Shutdown the executor service and wait for tasks to complete
        }catch(Exception e){
            System.out.println("ValidProxies : Exception: ");

        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: ");
           // e.printStackTrace();
        }
        System.out.println("ValidProxies:" +validProxies.size());
        return validProxies;
    }

    // Check if the provided proxy address is a valid HTTPS proxy by trying to establish a connection to a test URL
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

    public static List<String> getValidProxies() throws IOException {
        synchronized (validproxies) {
            if(validproxies.isEmpty()) validproxies = validateProxies(fetchProxies());
            return new ArrayList<>(validproxies); // Return a copy of the list to avoid exposing the internal structure
        }
    }
    
     public static List<String> getValidProxies(List proxies) throws IOException {
        synchronized (validproxies) {
            if(Objects.equals(proxies, new ArrayList<>(validproxies)))
             validproxies = validateProxies(fetchProxies());
            return new ArrayList<>(validproxies); // Return a copy of the list to avoid exposing the internal structure
        }
    }

}

