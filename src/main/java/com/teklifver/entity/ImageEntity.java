package com.teklifver.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    private long postId;

    private String fileName;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
