package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;

import java.util.List;
import java.util.Optional;

public interface ShipService {

    List<Ship> getAll();

    Optional<Ship> findById(Long id);

    void deleteById(Long id);

    void save(Ship ship);

    void updateStatus(Long id, ShipStatus status);

    ShipStatus getStatus(Long id);
}
