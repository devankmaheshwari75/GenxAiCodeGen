package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.billing.PlanResponse;
import com.genxai.AiCodeGenerator.services.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
