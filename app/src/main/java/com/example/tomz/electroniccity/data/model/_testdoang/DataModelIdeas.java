package com.example.tomz.electroniccity.data.model._testdoang;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tomz on 2/3/2018.
 */

public class DataModelIdeas {
    @SerializedName("id")
    private String ideasID;
    @SerializedName("title")
    private String ideadsTitle;
    @SerializedName("description")
    private String ideasDescription;
    @SerializedName("image")
    private String ideasImage;
    @SerializedName("video")
    private String ideasVideo;

    public String getIdeasID() {
        return ideasID;
    }

    public void setIdeasID(String ideasID) {
        this.ideasID = ideasID;
    }

    public String getIdeasTitle() {
        return ideadsTitle;
    }

    public void setIdeasTitle(String ideadsTitle) {
        this.ideadsTitle = ideadsTitle;
    }

    public String getIdeasDescription() {
        return ideasDescription;
    }

    public void setIdeasDescription(String ideasDescription) {
        this.ideasDescription = ideasDescription;
    }

    public String getIdeasImage() {
        return ideasImage;
    }

    public void setIdeasImage(String ideasImage) {
        this.ideasImage = ideasImage;
    }

    public String getIdeasVideo() {
        return ideasVideo;
    }

    public void setIdeasVideo(String ideasVideo) {
        this.ideasVideo = ideasVideo;
    }
}
