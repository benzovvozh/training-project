package io.project.specification;


import io.project.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductSpecification {
    public Specification<Product> build(Integer price, String title, String description) {
        return Specification.where(buildPriceSpec(price))
                .and(buildTitleSpec(title))
                .and(buildDescriptionSpec(description));
    }

    private Specification<Product> buildPriceSpec(Integer price) {
        return Optional.ofNullable(price)
                .map(p ->
                        (Specification<Product>) (root, query, cb) ->
                        cb.equal(root.get("price"), p))
                .orElse(null);
    }

    private Specification<Product> buildTitleSpec(String title) {
        return Optional.ofNullable(title)
                .map(name ->
                        (Specification<Product>) (root, query, cb) ->
                        cb.equal(root.get("title"), name))
                .orElse(null);
    }

    private Specification<Product> buildDescriptionSpec(String description) {
        return Optional.ofNullable(description)
                .map(name ->
                        (Specification<Product>) (root, query, cb) ->
                        cb.equal(root.get("description"), name))
                .orElse(null);
    }
}
