package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @ManyToMany
    private List<Order> orders;

    public ShoppingCart(User user, LocalDateTime dateTime) {
        this.user = user;
        this.dateCreated = dateTime;
        this.orders = new ArrayList<>();
    }

    public ShoppingCart() {

    }
}
