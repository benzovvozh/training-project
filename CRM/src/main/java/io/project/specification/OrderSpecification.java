package io.project.specification;

import io.project.model.Order;
import io.project.model.OrderStatus;
import io.project.model.Product;
import io.project.repository.ProductRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderSpecification {
    @Autowired
    private ProductRepository productRepository;
    public Specification<Order> build(String status, List<Long> products){
        return Specification.where(buildStatusSpec(status))
                .and(buildProductsSpec(products));
    }

    private Specification<Order> buildStatusSpec(String status){
        return Optional.ofNullable(status)
                .map(st -> (Specification<Order>)
                        (root,query,cb)->
                        cb.equal(root.get("status"), (Enum.valueOf(OrderStatus.class, status))))
                .orElse(null);
    }

    private Specification<Order> buildProductsSpec(List<Long> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            // Получаем доступ к коллекции продуктов через join
            Join<Order, Product> productJoin = root.join("products", JoinType.INNER);

            // Создаем условие "WHERE product_id IN (:productIds)"
            return productJoin.get("id").in(productIds); // Используем "id" вместо "product"
        };
    }
}
