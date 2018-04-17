package uk.org.eduserv.status.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import uk.org.eduserv.status.types.ServiceTO;
import uk.org.eduserv.status.ui.ListTemplates;

@Singleton
public class ListServlet extends HttpServlet {

    private static final long serialVersionUID = -4153617168324478091L;
    private final ListTemplates templates;

    @Inject
    public ListServlet(ListTemplates templates) {
        this.templates = templates;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.statuspage.io/v1/pages/v4xyzf5fwkky/components.json")
                .addHeader("Authorization", "REMOVED").get().build();
        Response response = client.newCall(request).execute();


        try{
            ObjectMapper mapper = new ObjectMapper();

            //Map response to getters/setters
            ServiceTO[] service = mapper.readValue(response.body().string(), ServiceTO[].class);
            List<ServiceTO> services = new ArrayList<>(Arrays.asList(service));
            HashMap<String,String> ServicesMap = new HashMap<>();


            //iterates through list and add the component ID and service name to a hashmap
            for(int i=0; i<services.size(); i++) {
                ServicesMap.put(services.get(i).getId().toString(), services.get(i).getName().toString());
            }

            System.out.println("services :" + services);
            templates.renderMapList(ServicesMap,resp.getWriter());

            resp.setStatus(200);

        }catch (JSONException jex){
            jex.printStackTrace();
        }

    }


}
