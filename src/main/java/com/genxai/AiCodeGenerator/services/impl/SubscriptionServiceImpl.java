package com.genxai.AiCodeGenerator.services.impl;

import com.genxai.AiCodeGenerator.dtos.billing.CheckoutResponse;
import com.genxai.AiCodeGenerator.dtos.billing.PortalResponse;
import com.genxai.AiCodeGenerator.dtos.billing.SubscriptionResponse;
import com.genxai.AiCodeGenerator.dtos.project.CheckoutRequest;
import com.genxai.AiCodeGenerator.services.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getMySubscriptionDetails(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse getCustomerPortalUrl(Long userId) {
        return null;
    }
}
