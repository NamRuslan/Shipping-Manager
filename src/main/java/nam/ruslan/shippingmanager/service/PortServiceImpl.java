package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.dto.PortDto;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Port;
import nam.ruslan.shippingmanager.repository.PortRepository;
import nam.ruslan.shippingmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Service
public class PortServiceImpl implements PortService{

    private final PortRepository portRepository;
    private final ShipRepository shipRepository;

    public PortServiceImpl(PortRepository portRepository, ShipRepository shipRepository) {
        this.portRepository = portRepository;
        this.shipRepository = shipRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Port> getAll() {
        return portRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Port> findById(Long id) {
        return portRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortDto getPortCapacity(Long id) {
        if (!portRepository.existsById(id)) throw new ResourceNotFoundException("Port is not found");

        int maxCap = portRepository.getById(id).getCapacity();
        int occupiedCap = shipRepository.countByPortId(id);

        return new PortDto(maxCap, occupiedCap, maxCap - occupiedCap);
    }
}
