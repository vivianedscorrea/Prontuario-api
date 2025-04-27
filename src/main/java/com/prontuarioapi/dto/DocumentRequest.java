package com.prontuarioapi.dto;

import java.util.Date;

public class DocumentRequest {

    private String name;
    private String documentType;
    private Date sentDate;
    private byte[] documentFile; // Arquivo em formato PDF ou outro

    public DocumentRequest(String name, String documentType, Date sentDate, byte[] documentFile) {
        this.name = name;
        this.documentType = documentType;
        this.sentDate = sentDate;
        this.documentFile = documentFile;
    }

    // Getters and Setters
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

