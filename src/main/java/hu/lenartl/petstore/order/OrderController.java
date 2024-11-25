package hu.lenartl.petstore.order;

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
    public Order getOrderById(@PathVariable Long orderId) {
        /*TODO: return an order by id:
        Response model example:
        {
        "id": 0,
        "petId": 0,
        "quantity": 0,
        "shipDate": "2024-11-23T17:13:49.287Z",
        "status": "placed",
        "complete": true
        }
         */
        return null;
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

    @DeleteMapping
    public void deleteOrder(@PathVariable Long orderId) {
        //TODO: delete an order by id, no resp body
    }

}
