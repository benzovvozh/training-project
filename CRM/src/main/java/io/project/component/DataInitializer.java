package io.project.component;

import io.project.model.*;
import io.project.repository.ClientRepository;
import io.project.repository.EmployeeRepository;
import io.project.repository.OrderRepository;
import io.project.service.ClientService;
import io.project.service.EmployeeService;
import io.project.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// класс для создания базовых объектов
@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        var emp = createEmployee();
//        var client = createClient();
//        var order = createOrder(client);
    }

    private Client createClient() {
        var client = new Client();
        client.setEmail("client@gmail.com");
        client.setFirstName("Petr");
        client.setLastName("Ivanov");
        client.setTelephoneNumber("+79990295794");
        clientRepository.save(client);
        return client;
    }

    private Employee createEmployee() {
        var emp = new Employee();
        emp.setFirstName("Vladimir");
        emp.setLastName("Shumkov");
        emp.setEmail("vladimir@gmail.com");
        emp.setRole(EmployeeRole.ADMIN);
        emp.setPassword("123456");
        employeeRepository.save(emp);
        return emp;
    }

    private Order createOrder(Client client) {
        var order = new Order();
        order.setClient(client);
        order.setStatus(OrderStatus.NEW);
        orderRepository.save(order);
        return order;
    }
}
