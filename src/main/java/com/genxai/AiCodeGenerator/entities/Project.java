package com.genxai.AiCodeGenerator.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String name ;
    @ManyToOne
    @JoinColumn()
    User owner;
    Boolean isPublic ;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;



}
