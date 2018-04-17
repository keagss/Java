package com.keagan.Tools.getAccountById;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by kbc on 27/06/2017.
 */
public class saveAccount  {
    public static void saveAccount1(String id, String name, String course) throws IOException{


        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("course", course);

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
