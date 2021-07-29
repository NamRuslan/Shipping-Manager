package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService{

    private final ShipRepository shipRepository;

    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    @Override
    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public void save(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void updateStatus(Long id, ShipStatus status) {
        if (!shipRepository.existsById(id)) throw new ResourceNotFoundException("Ship is not found");

        Ship ship = shipRepository.getById(id);
        ship.setStatus(status);
    }

    @Override
    public ShipStatus getStatus(Long id) {
        if (!shipRepository.existsById(id)) throw new ResourceNotFoundException("Ship is not found");

        return shipRepository.getById(id).getStatus();
    }
}
