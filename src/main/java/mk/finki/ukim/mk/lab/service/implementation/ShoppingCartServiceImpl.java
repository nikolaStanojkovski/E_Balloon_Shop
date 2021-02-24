package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.InDatabase.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.InDatabase.UserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public void addOrderToCartWithUser(Order order, String username, Long cartId) {
        User user = this.userRepository.findByUsername(username).get();
        ShoppingCart shoppingCart = user.getCarts().stream().filter(i -> i.getId().equals(cartId)).findFirst().get();
        shoppingCart.getOrders().add(order);
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart listOrders(Long id) {
        return this.shoppingCartRepository.findById(id).get();
    }

    @Override
    public void addShoppingCart(ShoppingCart cart) {
        this.shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return this.shoppingCartRepository.findById(id).get();
    }
}
