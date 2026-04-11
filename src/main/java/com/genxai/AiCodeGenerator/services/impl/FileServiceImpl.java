package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.file.FileNode;
import com.genxai.AiCodeGenerator.services.FileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFilesOfProject(Long id) {
        return List.of();
    }

    @Override
    public FileNode getFileByPath(Long id, String path) {
        return null;
    }
}
