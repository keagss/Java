package uk.org.eduserv.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ContextListener extends GuiceServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

    @Override
    protected Injector getInjector() {
        LOG.debug("Creating injector");

        return Guice.createInjector(new StatusServletModule());
    }
}