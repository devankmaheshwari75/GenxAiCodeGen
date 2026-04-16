package com.genxai.AiCodeGenerator.controllers;

import com.genxai.AiCodeGenerator.dtos.file.FileNode;
import com.genxai.AiCodeGenerator.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file/projects/{id}")
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileNode>> getFiles(@PathVariable Long id){

        return ResponseEntity.ok(fileService.getFilesOfProject(id));

    }

    @GetMapping("/{*path}")
    public ResponseEntity<FileNode>  getFileByPath(@PathVariable Long id , @PathVariable String path){


        return ResponseEntity.ok(fileService.getFileByPath(id , path));

    }

}
