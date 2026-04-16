package com.genxai.AiCodeGenerator.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProjectMemberId implements Serializable {
    Long projectId;
    Long userId;

}
