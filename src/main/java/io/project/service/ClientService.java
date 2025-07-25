package io.project.service;

import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.mapper.ClientMapper;
import io.project.model.Client;
import io.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    public List<ClientDTO> getClientsByFirstName(String firstName, Pageable paging) {
        Page<Client> page = firstName == null
                ? repository.findAll(paging)
                : repository.findClientByFirstName(firstName, paging);
        return page.map(mapper::map).getContent();

    }

    public ClientDTO create(ClientCreateDTO clientCreateDTO) {
        var client = mapper.map(clientCreateDTO);
        repository.save(client);
        return mapper.map(client);
    }

    public ClientDTO show(Long id) {
        var client = repository.findById(id).orElse(null);
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
