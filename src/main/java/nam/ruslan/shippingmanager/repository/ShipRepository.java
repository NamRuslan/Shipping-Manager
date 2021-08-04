package nam.ruslan.shippingmanager.repository;

import nam.ruslan.shippingmanager.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    int countByPortId(Long id);
}
