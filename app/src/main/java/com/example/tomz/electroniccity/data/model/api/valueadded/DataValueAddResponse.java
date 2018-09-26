package com.example.tomz.electroniccity.data.model.api.valueadded;

public class DataValueAddResponse {

    private String statusResponse, id_value, title_value, description_value,
            image_value, isPremium, isPublish, sortTo;

    public DataValueAddResponse(){}

    public DataValueAddResponse(String statusResponse, String id_value, String title_value,
                                String description_value, String image_value, String isPremium,
                                String isPublish, String sortTo) {
        this.statusResponse = statusResponse;
        this.id_value = id_value;
        this.title_value = title_value;
        this.description_value = description_value;
        this.image_value = image_value;
        this.isPremium = isPremium;
        this.isPublish = isPublish;
        this.sortTo = sortTo;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getId_value() {
        return id_value;
    }

    public void setId_value(String id_value) {
        this.id_value = id_value;
    }

    public String getTitle_value() {
        return title_value;
    }

    public void setTitle_value(String title_value) {
        this.title_value = title_value;
    }

    public String getDescription_value() {
        return description_value;
    }

    public void setDescription_value(String description_value) {
        this.description_value = description_value;
    }

    public String getImage_value() {
        return image_value;
    }

    public void setImage_value(String image_value) {
        this.image_value = image_value;
    }

    public String getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(String isPremium) {
        this.isPremium = isPremium;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getSortTo() {
        return sortTo;
    }

    public void setSortTo(String sortTo) {
        this.sortTo = sortTo;
    }
}
