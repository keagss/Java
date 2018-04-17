package Athens.com.Tests;

import com.mashape.unirest.request.body.MultipartBody;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class addStatus {
    public static void main(String[] args) throws Exception {

        okhttp3.RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
                //Data that is added to maint
                .addFormDataPart("incident[name]", "Test").addFormDataPart("incident[status]", "scheduled")
                .addFormDataPart("incident[scheduled_for]", "2019-01-15T07:00:00Z")
                .addFormDataPart("incident[scheduled_until]", "2019-01-15T09:00:00Z").addFormDataPart("incident[body]", "Test")
                .addFormDataPart("incident[component_ids][]", "17m9f2pp54jd").addFormDataPart("incident[deliver_notifications]", "false")
                .build();

        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();





        OkHttpClient httpClient = new OkHttpClient();

        try{
            /*
            HttpPost request = new HttpPost("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents.json");
            //request.addHeader("content-type", "application/json");
            request.addHeader("Authorization" , "OAuth 6bbc6f30-0a42-446f-80ef-1b29324a15c0");
            */

            Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents.json")
                    .addHeader("Authorization", "OAuth 6bbc6f30-0a42-446f-80ef-1b29324a15c0").post(body).build();



            StringEntity params = new StringEntity(body.toString());

            /*
            request.setEntity(params);
            httpClient.execute(request);
            */




            //Response response = httpClient.newCall(request).execute();

            Response response = httpClient.newCall(request).execute();

            System.out.println(response.body().string());

            //httpClient.newCall(request).execute();


        } catch (Exception e) {
            // handle exception here
            e.printStackTrace();
            throw e;
        } finally {


        }


    }
}
