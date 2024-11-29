package hu.lenartl.petstore.order;

import com.github.fge.jsonpatch.JsonPatch;
import hu.lenartl.petstore.order.dto.OrderCommand;
import hu.lenartl.petstore.order.dto.OrderDetails;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/store/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Long> getAllOrderIds(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
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

    @PatchMapping(value = "/{orderId}", consumes = "application/json-patch+json")
    public OrderDetails updateOrder(@PathVariable Long orderId, @RequestBody JsonPatch patchDocument) {
        return orderService.update(orderId, patchDocument);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
    }

}
