package com.jpmc.midascore.component;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class IncentiveClient {

    private final RestTemplate restTemplate;

    public IncentiveClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public float fetchIncentive(Transaction transaction) {
        ResponseEntity<Incentive> resp =
                restTemplate.postForEntity("http://localhost:8080/incentive", transaction, Incentive.class);

        Incentive incentive = resp.getBody();
        return (incentive == null) ? 0.0f : incentive.getAmount();
    }
}
