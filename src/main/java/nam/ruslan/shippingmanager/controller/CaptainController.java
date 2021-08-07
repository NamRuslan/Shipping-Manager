package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.*;
import nam.ruslan.shippingmanager.dto.ShipStatusDto;
import nam.ruslan.shippingmanager.exception.CaptainException;
import nam.ruslan.shippingmanager.exception.PortException;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.Sailor;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.service.SailorService;
import nam.ruslan.shippingmanager.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"Captain's service"})
public class CaptainController {

    private final ShipService shipService;
    private final SailorService sailorService;

    public CaptainController(ShipService shipService, SailorService sailorService) {
        this.shipService = shipService;
        this.sailorService = sailorService;
    }

    @GetMapping("/ships/{id}/status")
    @ApiOperation(
            value = "Get status of ship",
            httpMethod = "GET",
            produces = "application/json",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> getShipStatus(
            @ApiParam(value = "Ship id", name = "id", required = true, example = "3")
            @PathVariable Long id) {

        ShipStatus status;
        try {
             status = shipService.getStatus(id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PatchMapping("/ships/{id}/status")
    @ApiOperation(
            value = "Change status of ship",
            httpMethod = "PATCH"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Ship is not allowed to sail: no captain"),
            @ApiResponse(code = 404, message = "Ship is not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> updateShipStatus(
            @ApiParam(value = "Ship id", name = "id", required = true, example = "3")
            @PathVariable Long id,
            @ApiParam(value = "Ship status", name = "status", required = true, example = "PORT")
            @RequestBody ShipStatusDto status
    ){

        try {
            shipService.updateStatus(id, status);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CaptainException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/ships/{shipId}/sailors/{sailorId}")
    @ApiOperation(
            value = "Set sailor to the ship",
            httpMethod = "PATCH"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Check captain or port"),
            @ApiResponse(code = 404, message = "Sailor or ship not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> setSailorOn(
            @ApiParam(value = "Ship id", name = "ship id", required = true, example = "3")
            @PathVariable Long shipId,
            @ApiParam(value = "Sailor id", name = "sailor id", required = true, example = "3")
            @PathVariable Long sailorId) {

        try {
            sailorService.setSailorToShip(shipId, sailorId);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (CaptainException | PortException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/ships/{shipId}/sailors/{sailorId}")
    @ApiOperation(
            value = "Set sailor off the ship",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Check port"),
            @ApiResponse(code = 404, message = "Ship or sailor not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> setSailorOff(
            @ApiParam(value = "Ship id", name = "ship id", required = true, example = "3")
            @PathVariable Long shipId,
            @ApiParam(value = "Sailor id", name = "sailor id", required = true, example = "3")
            @PathVariable Long sailorId) {

        try {
            sailorService.setSailorOffShip(shipId, sailorId);

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (PortException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ships/{shipId}/sailors")
    @ApiOperation(
            value = "Register new sailor",
            httpMethod = "POST"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> addSailor(@PathVariable Long shipId, @RequestBody Sailor sailor) {

        sailorService.registerNewSailor(sailor);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
