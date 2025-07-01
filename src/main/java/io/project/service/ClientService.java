package io.project.service;

import io.project.DTO.client.ClientCreateDTO;
import io.project.DTO.client.ClientDTO;
import io.project.DTO.client.ClientUpdateDTO;
import io.project.mapper.ClientMapper;
import io.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    public List<ClientDTO> getAll() {
        var list = repository.findAll().stream()
                .map(mapper::map)
                .toList();
        return list;
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
