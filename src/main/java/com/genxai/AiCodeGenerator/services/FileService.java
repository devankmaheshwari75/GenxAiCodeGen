package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.file.FileNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface FileService {

    public List<FileNode> getFilesOfProject(Long id) ;
    public FileNode getFileByPath(Long id, String path) ;
}
