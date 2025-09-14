package io.project.service;

import feign.Feign;
import feign.FeignException;
import io.project.controller.SubscriptionServiceFeignClient;
import io.project.customException.ClientNotFoundException;
import io.project.customException.ProductNotFoundException;
import io.project.dto.ProductSubDTO;
import io.project.dto.clientSubDTO.ClientSubDTO;
import io.project.dto.subscriptionDTO.SubscriptionCreateDTO;
import io.project.dto.subscriptionDTO.SubscriptionDTO;
import io.project.dto.subscriptionDTO.SubscriptionUpdateDTO;
import io.project.mapper.SubscriptionMapper;
import io.project.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;
    private final SubscriptionServiceFeignClient feignClient;

    public ClientSubDTO checkClientById(Long clientId) {
        log.info("Делаем запрос по поиску клиента к CRM");
        return feignClient.checkClientById(clientId);
    }

    public ProductSubDTO checkProduct(Long productId) {
        log.info("Делаем запрос по поиску продукта к CRM");
        return feignClient.checkProductById(productId);
    }

    public List<SubscriptionDTO> showAll() {
        return repository.findAll().stream()
                .map(mapper::map)
                .toList();
    }

    public SubscriptionDTO show(Long id) {
        var sub = repository.findById(id).orElse(null);
        return mapper.map(sub);
    }

    public SubscriptionDTO create(SubscriptionCreateDTO createDTO) {

        var clientId = createDTO.getClientId();
        var productId = createDTO.getProductId();

        validateClientExists(clientId);
        validateProductExists(productId);

        var sub = mapper.map(createDTO);
        repository.save(sub);
        return mapper.map(sub);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }

    public SubscriptionDTO update(Long id, SubscriptionUpdateDTO updateDTO) {
        var sub = repository.findById(id).orElse(null);
        mapper.update(updateDTO, sub);
        repository.save(sub);
        return mapper.map(sub);
    }

    private void validateClientExists(Long clientId) {
        try {
            checkClientById(clientId);
            log.info("Клиент с id={} найден", clientId);
        } catch (FeignException.NotFound e) {
            throw new ClientNotFoundException("Клиент с id=" + clientId + " не найден, невозможно создать подписку");
        }
    }

    private void validateProductExists(Long productId) {
        try {
            checkProduct(productId);
            log.info("Продукт с id={} найден", productId);
        } catch (FeignException.NotFound e) {
            throw new ProductNotFoundException("Продукт с id=" + productId + " не найден, невозможно создать подписку");
        }
    }
}
