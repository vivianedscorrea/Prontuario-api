package com.prontuarioapi.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String documentType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;

    @Lob
    private byte[] documentFile;

    public Document(Long id, String name, String documentType, Date sentDate, byte[] documentFile) {
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.sentDate = sentDate;
        this.documentFile = documentFile;
    }

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public byte[] getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(byte[] documentFile) {
        this.documentFile = documentFile;
    }
}

