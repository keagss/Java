package uk.org.eduserv.status;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.google.template.soy.SoyModule;

import uk.org.eduserv.status.config.Configuration;
import uk.org.eduserv.status.resources.*;


public class StatusServletModule extends ServletModule {

    private static final String OPENATHENS_CONFIG = "openathens.config";

    private static final Logger LOG = LoggerFactory.getLogger(StatusServletModule.class);

    private final Configuration config;



    public StatusServletModule() {
        try {
            InputStream is;
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            if (System.getProperty(OPENATHENS_CONFIG) == null) {
                is = ServletModule.class.getClassLoader().getResourceAsStream("configuration.yml");
            } else {
                is = new FileInputStream(System.getProperty(OPENATHENS_CONFIG));
            }

            //this.config = mapper.readValue(is, Configuration.class);
            this.config = mapper.readValue(is,Configuration.class);
            LOG.debug("Loaded configuration: {}", config);
        } catch (IOException e) {
            LOG.error("Cannot load server configuration", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void configureServlets() {

        LOG.debug("Configuring soy templates");
        install(new SoyModule());

        LOG.debug("Configuring OpenAthens module");

        LOG.debug("Configuring servlets");
        serve("/").with(ListServlet.class);
        serve("/delete").with(DeleteServlet.class);
        serve("/post").with(PostServlet.class);
        serve("/postincident").with(incPostServlet.class);
        serve("/status").with(IncidentsServlet.class);
        serve("/postupdateincident").with(IncUpdatePostServlet.class);

    }

    @Provides
    public Configuration provideConfig() {
        return config;
    }
}