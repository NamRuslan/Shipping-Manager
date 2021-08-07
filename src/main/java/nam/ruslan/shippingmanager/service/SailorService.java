package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.model.Sailor;

/**
 * Service to work with {@link Sailor}
 */
public interface SailorService {

    /**
     * Set sailor TO the ship
     * @param shipId Id of the {@link nam.ruslan.shippingmanager.model.Ship}
     * @param sailorId Id of the {@link Sailor}
     */
    void setSailorToShip(Long shipId, Long sailorId);

    /**
     * Set sailor OFF the ship
     * @param shipId Id of the {@link nam.ruslan.shippingmanager.model.Ship}
     * @param sailorId Id of the {@link Sailor}
     */
    void setSailorOffShip(Long shipId, Long sailorId);

    /**
     * Register new sailor and add to DB
     * @param sailor {@link Sailor} object
     */
    void registerNewSailor(Sailor sailor);
}
