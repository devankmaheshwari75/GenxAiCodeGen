package com.genxai.AiCodeGenerator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    Long id ;
    Project project;
    User user;

    ChatSession  chatSession;

    MessageRole role ;
    String content ;
    String toolCalls;
    String toolCallId;
    Integer tokensUsed;
    Instant createAt;

}
