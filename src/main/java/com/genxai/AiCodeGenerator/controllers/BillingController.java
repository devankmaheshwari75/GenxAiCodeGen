package com.genxai.AiCodeGenerator.controllers;

import com.genxai.AiCodeGenerator.dtos.billing.CheckoutResponse;
import com.genxai.AiCodeGenerator.dtos.billing.PlanResponse;
import com.genxai.AiCodeGenerator.dtos.billing.PortalResponse;
import com.genxai.AiCodeGenerator.dtos.billing.SubscriptionResponse;
import com.genxai.AiCodeGenerator.dtos.project.CheckoutRequest;
import com.genxai.AiCodeGenerator.services.PlanService;
import com.genxai.AiCodeGenerator.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class BillingController {


    private final  SubscriptionService subscriptionService;

    private final PlanService planService;



    @GetMapping("/api/plans")

    public ResponseEntity<List<PlanResponse>> getAllActPlans(){

        return ResponseEntity.ok(planService.getAllActivePlans());

    }


    @GetMapping("/api/me/subscription")
    public ResponseEntity<SubscriptionResponse> getMySubscriptionDetails(){

        Long userId =1L ;
        return ResponseEntity.ok(subscriptionService.getMySubscriptionDetails(userId));


    }

    @PostMapping("/api/stripe/checkout")

    public ResponseEntity<CheckoutResponse> createCheckoutResponse(@RequestBody CheckoutRequest request){
        Long userId  =1L;
        return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(request , userId));


    }


    @PostMapping("/api/stripe/portal")
    public ResponseEntity<PortalResponse> openCustomerPortal(){
        Long userId =1L;
        return ResponseEntity.ok(subscriptionService.getCustomerPortalUrl(userId  ));

    }
}
