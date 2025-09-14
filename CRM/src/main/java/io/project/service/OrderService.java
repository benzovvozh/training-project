package io.project.service;

import io.project.DTO.order.OrderCreateDTO;
import io.project.DTO.order.OrderDTO;
import io.project.DTO.order.OrderUpdateDTO;
import io.project.mapper.OrderMapper;
import io.project.model.Order;
import io.project.model.OrderStatus;
import io.project.model.Product;
import io.project.repository.OrderRepository;
import io.project.repository.ProductRepository;
import io.project.specification.OrderSpecification;
import io.project.specification.ProductSpecification;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private OrderSpecification specification;

    public List<OrderDTO> showAll(String status, List<Long> products, Pageable paging) {
        var spec = specification.build(status, products);
        var page = repository.findAll(spec, paging);
        return page.map(mapper::map).getContent();
    }

    public List<OrderDTO> getOrdersByStatus(String status, Pageable paging) {
        Page<Order> page = status == null
                ? repository.findAll(paging)
                : repository.findOrderByStatus(Enum.valueOf(OrderStatus.class, status), paging);
        return page.map(mapper::map).getContent();
    }

    public OrderDTO show(Long id) {
        var order = repository.findById(id).orElse(null);
        return mapper.map(order);
    }

    public void destroy(Long id) {
        repository.deleteById(id);
    }

    public OrderDTO create(OrderCreateDTO createDTO) {
        var order = mapper.mapCreate(createDTO);
        repository.save(order);
        return mapper.map(order);
    }

    public OrderDTO update(OrderUpdateDTO updateDTO, Long id) {
        var order = repository.findById(id).orElseThrow();
        mapper.update(updateDTO, order);
        repository.save(order);
        return mapper.map(order);
    }
}
