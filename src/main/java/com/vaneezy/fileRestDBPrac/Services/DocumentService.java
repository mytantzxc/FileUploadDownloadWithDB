package com.vaneezy.fileRestDBPrac.Services;

import com.vaneezy.fileRestDBPrac.Entity.Document;
import com.vaneezy.fileRestDBPrac.Repositories.DocumentRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@ConfigurationProperties(prefix = "file")
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) throws FileSystemException {
        this.documentRepository = documentRepository;
    }

    public Document saveFile(MultipartFile file) throws IOException {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Document document = Document.builder()
                .fileName(file.getOriginalFilename())
                .documentContent(file.getBytes())
                .downloadUri(RandomStringUtils.randomAlphabetic(8))
                .uploadTime(LocalDateTime.now())
                .build();
        documentRepository.save(document);
        return document;
    }

    public Document getFileByCode(String fileCode) throws FileNotFoundException, MalformedURLException {
        return documentRepository.getByDownloadUri(fileCode)
                .orElseThrow(
                        () -> new FileNotFoundException("File with this uri: " + fileCode + " not found")
                );
    }
}
