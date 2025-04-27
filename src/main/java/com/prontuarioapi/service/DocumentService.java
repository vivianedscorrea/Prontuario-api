package com.prontuarioapi.service;

import com.prontuarioapi.dto.DocumentRequest;
import com.prontuarioapi.dto.DocumentResponse;
import com.prontuarioapi.model.Document;
import com.prontuarioapi.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentResponse createDocument(DocumentRequest request) {
        Document document = new Document();
        document.setName(request.getName());
        document.setDocumentType(request.getDocumentType());
        document.setSentDate(request.getSentDate());
        document.setDocumentFile(request.getDocumentFile());

        document = documentRepository.save(document);

        return convertToResponse(document);
    }

    public DocumentResponse updateDocument(Long id, DocumentRequest request) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        document.setName(request.getName());
        document.setDocumentType(request.getDocumentType());
        document.setSentDate(request.getSentDate());
        document.setDocumentFile(request.getDocumentFile());

        document = documentRepository.save(document);

        return convertToResponse(document);
    }

    public DocumentResponse getDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return convertToResponse(document);
    }

    // Novo método para obter todos os documentos
    public List<DocumentResponse> getAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        return documents.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private DocumentResponse convertToResponse(Document document) {
        DocumentResponse response = new DocumentResponse();
        response.setId(document.getId());
        response.setName(document.getName());
        response.setDocumentType(document.getDocumentType());
        response.setSentDate(document.getSentDate());
        return response;
    }
}


