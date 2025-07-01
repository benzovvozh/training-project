package io.project.mapper;

import io.project.DTO.product.ProductCreateDTO;
import io.project.DTO.product.ProductDTO;
import io.project.DTO.product.ProductUpdateDTO;
import io.project.model.Order;
import io.project.model.Product;
import io.project.repository.OrderRepository;
import io.project.repository.ProductRepository;
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
public abstract class ProductMapper {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Mapping(target = "orders", source = "ordersId", qualifiedByName = "forOrders")
    public abstract Product map(ProductCreateDTO createDTO);

    @Mapping(target = "ordersId", source = "orders", qualifiedByName = "forOrders2")
    public abstract ProductDTO map(Product product);

    public abstract void update(ProductUpdateDTO updateDTO, @MappingTarget Product product);

    @Named("forOrders")
    public List<Order> idsToProducts(List<Long> ordersIds) {
        if (ordersIds != null) {
            var result = ordersIds.stream()
                    .map(id -> orderRepository.findById(id).orElseThrow())
                    .toList();
            return result;
        } else return new ArrayList<>();
    }

    @Named("forOrders2")
    public List<Long> productsToIds(List<Order> orders) {
        if (orders != null) {
            var result = orders.stream()
                    .map(order -> order.getId())
                    .toList();
            return result;
        } else return new ArrayList<>();
    }
}
