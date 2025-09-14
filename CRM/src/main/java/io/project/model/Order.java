package io.project.model;

import io.project.mapper.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity // помечаем как сущность
@Table(name = "orders") //создаём таблицу с названием
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // аннотация для автоматического заполнения полей по типу дата создания
@AllArgsConstructor // конструкторы
@NoArgsConstructor
public class Order implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    //владелец связи с клиентом
    @ManyToOne
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getOrders().remove(this);
    }
}
