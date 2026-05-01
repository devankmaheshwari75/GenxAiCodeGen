package com.genxai.AiCodeGenerator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {
//permission ("resource:action")
    VIEW ("project:view"),
    EDIT ("project:edit"),
    DELETE ("project:delete"),
    MANAGE_MEMBERS("project_members:manage"),
    VIEW_MEMBERS("project_members:view");

    private final String value;


}
