package mk.finki.ukim.mk.lab.repository.InMemory;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public class InMemoryOrderRepository {

    public void removeOrder(HttpServletRequest request, Long id) {
        List<Order> orderList = (List<Order>) request.getSession().getAttribute("orderList");
        orderList.removeIf(i -> i.getId().equals(id));
    }

    public Order getById(HttpServletRequest request, Long id) {
        List<Order> orderList = (List<Order>) request.getSession().getAttribute("orderList");
        return orderList.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }

    public void save(String balloonColor, String balloonSize, String clientName, String clientAddress, Long orderID, HttpServletRequest request) {
        List<Order> orderList = (List<Order>) request.getSession().getAttribute("orderList");
        int index = orderList.indexOf(getById(request, orderID));
        orderList.removeIf(i -> i.getId().equals(orderID));
        orderList.add(index, new Order(balloonColor, balloonSize, (User) request.getSession().getAttribute("user")));
    }

}
