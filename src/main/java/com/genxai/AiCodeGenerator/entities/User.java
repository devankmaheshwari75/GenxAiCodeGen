package com.genxai.AiCodeGenerator.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String email ;
     String name ;

     String passwordHash;
     String avatarUrl;

     Instant createdAt;
     Instant updatedAt;
     Instant deletedAt;


}
