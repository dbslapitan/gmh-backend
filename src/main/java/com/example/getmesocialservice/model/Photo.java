package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.LowercaseOnly;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Photos")
public class Photo {

    @Id
    private String id;
    private String photoId;
    private String photoUrl;
    @LowercaseOnly
    private String createdBy;
    private Date dateCreated;

    public Photo(String photoId, String photoUrl, String createdBy) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.createdBy = createdBy;
        this.dateCreated = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
