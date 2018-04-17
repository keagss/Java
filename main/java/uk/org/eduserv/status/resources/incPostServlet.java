package uk.org.eduserv.status.resources;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/postincident")
@Singleton
public class incPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws SecurityException,IOException{

        String name = req.getParameter("servicename");
        String componentId = req.getParameter("componentid");
        String descBody = req.getParameter("description");


        okhttp3.RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
                //Data that is added to maint
                .addFormDataPart("incident[name]", name).addFormDataPart("incident[status]", "identified")
                .addFormDataPart("incident[body]", descBody)
                .addFormDataPart("incident[component_ids][]", componentId).addFormDataPart("incident[deliver_notifications]", "false")
                .build();

        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents.json")
                .addHeader("Authorization", "REMOVED").post(body).build();

        httpClient.newCall(request).execute();
        resp.setStatus(200);
        resp.sendRedirect("/");

    }

}
