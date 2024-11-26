package hu.lenartl.petstore.pet;

import hu.lenartl.petstore.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PetStatus status;

    @OneToMany(mappedBy = "pet")
    private transient List<Order> order;
}
