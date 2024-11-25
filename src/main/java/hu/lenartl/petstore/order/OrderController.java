package hu.lenartl.petstore.order;

import hu.lenartl.petstore.order.dto.OrderCommand;
import hu.lenartl.petstore.order.dto.OrderDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/store/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Long> getAllOrderIds(
            @RequestParam(required = false)  LocalDateTime from,
            @RequestParam(required = false)  LocalDateTime to) {
        return orderService.getAllIdsBetween(from, to);
    }

    @GetMapping("/{orderId}")
    public OrderDetails getOrderById(@PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
    public OrderDetails save(@RequestBody OrderCommand order) {
        return orderService.save(order);
    }

    @PatchMapping("/{orderId}")
    public OrderDetails updateOrder(@PathVariable Long orderId, @RequestBody OrderCommand order) {
        //TODO: patch NonNull values
        return null;
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
    }

}
