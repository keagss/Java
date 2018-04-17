package uk.org.eduserv.status.config;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

public class Configuration {




    public List<String> templates= new ArrayList<>();


    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }



    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("templates", templates).toString();
    }

}
