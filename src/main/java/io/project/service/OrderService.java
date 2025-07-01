package io.project.service;

import io.project.DTO.order.OrderCreateDTO;
import io.project.DTO.order.OrderDTO;
import io.project.DTO.order.OrderUpdateDTO;
import io.project.mapper.OrderMapper;
import io.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderMapper mapper;

    public List<OrderDTO> getAll() {
        var order = repository.findAll().stream()
                .map(mapper::map)
                .toList();
        return order;
    }

    public OrderDTO show(Long id) {
        var order = repository.findById(id).orElse(null);
        return mapper.map(order);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }

    public OrderDTO create(OrderCreateDTO createDTO) {
        var order = mapper.map(createDTO);
        repository.save(order);
        return mapper.map(order);
    }
    public OrderDTO update(OrderUpdateDTO updateDTO, Long id){
        var order = repository.findById(id).orElseThrow();
        mapper.update(updateDTO,order);
        repository.save(order);
        return mapper.map(order);
    }
}
