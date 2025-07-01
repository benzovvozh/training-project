package io.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private String description;
    private Integer price;

    /*
    делаем владельцем (не указываем mappedBy) связи с заказами
    потому что, если мы удаляем продукт -> то должен удалиться заказ
     */
    @ManyToMany
    private List<Order> orders = new ArrayList<>();

}
