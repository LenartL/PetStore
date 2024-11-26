package hu.lenartl.petstore.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import hu.lenartl.petstore.exception.JsonPatchMappingException;
import hu.lenartl.petstore.exception.OrderNotFoundException;
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
    private final ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, PetService petService, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.petService = petService;
        this.objectMapper = objectMapper;
    }

    public List<Long> getAllIdsBetween(LocalDateTime from, LocalDateTime to) {
        return orderRepository.getAllIdsBetween(from, to);
    }

    public OrderDetails findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        return map(order);
    }

    public OrderDetails save(OrderCommand command) {
        Order order = orderRepository.save(map(command));
        return map(order);
    }

    public OrderDetails update(Long orderId, JsonPatch patchDocument) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order = applyPatch(patchDocument, order);

        return map(order);
    }

    public void deleteById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderRepository.delete(order);
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

    private Order applyPatch(JsonPatch patchDocument, Order order) {
        try {
            JsonNode node = patchDocument.apply(objectMapper.convertValue(order, JsonNode.class));
            return objectMapper.treeToValue(node, Order.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonPatchMappingException(e.getMessage());
        }
    }
}
