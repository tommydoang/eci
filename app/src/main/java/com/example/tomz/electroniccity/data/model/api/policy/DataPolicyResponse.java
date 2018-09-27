package com.example.tomz.electroniccity.data.model.api.policy;

public class DataPolicyResponse {

    private String id_info, parent, id_info_type, id_info_position, link, title, description;

    public DataPolicyResponse(){

    }

    public DataPolicyResponse(String id_info, String parent, String id_info_type,
                              String id_info_position, String link, String title, String description) {
        this.id_info = id_info;
        this.parent = parent;
        this.id_info_type = id_info_type;
        this.id_info_position = id_info_position;
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public String getId_info() {
        return id_info;
    }

    public void setId_info(String id_info) {
        this.id_info = id_info;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getId_info_type() {
        return id_info_type;
    }

    public void setId_info_type(String id_info_type) {
        this.id_info_type = id_info_type;
    }

    public String getId_info_position() {
        return id_info_position;
    }

    public void setId_info_position(String id_info_position) {
        this.id_info_position = id_info_position;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
