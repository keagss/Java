package uk.org.eduserv.status.ui;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.eduserv.status.config.Configuration;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


@Singleton
public class StatusTemplates {

    private static final Logger LOG = LoggerFactory.getLogger(StatusTemplates.class);

    private static final String NAMESPACE = "uk.org.eduserv.status";
    private final SoyTofu tofu;

    @Inject
    public StatusTemplates(SoyFileSet.Builder sfsBuilder, Configuration config) throws Exception {
        LOG.debug("Configuring page templates: {}", config.getTemplates());

        try {
            System.out.println("template: "+config.getTemplates().get(1));
            this.tofu = sfsBuilder
                    .add(StatusTemplates.class.getClassLoader().getResource("status.soy")).build().compileToTofu();
        } catch (Exception e) {
            LOG.error("{Error parsing to status.soy}", e);
            throw e;
        }
    }


    private void renderInc(SoyMapData data, Appendable appendable) {
        LOG.debug("SoyMapData: {}", data);

        this.tofu.newRenderer(NAMESPACE + ".status").setData(data).render(appendable);
    }

    public void renderIncList(HashMap Incidents, PrintWriter writer) {
        SoyMapData data = new SoyMapData("incidents", Incidents);

        renderInc(data, writer);
    }
}
