package uk.org.eduserv.status.types;

import java.util.List;

public class IncidentsTO {



    String name;
    String status;
    String created_at;
    String updated_at;
    String monitoring_at;
    String resolved_at;
    String impact;
    String shortlink;
    String postmortem_ignored;
    String postmortem_body;
    String postmortem_body_last_updated_at;
    String postmortem_published_at;
    String postmortem_notified_subscribers;
    String postmortem_notified_twitter;
    String backfilled;
    String scheduled_for;
    String scheduled_until;
    String scheduled_remind_prior;
    String scheduled_reminded_at;
    String impact_override;
    String scheduled_auto_in_progress;
    String scheduled_auto_completed;
    String id;
    String page_id;
    public List<IncidentUpdatesTO> incident_updates;


    @Override
    public String toString() {
        return "IncidentsTO{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", monitoring_at='" + monitoring_at + '\'' +
                ", resolved_at='" + resolved_at + '\'' +
                ", impact='" + impact + '\'' +
                ", shortlink='" + shortlink + '\'' +
                ", postmortem_ignored='" + postmortem_ignored + '\'' +
                ", postmortem_body='" + postmortem_body + '\'' +
                ", postmortem_body_last_updated_at='" + postmortem_body_last_updated_at + '\'' +
                ", postmortem_published_at='" + postmortem_published_at + '\'' +
                ", postmortem_notified_subscribers='" + postmortem_notified_subscribers + '\'' +
                ", postmortem_notified_twitter='" + postmortem_notified_twitter + '\'' +
                ", backfilled='" + backfilled + '\'' +
                ", scheduled_for='" + scheduled_for + '\'' +
                ", scheduled_until='" + scheduled_until + '\'' +
                ", scheduled_remind_prior='" + scheduled_remind_prior + '\'' +
                ", scheduled_reminded_at='" + scheduled_reminded_at + '\'' +
                ", impact_override='" + impact_override + '\'' +
                ", scheduled_auto_in_progress='" + scheduled_auto_in_progress + '\'' +
                ", scheduled_auto_completed='" + scheduled_auto_completed + '\'' +
                ", id='" + id + '\'' +
                ", page_id='" + page_id + '\'' +
                ", incident_updates=" + incident_updates +
                '}';
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getMonitoring_at() {
        return monitoring_at;
    }

    public void setMonitoring_at(String monitoring_at) {
        this.monitoring_at = monitoring_at;
    }

    public String getResolved_at() {
        return resolved_at;
    }

    public void setResolved_at(String resolved_at) {
        this.resolved_at = resolved_at;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getShortlink() {
        return shortlink;
    }

    public void setShortlink(String shortlink) {
        this.shortlink = shortlink;
    }

    public String getPostmortem_ignored() {
        return postmortem_ignored;
    }

    public void setPostmortem_ignored(String postmortem_ignored) {
        this.postmortem_ignored = postmortem_ignored;
    }

    public String getPostmortem_body() {
        return postmortem_body;
    }

    public void setPostmortem_body(String postmortem_body) {
        this.postmortem_body = postmortem_body;
    }

    public String getPostmortem_body_last_updated_at() {
        return postmortem_body_last_updated_at;
    }

    public void setPostmortem_body_last_updated_at(String postmortem_body_last_updated_at) {
        this.postmortem_body_last_updated_at = postmortem_body_last_updated_at;
    }

    public String getPostmortem_published_at() {
        return postmortem_published_at;
    }

    public void setPostmortem_published_at(String postmortem_published_at) {
        this.postmortem_published_at = postmortem_published_at;
    }

    public String getPostmortem_notified_subscribers() {
        return postmortem_notified_subscribers;
    }

    public void setPostmortem_notified_subscribers(String postmortem_notified_subscribers) {
        this.postmortem_notified_subscribers = postmortem_notified_subscribers;
    }

    public String getPostmortem_notified_twitter() {
        return postmortem_notified_twitter;
    }

    public void setPostmortem_notified_twitter(String postmortem_notified_twitter) {
        this.postmortem_notified_twitter = postmortem_notified_twitter;
    }

    public String getBackfilled() {
        return backfilled;
    }

    public void setBackfilled(String backfilled) {
        this.backfilled = backfilled;
    }

    public String getScheduled_for() {
        return scheduled_for;
    }

    public void setScheduled_for(String scheduled_for) {
        this.scheduled_for = scheduled_for;
    }

    public String getScheduled_until() {
        return scheduled_until;
    }

    public void setScheduled_until(String scheduled_until) {
        this.scheduled_until = scheduled_until;
    }

    public String getScheduled_remind_prior() {
        return scheduled_remind_prior;
    }

    public void setScheduled_remind_prior(String scheduled_remind_prior) {
        this.scheduled_remind_prior = scheduled_remind_prior;
    }

    public String getScheduled_reminded_at() {
        return scheduled_reminded_at;
    }

    public void setScheduled_reminded_at(String scheduled_reminded_at) {
        this.scheduled_reminded_at = scheduled_reminded_at;
    }

    public String getImpact_override() {
        return impact_override;
    }

    public void setImpact_override(String impact_override) {
        this.impact_override = impact_override;
    }

    public String getScheduled_auto_in_progress() {
        return scheduled_auto_in_progress;
    }

    public void setScheduled_auto_in_progress(String scheduled_auto_in_progress) {
        this.scheduled_auto_in_progress = scheduled_auto_in_progress;
    }

    public String getScheduled_auto_completed() {
        return scheduled_auto_completed;
    }

    public void setScheduled_auto_completed(String scheduled_auto_completed) {
        this.scheduled_auto_completed = scheduled_auto_completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public List<IncidentUpdatesTO> getIncident_updates() {
        return incident_updates;
    }

    public void setIncident_updates(List<IncidentUpdatesTO> incident_updates) {
        this.incident_updates = incident_updates;
    }














}
