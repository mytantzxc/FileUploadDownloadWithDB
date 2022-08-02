package com.vaneezy.fileRestDBPrac.Responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private LocalDateTime uploadTime;
}
