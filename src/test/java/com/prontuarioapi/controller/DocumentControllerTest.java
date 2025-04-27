package com.prontuarioapi.controller;

import com.prontuarioapi.dto.DocumentRequest;
import com.prontuarioapi.dto.DocumentResponse;
import com.prontuarioapi.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DocumentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    void createDocument() throws Exception {
        // Usando SimpleDateFormat para converter a string em Date
        Date sentDate = dateFormat.parse("2025-04-27");

        // Simulando um arquivo como byte[]
        byte[] documentFile = "dummy file content".getBytes();

        DocumentRequest request = new DocumentRequest("Document 1", "Type A", sentDate, documentFile);
        DocumentResponse response = new DocumentResponse(1L, "Document 1", "Type A", sentDate);

        when(documentService.createDocument(Mockito.any(DocumentRequest.class))).thenReturn(response);

        // Aqui, você enviaria o conteúdo do arquivo no corpo da requisição, mas como você só está simulando o envio do nome, mantém-se a string "file.pdf"
        mockMvc.perform(post("/api/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Document 1\",\"documentType\":\"Type A\",\"sentDate\":\"2025-04-27\",\"documentFile\":\"file.pdf\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Document 1"))
                .andExpect(jsonPath("$.documentType").value("Type A"))
                .andExpect(jsonPath("$.sentDate").value("2025-04-27"));

        verify(documentService, times(1)).createDocument(Mockito.any(DocumentRequest.class));
    }

    @Test
    void updateDocument() throws Exception {
        // Usando SimpleDateFormat para converter a string em Date
        Date sentDate = dateFormat.parse("2025-04-28");

        // Simulando um arquivo como byte[]
        byte[] documentFile = "dummy updated file content".getBytes();

        DocumentRequest request = new DocumentRequest("Updated Document", "Type B", sentDate, documentFile);
        DocumentResponse response = new DocumentResponse(1L, "Updated Document", "Type B", sentDate);

        when(documentService.updateDocument(eq(1L), any(DocumentRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/documents/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Document\",\"documentType\":\"Type B\",\"sentDate\":\"2025-04-28\",\"documentFile\":\"file_updated.pdf\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Document"))
                .andExpect(jsonPath("$.documentType").value("Type B"))
                .andExpect(jsonPath("$.sentDate").value("2025-04-28"));

        verify(documentService, times(1)).updateDocument(eq(1L), any(DocumentRequest.class));
    }

    @Test
    void getDocument() throws Exception {
        // Usando SimpleDateFormat para converter a string em Date
        Date sentDate = dateFormat.parse("2025-04-27");

        DocumentResponse response = new DocumentResponse(1L, "Document 1", "Type A", sentDate);

        when(documentService.getDocument(eq(1L))).thenReturn(response);

        mockMvc.perform(get("/api/documents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Document 1"))
                .andExpect(jsonPath("$.documentType").value("Type A"))
                .andExpect(jsonPath("$.sentDate").value("2025-04-27"));

        verify(documentService, times(1)).getDocument(eq(1L));
    }

    @Test
    void getAllDocuments() throws Exception {
        // Usando SimpleDateFormat para converter a string em Date
        Date sentDate1 = dateFormat.parse("2025-04-27");
        Date sentDate2 = dateFormat.parse("2025-04-28");

        List<DocumentResponse> responseList = Arrays.asList(
                new DocumentResponse(1L, "Document 1", "Type A", sentDate1),
                new DocumentResponse(2L, "Document 2", "Type B", sentDate2)
        );

        when(documentService.getAllDocuments()).thenReturn(responseList);

        mockMvc.perform(get("/api/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[0].name").value("Document 1"))
                .andExpect(jsonPath("$[1].name").value("Document 2"));

        verify(documentService, times(1)).getAllDocuments();
    }
}
