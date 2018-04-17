package uk.org.eduserv.status.ui;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.template.soy.SoyFileSet.Builder;
import com.google.template.soy.data.SanitizedContent;
import com.google.template.soy.data.SanitizedContent.ContentKind;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.UnsafeSanitizedContentOrdainer;
import com.google.template.soy.tofu.SoyTofu;

import uk.org.eduserv.status.config.Configuration;

@Singleton
public class ListTemplates {

    private static final Logger LOG = LoggerFactory.getLogger(ListTemplates.class);

    private static final String NAMESPACE = "uk.org.eduserv.status";
    private final SoyTofu tofu;

    @Inject
    public ListTemplates(Builder sfsBuilder, Configuration config) throws Exception {
        LOG.debug("Configuring page templates: {}", config.getTemplates());

        try {
            System.out.println("template: "+config.getTemplates().get(0));
                this.tofu = sfsBuilder
                        .add(new File(ListTemplates.class.getClassLoader().getResource(config.getTemplates().get(1)).getFile())).build()
                        .compileToTofu();

        } catch (Exception e) {
            LOG.error("{}", e);
            throw e;
        }

    }

    //adds data to list name space for soy
    private void renderList(SoyMapData data, Appendable appendable) {
        LOG.debug("SoyMapData: {}", data);

        this.tofu.newRenderer(NAMESPACE + ".list").setData(data).render(appendable);
    }

    //creates soymapdata that contains hashmap
    public void renderMapList(HashMap Services, PrintWriter writer) {
        SoyMapData data = new SoyMapData("services", Services);

        renderList(data, writer);


    }





}