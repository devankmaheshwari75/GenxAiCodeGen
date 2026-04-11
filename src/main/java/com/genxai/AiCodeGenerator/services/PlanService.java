package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.billing.PlanResponse;

import java.util.List;

public interface PlanService {
    public List<PlanResponse> getAllActivePlans();
}
