package com.genxai.AiCodeGenerator.entities;

import com.genxai.AiCodeGenerator.enums.ProjectPermission;
import static com.genxai.AiCodeGenerator.enums.ProjectPermission.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor
@Getter
public enum ProjectRole {

    EDITOR(DELETE , EDIT , VIEW_MEMBERS , VIEW) ,
    VIEWER(VIEW, VIEW_MEMBERS) ,
    OWNER(VIEW , DELETE , EDIT ,MANAGE_MEMBERS , VIEW_MEMBERS);

    ProjectRole(ProjectPermission... permissions){
        this.permissions =Set.of(permissions);

    }

    private final Set<ProjectPermission> permissions;


}
