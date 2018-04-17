package uk.org.eduserv.status.types;

public class ServiceTO {

    private String status;
    private String name;
    private String created_at;
    private String updated_at;
    private int position;
    private String description;
    private boolean showcase;
    private String id;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowcase() {
        return showcase;
    }

    public void setShowcase(boolean showcase) {
        this.showcase = showcase;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    private String page_id;
    private String group_id;


    @Override
    public String toString() {
        return "ServiceTO{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", showcase=" + showcase +
                ", id='" + id + '\'' +
                ", page_id='" + page_id + '\'' +
                ", group_id='" + group_id + '\'' +
                '}';
    }


    /*
    private String id;
    private String status;
    private String name;
    private int position;
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServicesTO{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", description='" + description + '\'' +
                '}';
    }
    */





}
