package hu.lenartl.petstore.order;

import hu.lenartl.petstore.order.dto.OrderCommand;
import hu.lenartl.petstore.order.dto.OrderDetails;
import hu.lenartl.petstore.pet.PetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final PetService petService;

    public OrderService(OrderRepository orderRepository, PetService petService) {
        this.orderRepository = orderRepository;
        this.petService = petService;
    }

    public List<Long> getAllIdsBetween(LocalDateTime from, LocalDateTime to) {
        return orderRepository.getAllIdsBetween(from, to);
    }

    public OrderDetails findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return map(order);
    }

    public OrderDetails save(OrderCommand command) {
        Order order = orderRepository.save(map(command));
        return map(order);
    }

    private Order map(OrderCommand command) throws IllegalArgumentException {
        return Order.builder()
                .pet(petService.findById(command.petId()))
                .quantity(command.quantity())
                .shipDate(command.shipDate())
                .status(OrderStatus.valueOf(command.status().toUpperCase()))
                .complete(command.complete())
                .build();
    }

    private OrderDetails map(Order order) {
        return OrderDetails.builder()
                .id(order.getId())
                .petId(order.getPet().getId())
                .quantity(order.getQuantity())
                .shipDate(order.getShipDate())
                .status(order.getStatus().getDisplayValue())
                .complete(order.isComplete())
                .build();
    }
}
