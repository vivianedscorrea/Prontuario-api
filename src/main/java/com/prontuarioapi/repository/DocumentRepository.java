package com.prontuarioapi.repository;

import com.prontuarioapi.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

