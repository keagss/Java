package uk.org.eduserv.status.types;

import java.util.List;

public class IncidentUpdatesTO {

    String status;
    String body;
    String created_at;
    String wants_twitter_update;
    String twitter_updated_at;
    String updated_at;
    String display_at;
    List<AffectedCompTO> affected_components;
    String custom_tweet;
    String deliver_notifications;
    String tweet_id;
    String id;
    String incident_id;

    @Override
    public String toString() {
        return "IncidentUpdatesTO{" +
                "status='" + status + '\'' +
                ", body='" + body + '\'' +
                ", created_at='" + created_at + '\'' +
                ", wants_twitter_update='" + wants_twitter_update + '\'' +
                ", twitter_updated_at='" + twitter_updated_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", display_at='" + display_at + '\'' +
                ", affected_components=" + affected_components +
                ", custom_tweet='" + custom_tweet + '\'' +
                ", deliver_notifications='" + deliver_notifications + '\'' +
                ", tweet_id='" + tweet_id + '\'' +
                ", id='" + id + '\'' +
                ", incident_id='" + incident_id + '\'' +
                '}';
    }





    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getWants_twitter_update() {
        return wants_twitter_update;
    }

    public void setWants_twitter_update(String wants_twitter_update) {
        this.wants_twitter_update = wants_twitter_update;
    }

    public String getTwitter_updated_at() {
        return twitter_updated_at;
    }

    public void setTwitter_updated_at(String twitter_updated_at) {
        this.twitter_updated_at = twitter_updated_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDisplay_at() {
        return display_at;
    }

    public void setDisplay_at(String display_at) {
        this.display_at = display_at;
    }

    public List<AffectedCompTO> getAffected_components() {
        return affected_components;
    }

    public void setAffected_components(List<AffectedCompTO> affected_components) {
        this.affected_components = affected_components;
    }

    public String getCustom_tweet() {
        return custom_tweet;
    }

    public void setCustom_tweet(String custom_tweet) {
        this.custom_tweet = custom_tweet;
    }

    public String getDeliver_notifications() {
        return deliver_notifications;
    }

    public void setDeliver_notifications(String deliver_notifications) {
        this.deliver_notifications = deliver_notifications;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(String incident_id) {
        this.incident_id = incident_id;
    }









    /*
    public Incident_UpdatesTO(String status, String body, String created_at, String wants_twitter_update, String twitter_updated_at, String updated_at, String display_at, String affected_components, String custom_tweet, String deliver_notifications, String tweet_id, String id, String incident_id) {
        this.status = status;
        this.body = body;
        this.created_at = created_at;
        this.wants_twitter_update = wants_twitter_update;
        this.twitter_updated_at = twitter_updated_at;
        this.updated_at = updated_at;
        this.display_at = display_at;
        this.affected_components = affected_components;
        this.custom_tweet = custom_tweet;
        this.deliver_notifications = deliver_notifications;
        this.tweet_id = tweet_id;
        this.id = id;
        this.incident_id = incident_id;
    }
    */


}
