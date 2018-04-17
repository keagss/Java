package uk.org.eduserv.status.resources;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/postupdateincident")
@Singleton
public class IncUpdatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws SecurityException,IOException {
        //calls the PUT api request
        doPut(req, resp);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)throws SecurityException,IOException{


        String description = req.getParameter("description");
        String incidentId = req.getParameter("incidentid");
        String incidentStatus = req.getParameter("incidentstatus");
        String impact = req.getParameter("incimpact");

        System.out.println(description+ "id: " +incidentId+ "status: "+incidentStatus+ "impact: "+impact);


        //Data that is added to update incident
        okhttp3.RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)

                .addFormDataPart("incident[status]", incidentStatus)
                .addFormDataPart("incident[body]", description).addFormDataPart("incident[impact_override]",impact)
                .addFormDataPart("incident[component_ids][]", incidentId).addFormDataPart("incident[deliver_notifications]", "false").addFormDataPart("incident[wants_twitter_update]","f")
                .build();

        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents/"+incidentId+".json")
                .addHeader("Authorization", "REMOVED").put(body).build();


        httpClient.newCall(request).execute();
        resp.setStatus(200);
        resp.sendRedirect("/status");
    }


}
