package com.teklifver.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "deneme_files")
public class DenemeFiles extends BaseEntity  implements Serializable {

    private String fileName;
    private String modifiedFileName;
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "deneme_id")
    private DenemeEntity denemeEntity;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModifiedFileName() {
        return modifiedFileName;
    }

    public void setModifiedFileName(String modifiedFileName) {
        this.modifiedFileName = modifiedFileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public DenemeEntity getDenemeEntity() {
        return denemeEntity;
    }

    public void setDenemeEntity(DenemeEntity denemeEntity) {
        this.denemeEntity = denemeEntity;
    }
}
