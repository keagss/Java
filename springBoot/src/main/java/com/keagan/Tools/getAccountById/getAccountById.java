package com.keagan.Tools.getAccountById;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by kbc on 26/06/2017.
 */
public class getAccountById {

    public ArrayList <String> array ()throws IOException{


        ArrayList<String> array = new ArrayList();



        StringBuilder result = new StringBuilder();

        JFrame frame = new JFrame();

        String stringId = JOptionPane.showInputDialog(frame, "Enter the account id");
        int id = Integer.parseInt(stringId);

        URL url = new URL("http://localhost:8080/students/"+id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);

        }
        rd.close();
        System.out.println(result.toString());

        for(int i=0;i<=2;i++) {

             String between=result.toString().replaceAll("[{}/(^\")|(\"$)/g]","").split(",")[i];

                between= between.split(":")[1];
                System.out.println(between);
                array.add(between);
        }
        /*

        String between = result.toString().split(",")[0];
        between= between.split(":")[1];
        System.out.println(between);

        */

        return array;

    }
}
