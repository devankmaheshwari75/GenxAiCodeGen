package com.genxai.AiCodeGenerator.services;

import com.genxai.AiCodeGenerator.dtos.billing.CheckoutResponse;
import com.genxai.AiCodeGenerator.dtos.billing.PlanResponse;
import com.genxai.AiCodeGenerator.dtos.billing.PortalResponse;
import com.genxai.AiCodeGenerator.dtos.billing.SubscriptionResponse;
import com.genxai.AiCodeGenerator.dtos.project.CheckoutRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {




    SubscriptionResponse getMySubscriptionDetails(Long userId) ;

    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);


     PortalResponse getCustomerPortalUrl(Long userId);
}
