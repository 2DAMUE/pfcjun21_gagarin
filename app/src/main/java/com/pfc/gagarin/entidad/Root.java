package com.pfc.gagarin.entidad;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    @SerializedName("photos")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
