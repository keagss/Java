package uk.org.eduserv.status.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import uk.org.eduserv.status.types.AffectedCompTO;
import uk.org.eduserv.status.types.IncidentUpdatesTO;
import uk.org.eduserv.status.types.IncidentsTO;
import uk.org.eduserv.status.ui.ListTemplates;
import uk.org.eduserv.status.ui.StatusTemplates;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Singleton
public class IncidentsServlet extends HttpServlet {
    private static final long serialVersionUID = -4153617168324478091L;
    private final StatusTemplates templates;

    @Inject
    public IncidentsServlet(StatusTemplates templates) {
        this.templates = templates;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/incidents//unresolved.json")
                .addHeader("Authorization", "REMOVED").get().build();
        Response response = client.newCall(request).execute();

        try {
            ObjectMapper mapper = new ObjectMapper();
            IncidentsTO[] service = mapper.readValue(response.body().string(), IncidentsTO[].class);
            List<IncidentsTO> services = new ArrayList<>(Arrays.asList(service));
           // ArrayList<String> incidents = new ArrayList<>();
            //HashMap<String,String> ServicesMap = new HashMap<>();
            HashMap<String,ArrayList<String>> multiMapInc = new HashMap<>();



            for (int i = 0; i < services.size(); i++) {
                //incidents.add(services.get(i).getName());

                //ServicesMap.put(services.get(i).getId(), services.get(i).getName()+" "+services.get(i).getIncident_updates().get(0).getBody());
                ArrayList<String>incBody = new ArrayList<String>();
                incBody.add(services.get(i).getName());
                for(int j =0; j<services.get(i).incident_updates.size(); j++){

                    incBody.add(services.get(i).incident_updates.get(j).getBody());
                }

                multiMapInc.put(services.get(i).getId(),incBody);

            }


            System.out.println("incidents :" + multiMapInc.toString());
            templates.renderIncList(multiMapInc,resp.getWriter());
            resp.setStatus(200);

        }catch (JSONException jex){
            jex.printStackTrace();
        }

    }
}
