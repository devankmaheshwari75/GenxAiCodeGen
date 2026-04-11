package com.genxai.AiCodeGenerator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {
    Long id;
    com.genxai.AiCodeGenerator.entities.Project project;
    String namespace;
    String podName;
    String previewUrl;
    com.genxai.AiCodeGenerator.entities.PreviewStatus status;
    Instant startedAt;
    Instant terminatedAt;
    Instant createdAt;

}
