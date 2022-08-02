package com.vaneezy.fileRestDBPrac.Controller;

import com.vaneezy.fileRestDBPrac.Entity.Document;
import com.vaneezy.fileRestDBPrac.Responses.FileUploadResponse;
import com.vaneezy.fileRestDBPrac.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Document document = documentService.saveFile(multipartFile);
        FileUploadResponse fileUploadResponse = FileUploadResponse.builder()
                .fileName(document.getFileName())
                .downloadUri(document.getDownloadUri())
                .uploadTime(document.getUploadTime())
                .build();
        return new ResponseEntity<FileUploadResponse>(fileUploadResponse,HttpStatus.OK);
    }

    @GetMapping("downloadFile/{fileCode}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
        Document document = documentService.getFileByCode(fileCode);
        String contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(new ByteArrayResource(document.getDocumentContent()));
    }
}
