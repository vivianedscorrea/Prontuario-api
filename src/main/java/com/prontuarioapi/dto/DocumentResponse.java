package com.prontuarioapi.dto;

import java.util.Date;

public class DocumentResponse {

    private Long id;
    private String name;
    private String documentType;
    private Date sentDate;

    public DocumentResponse(Long id, String name, String documentType, Date sentDate) {
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.sentDate = sentDate;
    }

    public DocumentResponse() {
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
}

