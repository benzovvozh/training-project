package io.project.service;

import feign.FeignException;
import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientCreateEvent;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.DTO.product.ProductCreatedEvent;

import io.project.customException.NotFoundException;
import io.project.mapper.ClientMapper;
import io.project.model.Client;
import io.project.repository.ClientRepository;
import io.project.specification.ClientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
//    @Autowired
//    private KafkaTemplate<String, ClientCreateEvent> kafkaTemplate;
    @Autowired
    private ClientRepository repository;
    @Autowired
    private ClientSpecification clientSpecification;
    @Autowired
    private ClientMapper mapper;

    public List<ClientDTO> getClientsWithSpec(String firstName, String lastName, Pageable paging) {
        var spec = clientSpecification.build(firstName, lastName);
        Page<Client> page = repository.findAll(spec, paging);
        return page.map(mapper::map).getContent();

    }

    public ClientDTO create(ClientCreateDTO clientCreateDTO) {
        var client = mapper.map(clientCreateDTO);
        repository.save(client);
        System.out.println("client created");
//        ClientCreateEvent clientCreateEvent = new ClientCreateEvent(
//                clientCreateDTO.getFirstName(),
//                clientCreateDTO.getLastName(),
//                clientCreateDTO.getEmail(),
//                clientCreateDTO.getTelephoneNumber());
//        kafkaTemplate.send("client-created-event-topic", String.valueOf(client.getId()), clientCreateEvent);
        return mapper.map(client);
    }

    public ClientDTO show(Long id) {
        var client = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Client not found in CRM"));
        return mapper.map(client);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public ClientDTO update(Long id, ClientUpdateDTO updateDTO) {
        var client = repository.findById(id).orElse(null);
        mapper.update(updateDTO, client);
        repository.save(client);
        return mapper.map(client);
    }


}
