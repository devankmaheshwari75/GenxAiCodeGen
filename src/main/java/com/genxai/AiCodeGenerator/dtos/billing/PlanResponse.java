package com.genxai.AiCodeGenerator.dtos.billing;

public record PlanResponse(
        String name ,
        Long id,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews,
        Boolean unlimitedAi,

        String price

) {
}
