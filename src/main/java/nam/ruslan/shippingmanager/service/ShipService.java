package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.dto.ShipDto;
import nam.ruslan.shippingmanager.dto.ShipStatusDto;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;

import java.util.List;
import java.util.Optional;

/**
 * Service to work with {@link Ship}
 */
public interface ShipService {

    /**
     * Get all ships
     * @return List of {@link Ship}
     */
    List<ShipDto> getAll();

    /**
     * Get all ships with certain status
     * @param status Status of the ship
     * @return List of {@link Ship}
     */
    //set this to string in a case
    List<ShipDto> getAll(ShipStatus status);

    /**
     * Find {@link Ship} with certain id
     * @param id {@link Ship} id
     * @return Optinal {@link Ship}
     */
    Optional<Ship> findById(Long id);

    /**
     * Delete {@link Ship} with certain id
     * @param id {@link Ship} id
     */
    void deleteById(Long id);

    /**
     * Save {@link Ship} as persist data
     * @param ship {@link Ship} object to be saved
     */
    void save(Ship ship);

    /**
     * Update status of {@link Ship} with certain id
     * @param id {@link Ship} id
     * @param status {@link ShipStatus} of {@link Ship}
     */
    void updateStatus(Long id, ShipStatusDto status);

    /**
     * Get status of {@link Ship} with certain id
     * @param id {@link Ship} id
     * @return {@link ShipStatus} of {@link Ship}
     */
    ShipStatus getStatus(Long id);
}
