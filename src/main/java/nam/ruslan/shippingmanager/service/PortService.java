package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.model.Port;

import java.util.List;
import java.util.Optional;

public interface PortService {

    List<Port> getAll();

    Optional<Port> findById(Long id);

    int getPortCapacity(Long id);
}
