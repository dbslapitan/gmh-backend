package com.example.getmesocialservice.model;

import com.example.getmesocialservice.validation.LowercaseOnly;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Comments")
public class Comment {

    @Id
    private String id;
    private String photoId;
    @Length(min = 5)
    private String message;
    @LowercaseOnly
    private String createdBy;
    private Date dateCreated;

    public Comment(String photoId, String message, String createdBy) {
        this.photoId = photoId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
