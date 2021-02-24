package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.InDatabase.OrderRepository;
import mk.finki.ukim.mk.lab.repository.InDatabase.UserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void removeOrder(Long id) {
        this.orderRepository.deleteById(id);
    }

    @Override
    public Order getById(Long id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order save(String balloonColor, String balloonSize, User user, Long orderId) {
        if(orderId != null)
            this.orderRepository.deleteById(orderId);

        Order order = new Order(balloonColor, balloonSize, user);
        return this.orderRepository.save(order);
    }
}
