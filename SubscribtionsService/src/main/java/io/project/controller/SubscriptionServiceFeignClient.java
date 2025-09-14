package io.project.controller;

import io.project.dto.ProductSubDTO;
import io.project.dto.clientSubDTO.ClientSubDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crm-service", url = "${crm.service.url}")
public interface SubscriptionServiceFeignClient {
    @GetMapping("/clients/{id}")
    ClientSubDTO checkClientById (@PathVariable("id") Long id);

    @GetMapping("/products/{id}")
    ProductSubDTO checkProductById (@PathVariable("id") Long id);

}
