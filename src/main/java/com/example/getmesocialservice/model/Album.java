package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.LowercaseOnly;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Albums")
public class Album {

    @Id
    private String id;
    @Length(max = 10)
    private String name;
    private String description;
    private String coverPhotoUrl;
    @LowercaseOnly
    private String createdBy;
    private Date dateCreated;

    public Album(String name, String description, String coverPhotoUrl, String createdBy) {
        this.name = name;
        this.description = description;
        this.coverPhotoUrl = coverPhotoUrl;
        this.createdBy = createdBy;
        this.dateCreated = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public void setCoverPhotoUrl(String coverPicUrl) {
        this.coverPhotoUrl = coverPicUrl;
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
