package Athens.com.Tests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;


/**
 * Created by kbc on 22/06/2017.
 */


public class addAccount {
    public static void main(String[] args) throws IOException {

        JSONObject json = new JSONObject();
        //json.put("attributes" ,atrritbutes);
        json.put("expiry", "2019-11-20T12:34:00Z");
        json.put("status", "pending");
        json.put("username", "athtestname1000");

        JSONArray atrritbutes = new JSONArray();
        atrritbutes.add("forenames: testagain");
        atrritbutes.add("surname: testagain");
        atrritbutes.add("emailAddress: andree300o0@example.org");


        //array.add(atrritbutes);


        json.put("attributes" ,atrritbutes);

        String message = json.toString();
        System.out.println(message);


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("https://admin.openathens.net/api/v1/athens/organisation/70166849/accounts/create/personal?sendEmail=true");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization" , "OAApiKey a93c7fad-e45a-4656-92c9-8a516d83b41b");
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
