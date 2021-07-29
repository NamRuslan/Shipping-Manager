package nam.ruslan.shippingmanager.controller;

import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaptainController {

    private final ShipService shipService;

    public CaptainController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ships/{id}/status")
    public ResponseEntity<?> getShipStatus(@PathVariable Long id) {

        ShipStatus status;
        try {
             status = shipService.getStatus(id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PatchMapping("/ships/{id}/status")
    public ResponseEntity<?> updateShipStatus(@PathVariable Long id, @RequestBody ShipStatus status) {

        try {
            shipService.updateStatus(id, status);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
