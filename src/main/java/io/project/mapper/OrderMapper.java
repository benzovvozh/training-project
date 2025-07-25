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

    //игнорируем этот маппинг и используем свой с преобразованием ид клиента и продуктов в сущности
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "products", ignore = true)
    public abstract Order map(OrderCreateDTO createDTO);

    // ПОЧЕМУ CLIENT.ID ??????????
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "productsId", source = "products", qualifiedByName = "modelToId")
    public abstract OrderDTO map(Order order);

    public abstract void update(OrderUpdateDTO updateDTO, @MappingTarget Order order);

    /*
    сначала испульзуем базовый маппинг
    потом ставим клиента с преобразованием из его айди
    проверяем пустой ли список продуктов
    проходимся по списку продуктов, преобразуя каждый из его айди и добавляем
    */
    public Order mapCreate(OrderCreateDTO orderCreateDTO) {
        var order = map(orderCreateDTO);
        order.setClient(clientRepository.findById(orderCreateDTO.getClientId()).orElseThrow());
        if (orderCreateDTO.getProductsId() != null) {
            var list = orderCreateDTO.getProductsId();
            for (var productId : list) {
                var product = productRepository.findById(productId).orElseThrow();
                order.addProduct(product);
            }
        }
        return order;
    }


    // конвертируем список продуктов в список их идентификаторов
    @Named("modelToId")
    public List<Long> mapProductToProductIds(List<Product> products) {
        if (products != null) {
            var result = products.stream()
                    .map(product -> product.getId())
                    .toList();
            return result;
        } else return new ArrayList<>();
    }


}
