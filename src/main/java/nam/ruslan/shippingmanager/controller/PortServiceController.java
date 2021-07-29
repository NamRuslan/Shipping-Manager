package nam.ruslan.shippingmanager.controller;

import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.service.PortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortServiceController {

    private final PortService portService;

    public PortServiceController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping("/ports/{id}/capacity")
    public ResponseEntity<?> getPortCapacity(@PathVariable Long id) {
        int capacity;
        try {
            capacity = portService.getPortCapacity(id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(capacity, HttpStatus.OK);
    }
}
