package nam.ruslan.shippingmanager.repository;

import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    int countByPortId(Long id);
    List<Ship> getAllByStatus(ShipStatus status);
}
