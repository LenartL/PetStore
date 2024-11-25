package hu.lenartl.petstore.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id FROM Order as o " +
            "WHERE (cast(:from as timestamp) IS NULL OR o.shipDate >= :from) " +
            "AND (cast(:to as timestamp) IS NULL OR o.shipDate <= :to)")
    List<Long> getAllIdsBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
