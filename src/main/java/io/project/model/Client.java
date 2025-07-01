package io.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telephoneNumber;
    /*
    указываем связь один ко многим, владельцем связи является класс Order

    каскадирование - если мы изменяем родительскую сущность, то изменяются и связанные, т.е.
    если удаляется клиент, то сначала удаляется список заказов клиента, а потом сам клиент

    orphanRemoval - если мы удаляем элемент из списка (orders), то этот элемент(строка) удаляется из БД
     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}
