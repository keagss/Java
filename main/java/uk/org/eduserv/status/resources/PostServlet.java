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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/post")
@Singleton
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //pulling data from request to fill in the API call
        String name = req.getParameter("servicename");
        String starteDate = req.getParameter("startdate");
        String componentId = req.getParameter("componentid");
        String endDate = req.getParameter("enddate");
        String descBody = req.getParameter("description");

        //wont post correct time without this appended
        starteDate=starteDate+":00.000+01:00";
        endDate=endDate+":00.000+01:00";


        System.out.println("start :"+starteDate+" end:"+endDate);

        //Data that is added to maint
        okhttp3.RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
                .addFormDataPart("incident[name]", name).addFormDataPart("incident[status]", "scheduled")
                .addFormDataPart("incident[scheduled_for]", starteDate)
                .addFormDataPart("incident[scheduled_until]", endDate).addFormDataPart("incident[body]", descBody)
                .addFormDataPart("incident[component_ids][]", componentId).addFormDataPart("incident[deliver_notifications]", "false")
                .build();

        System.out.println("start :"+starteDate+" end:"+endDate);

        //api request
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents.json")
                .addHeader("Authorization", "REMOVED").post(body).build();

        httpClient.newCall(request).execute();
        resp.setStatus(200);
        resp.sendRedirect("/");

    }
}
