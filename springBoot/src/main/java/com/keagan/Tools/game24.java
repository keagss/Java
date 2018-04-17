package com.keagan.Tools;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kbc on 20/09/2017.
 */
public class game24 {
    public static void main(String[] a) throws JSONException {
        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL("https://api.github.com/users/Pratik151/repos");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");


            //connection.addRequestProperty("Authorization", "Basic " + baseAuthStr);



            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            BufferedReader br = new BufferedReader(in);
            if(br!=null){
                int  cp;
                while((cp=br.read())!=-1){

                    sb.append((char) cp);
                }
                br.close();
            }
            sb.setCharAt(0,'[');
            sb.setCharAt(sb.length() - 1, ']');

            try {

                JSONArray jobj = new JSONArray(sb.toString());
                System.out.println(jobj);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
