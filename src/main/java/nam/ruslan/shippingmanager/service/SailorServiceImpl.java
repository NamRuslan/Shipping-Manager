package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.exception.CaptainException;
import nam.ruslan.shippingmanager.exception.PortException;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Rank;
import nam.ruslan.shippingmanager.model.Sailor;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.repository.SailorRepository;
import nam.ruslan.shippingmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */
@Service
public class SailorServiceImpl implements SailorService{

    private final SailorRepository sailorRepository;
    private final ShipRepository shipRepository;

    public SailorServiceImpl(SailorRepository sailorRepository, ShipRepository shipRepository) {
        this.sailorRepository = sailorRepository;
        this.shipRepository = shipRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSailorToShip(Long shipId, Long sailorId) {

        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship with id: " + shipId + " is not found"));

        Sailor sailor = sailorRepository.findById(sailorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sailor with id: " + sailorId + " is not found"));

        if (ship.getStatus() == ShipStatus.SEA) {
            throw new PortException("Crew can only be changed in port");

        } else if (sailor.getShipId() != null) {
            throw new IllegalArgumentException("Sailor is already on other board");
        }

        if (sailor.getRank() == Rank.CAPTAIN) {
            if (ship.isHasCaptain()) throw new CaptainException("Ship is already has captain");
            ship.setHasCaptain(true);
        }

        sailor.setId(shipId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSailorOffShip(Long shipId, Long sailorId) {

        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new ResourceNotFoundException("Ship with id: " + shipId + " is not found"));

        if (ship.getStatus() == ShipStatus.SEA) {
            throw new PortException("Crew can only be changed in port");
        }

        Sailor sailor = sailorRepository.findById(sailorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sailor with id: " + sailorId + " is not found"));

        if (!sailor.getShipId().equals(shipId)) {
            throw new ResourceNotFoundException("Sailor is not on this ship");
        }

        if (sailor.getRank() == Rank.CAPTAIN) {
            ship.setHasCaptain(false);
        }

        sailor.setShipId(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerNewSailor(Sailor sailor) {
        sailorRepository.save(sailor);
    }
}
