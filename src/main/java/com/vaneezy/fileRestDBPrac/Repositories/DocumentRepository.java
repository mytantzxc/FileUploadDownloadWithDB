package com.vaneezy.fileRestDBPrac.Repositories;

import com.vaneezy.fileRestDBPrac.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public Optional<Document> getByDownloadUri(String uri);
}
