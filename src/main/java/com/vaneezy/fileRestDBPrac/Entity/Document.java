package com.vaneezy.fileRestDBPrac.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "document")
@Builder @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "document_content")
    private byte[] documentContent;
    @Column(name = "download_uri")
    private String downloadUri;
    @Column(name = "upload_time")
    private LocalDateTime uploadTime;
}
