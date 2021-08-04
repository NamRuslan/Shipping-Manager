package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.dto.PortDto;
import nam.ruslan.shippingmanager.model.Port;

import java.util.List;
import java.util.Optional;

/**
 * Service to work with {@link Port}
 */
public interface PortService {

    /**
     * Get all ports
     * @return List of {@link Port} objects
     */
    List<Port> getAll();

    /**
     * Find {@link Port} by id
     * @param id {@link Port} id
     * @return Optional {@link Port{}
     */
    Optional<Port> findById(Long id);


    /**
     * Get capacity of {@link Port} with id
     * @param id {@link Port} id
     * @return {@link Port} capacity
     */
    PortDto getPortCapacity(Long id);
}
