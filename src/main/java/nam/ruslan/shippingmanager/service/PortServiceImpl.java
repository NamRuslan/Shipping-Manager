package nam.ruslan.shippingmanager.service;

import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Port;
import nam.ruslan.shippingmanager.repository.PortRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortServiceImpl implements PortService{

    private final PortRepository portRepository;

    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public List<Port> getAll() {
        return portRepository.findAll();
    }

    @Override
    public Optional<Port> findById(Long id) {
        return portRepository.findById(id);
    }

    @Override
    public int getPortCapacity(Long id) {
        if (!portRepository.existsById(id)) throw new ResourceNotFoundException("Port is not found");
        return portRepository.getById(id).getCapacity();
    }
}
