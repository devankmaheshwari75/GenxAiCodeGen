package com.genxai.AiCodeGenerator.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(

        name = "project",

        indexes = {
                @Index(
                        name = "idx_project_owner_deleted_updated",
                        columnList = "owner_id, deleted_at, updated_at DESC"
                )
        }
)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String name ;
    @ManyToOne
    @JoinColumn()
    User owner;
    Boolean isPublic ;

    @CreationTimestamp
    Instant createdAt;
    @UpdateTimestamp
    Instant updatedAt;
    Instant deletedAt;



}
