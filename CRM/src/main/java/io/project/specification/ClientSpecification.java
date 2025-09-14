package io.project.specification;

import io.project.model.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientSpecification {
    /*
    Root - позволяет получить доступ к атрибутам сущности
    Criteria Query - используется для настройки запроса
    Criteria Builder - предоставляет методы создания условий
     */
    public Specification<Client> build(String firstName, String lastName) {
        return Specification.where(buildFirstNameSpec(firstName))
                .and(buildLastNameSpec(lastName));
    }

    private Specification<Client> buildFirstNameSpec(String firstName) {
        return Optional.ofNullable(firstName)
                .map(name -> (Specification<Client>) (root, query, cb) ->
                        cb.equal(root.get("firstName"), name))
                .orElse(null);
    }

    private Specification<Client> buildLastNameSpec(String lastName) {
        return Optional.ofNullable(lastName)
                .map(name -> (Specification<Client>) (root, query, cb) ->
                        cb.equal(root.get("lastName"), name))
                .orElse(null);
    }
}
