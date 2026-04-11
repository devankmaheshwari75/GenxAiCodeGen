package com.genxai.AiCodeGenerator.dtos.file;

public record FileNode(
        String name ,
        String path ,
        Integer size ,
        String type
) {
}
