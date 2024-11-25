package hu.lenartl.petstore.order;

import hu.lenartl.petstore.pet.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "complete")
    private boolean complete;
}
