package com.genxai.AiCodeGenerator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    Long id ;
    User user;
    Plan plan ;
    String stripeCustomerId;
    String stripeSubscriptionId;
    Instant currentPeriodStart ;
    Instant currentPeriodEnd;
    Instant cancelAtPeriodEnd ;
    Instant createdAt;
    Instant updatedAt;
    SubscriptionStatus status;




}
