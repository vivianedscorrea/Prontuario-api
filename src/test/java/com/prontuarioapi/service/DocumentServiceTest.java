package com.prontuarioapi.service;

import com.prontuarioapi.dto.DocumentRequest;
import com.prontuarioapi.dto.DocumentResponse;
import com.prontuarioapi.model.Document;
import com.prontuarioapi.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    private DocumentRequest documentRequest;
    private Document document;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws Exception {
        // Usando SimpleDateFormat para converter a string em Date
        Date sentDate = dateFormat.parse("2025-04-27");

        // Simulando o arquivo como byte[]
        byte[] documentFile = "dummy file content".getBytes();

        documentRequest = new DocumentRequest("Document 1", "Type A", sentDate, documentFile);
        document = new Document(1L, "Document 1", "Type A", sentDate, documentFile);
    }

    @Test
    void createDocument() {
        when(documentRepository.save(Mockito.any(Document.class))).thenReturn(document);

        DocumentResponse response = documentService.createDocument(documentRequest);

        assertNotNull(response);
        assertEquals("Document 1", response.getName());
        verify(documentRepository, times(1)).save(Mockito.any(Document.class));
    }

    @Test
    void updateDocument() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(documentRepository.save(Mockito.any(Document.class))).thenReturn(document);

        DocumentResponse response = documentService.updateDocument(1L, documentRequest);

        assertNotNull(response);
        assertEquals("Document 1", response.getName());
        verify(documentRepository, times(1)).findById(1L);
        verify(documentRepository, times(1)).save(Mockito.any(Document.class));
    }

    @Test
    void getDocument() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));

        DocumentResponse response = documentService.getDocument(1L);

        assertNotNull(response);
        assertEquals("Document 1", response.getName());
        verify(documentRepository, times(1)).findById(1L);
    }

    @Test
    void getAllDocuments() throws ParseException {
        List<Document> documents = Arrays.asList(
                document,
                new Document(2L, "Document 2", "Type B", dateFormat.parse("2025-04-28"), "dummy second file".getBytes())
        );
        when(documentRepository.findAll()).thenReturn(documents);

        List<DocumentResponse> response = documentService.getAllDocuments();

        assertEquals(2, response.size());
        verify(documentRepository, times(1)).findAll();
    }
}
