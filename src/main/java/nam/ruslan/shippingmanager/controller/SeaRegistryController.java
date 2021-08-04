package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.Api;
import nam.ruslan.shippingmanager.model.Port;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.service.PortService;
import nam.ruslan.shippingmanager.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Sea Registry Service"})
public class SeaRegistryController {

    private final PortService portService;
    private final ShipService shipService;

    public SeaRegistryController(PortService portService, ShipService shipService) {
        this.portService = portService;
        this.shipService = shipService;
    }

    @GetMapping("/ports")
    public ResponseEntity<?> getAllPorts() {
        List<Port> list = portService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ships")
    public ResponseEntity<?> getAllShips(@RequestParam(name = "status", required = false) String status) {

        List<Ship> list;

        if (status != null && !status.isBlank()) {
            list = shipService.getAll(status);
        } else {
            list = shipService.getAll();
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/ships")
    public ResponseEntity<?> addShip(@RequestBody Ship ship) {
        shipService.save(ship);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/ships/{id}")
    public ResponseEntity<?> deleteShip(@PathVariable Long id) {
        shipService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
