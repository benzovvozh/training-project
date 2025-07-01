package io.project.mapper;

import io.project.DTO.order.OrderCreateDTO;
import io.project.DTO.order.OrderDTO;
import io.project.DTO.order.OrderUpdateDTO;
import io.project.model.Client;
import io.project.model.Order;
import io.project.model.Product;
import io.project.repository.ClientRepository;
import io.project.repository.OrderRepository;
import io.project.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class OrderMapper {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;

    //target = "client" - указываем, поле client в классе Order
    //source = "clientId" - указываем поле clientId в createDTO
    //используем кастомный метод преобразования, чтобы преобразовать id клиента в самого клиента
    @Mapping(target = "client", source = "clientId", qualifiedByName = "idToEntity")
    @Mapping(target = "products", source = "productsIds", qualifiedByName = "forProducts")
    public abstract Order map(OrderCreateDTO createDTO);

    //кастомный метод преобразования
    @Named("idToEntity")
    public Client mapClientIdToClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow();
    }

    // конвертируем список айдишек продуктов в список продуктов
    @Named("forProducts")
    public List<Product> mapProductIdToProduct(List<Long> productsIds) {
        if (productsIds != null) {
            var result = productsIds.stream()
                    .map(productId ->
                            productRepository.findById(productId).orElseThrow())
                    .toList();
            return result;
        } else return new ArrayList<>();
    }

    // ПОЧЕМУ CLIENT.ID ??????????
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "productsIds", source = "products", qualifiedByName = "forProducts2")
    public abstract OrderDTO map(Order order);

    // конвертируем список продуктов в список их идентификаторов
    @Named("forProducts2")
    public List<Long> mapProductToProductIds(List<Product> products) {
        if (products != null) {
            var result = products.stream()
                    .map(product -> product.getId())
                    .toList();
            return result;
        } else return new ArrayList<>();
    }

    @Mapping(target = "products", source = "productsIds",qualifiedByName = "forProducts")
    public abstract void update(OrderUpdateDTO updateDTO, @MappingTarget Order order);


}
