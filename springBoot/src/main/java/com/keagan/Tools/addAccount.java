package com.keagan.Tools;

import com.keagan.DAO.StudentDao;
import com.keagan.Entity.Student;
import com.keagan.Service.StudentService;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

import javax.swing.*;
import java.io.IOException;
import javax.swing.JButton;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.omg.CORBA.NameValuePair;

/**
 * Created by kbc on 22/06/2017.
 */


public class addAccount {
    public static void main(String[] args) throws IOException  {

        /*
        HashMap<Integer, Student> students;
        {
            students = new HashMap<Integer, Student>(){

                {
                    put(4, new Student(4, "Bill", "Computer Science"));

                }
            };
        }
        */
        //jsonString = new JSONObject()


        JSONObject json = new JSONObject();
                json.put("id", 5);
                json.put("name", "test");
                json.put("course", "maths");

        System.out.println(json);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://localhost:8080/students");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.close();
        }

    }
}
