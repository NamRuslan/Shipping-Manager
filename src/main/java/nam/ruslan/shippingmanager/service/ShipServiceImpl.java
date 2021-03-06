package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.dto.ShipDto;
import nam.ruslan.shippingmanager.dto.ShipStatusDto;
import nam.ruslan.shippingmanager.exception.CaptainException;
import nam.ruslan.shippingmanager.exception.PortException;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.repository.PortRepository;
import nam.ruslan.shippingmanager.repository.SailorRepository;
import nam.ruslan.shippingmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class ShipServiceImpl implements ShipService{

    private final ShipRepository shipRepository;
    private final PortRepository portRepository;
    private final SailorRepository sailorRepository;

    public ShipServiceImpl(ShipRepository shipRepository, PortRepository portRepository, SailorRepository sailorRepository) {
        this.shipRepository = shipRepository;
        this.portRepository = portRepository;
        this.sailorRepository = sailorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShipDto> getAll() {
        return shipRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShipDto> getAll(ShipStatus status) {
        return shipRepository.getAllByStatus(status).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        shipRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Ship ship) {
        if (isFull(ship.getPortId())) {
            throw new PortException("Port is full");
        }

        shipRepository.save(ship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(Long id, ShipStatusDto status) {
        if (!shipRepository.existsById(id)) throw new ResourceNotFoundException("Ship is not found");

        Ship ship = shipRepository.getById(id);

        if (status.getStatus() == ShipStatus.SEA){
            if (!ship.isHasCaptain()) throw new CaptainException("Ship is not allowed to sail: no captain");
            ship.setPortId(null);

        } else if (isFull(status.getPortId())) {
            throw new PortException("Port is full");
        }

        ship.setPortId(status.getPortId());
        ship.setStatus(status.getStatus());
        shipRepository.save(ship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShipStatus getStatus(Long id) {
        if (!shipRepository.existsById(id)) throw new ResourceNotFoundException("Ship is not found");

        return shipRepository.getById(id).getStatus();
    }

    private boolean isFull(Long id) {
        return shipRepository.countByPortId(id) >= portRepository.getById(id).getCapacity();
    }

    private ShipDto toDto(Ship ship) {
        return new ShipDto(
                ship.getId(),
                ship.getName(),
                ship.getPortId(),
                ship.getStatus(),
                sailorRepository.countByShipId(ship.getId())
        );
    }
}
