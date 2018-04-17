package com.keagan.Tools; /**
 * Created by kbc on 22/06/2017.
 */
import org.apache.http.message.BasicNameValuePair;
import org.omg.CORBA.NameValuePair;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;


public class DeleteAccountById {

    public static void main(String[] args) throws IOException {


        int id;

        JFrame frame = new JFrame();

        String stringId = JOptionPane.showInputDialog(frame, "Enter the id of the account you would like to delete");

        id = Integer.parseInt(stringId);
        URL url = new URL("http://localhost:8080/students/"+ id);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("DELETE");

        httpCon.setRequestProperty("User-Agent", USER_AGENT);
        httpCon.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpCon.setRequestProperty("Content-Type","application/json");





        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();









    }
}
