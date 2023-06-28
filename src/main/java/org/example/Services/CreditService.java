package org.example.Services;

import org.example.Models.Credit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

interface CreditServiceInterface{
    String get(String url);
}

public class CreditService  implements CreditServiceInterface{

    private CreditService(){
        super();
    };
    private static class CreditServiceHelper{
        private static final CreditService instance = new CreditService();
    }

    public static CreditService getInstance(){
        return CreditServiceHelper.instance;
    }

    public String get(String urlString){
        StringBuilder content = new StringBuilder();
        // Use try and catch to avoid the exceptions
        try
        {
            URL url = new URL(urlString); // creating a url object
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

            // wrapping the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}

