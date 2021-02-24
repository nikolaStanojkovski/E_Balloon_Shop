package mk.finki.ukim.mk.lab.repository.InDatabase;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteById(Long id);
    Optional<Order> findByBalloonColorAndBalloonSize(String balloonColor, String balloonSize);
    void deleteByBalloonColorAndBalloonSize(String balloonColor, String balloonSize);
    List<Order> findAll();
}
