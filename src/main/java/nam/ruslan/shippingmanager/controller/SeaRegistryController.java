package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.*;
import nam.ruslan.shippingmanager.model.Port;
import nam.ruslan.shippingmanager.model.Ship;
import nam.ruslan.shippingmanager.model.ShipStatus;
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
    @ApiOperation(
            value = "Get all ports info",
            httpMethod = "GET",
            produces = "application/json",
            response = Port.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<List<Port>> getAllPorts() {
        List<Port> list = portService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ships")
    @ApiOperation(
            value = "Get all ships info with filter",
            httpMethod = "GET",
            produces = "application/json",
            response = Ship.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error"),
    })
    public ResponseEntity<List<Ship>> getAllShips(
            @ApiParam(value = "String: ship status", name = "status")
            @RequestParam(name = "status", required = false) ShipStatus status) {

        List<Ship> list;

        if (status != null) {
            list = shipService.getAll(status);
        } else {
            list = shipService.getAll();
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/ships")
    @ApiOperation(
            value = "Add new ship",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ship successfully added"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> addShip(
            @ApiParam(value = "JSON - structured ship object", name = "ship", required = true)
            @RequestBody Ship ship) {

        shipService.save(ship);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/ships/{id}")
    @ApiOperation(
            value = "Delete ship",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Sever error")
    })
    public ResponseEntity<?> deleteShip(
            @ApiParam(value = "Ship id", name = "id", required = true)
            @PathVariable Long id) {

        shipService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
