package com.example.tomz.electroniccity.data.model.api.about;

public class DataAboutUsResponse {

    private String id, title, description, image, tab, sort, publish;

    public DataAboutUsResponse(){

    }

    public DataAboutUsResponse(String id, String title, String description, String image,
                               String tab, String sort, String publish) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.tab = tab;
        this.sort = sort;
        this.publish = publish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
