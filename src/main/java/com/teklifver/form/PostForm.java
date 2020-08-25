package com.teklifver.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class PostForm implements Form {

    public Long postFormId;
    public String title;
    public String description;
    public String categoryId;
    public String subCategoryId;
    public int status;


    public Long getPostFormId() {
        return postFormId;
    }

    public void setPostFormId(Long postFormId) {
        this.postFormId = postFormId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MultipartFile> file;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }
}
