package com.example.tomz.electroniccity.data.model.api.promo;

public class DataPromoResponse {

    private String statusResponse, id_banner, priority, name, type, desc, link,
            image_banner, image_mobile, image_apps, startDateTime, endDateTime, isPublish,
            meta_title, meta_desc, meta_keywords, userCreated,
            dateCreated, userModified, dateModified;

    public DataPromoResponse(){

    }

    public DataPromoResponse(String statusResponse, String id_banner, String priority, String name, String type,
                             String desc, String link, String image_banner, String image_mobile, String image_apps,
                             String startDateTime, String endDateTime, String isPublish, String meta_title,
                             String meta_desc, String meta_keywords, String userCreated, String dateCreated,
                             String userModified, String dateModified) {
        this.statusResponse = statusResponse;
        this.id_banner = id_banner;
        this.priority = priority;
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.link = link;
        this.image_banner = image_banner;
        this.image_mobile = image_mobile;
        this.image_apps = image_apps;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isPublish = isPublish;
        this.meta_title = meta_title;
        this.meta_desc = meta_desc;
        this.meta_keywords = meta_keywords;
        this.userCreated = userCreated;
        this.dateCreated = dateCreated;
        this.userModified = userModified;
        this.dateModified = dateModified;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getId_banner() {
        return id_banner;
    }

    public void setId_banner(String id_banner) {
        this.id_banner = id_banner;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_banner() {
        return image_banner;
    }

    public void setImage_banner(String image_banner) {
        this.image_banner = image_banner;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public void setImage_mobile(String image_mobile) {
        this.image_mobile = image_mobile;
    }

    public String getImage_apps() {
        return image_apps;
    }

    public void setImage_apps(String image_apps) {
        this.image_apps = image_apps;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(String isPublish) {
        this.isPublish = isPublish;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_desc() {
        return meta_desc;
    }

    public void setMeta_desc(String meta_desc) {
        this.meta_desc = meta_desc;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
