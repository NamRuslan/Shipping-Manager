package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.*;
import nam.ruslan.shippingmanager.dto.ShipStatusDto;
import nam.ruslan.shippingmanager.exception.NoCaptainException;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.model.ShipStatus;
import nam.ruslan.shippingmanager.service.ShipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"Captain's service"})
public class CaptainController {

    private final ShipService shipService;

    public CaptainController(ShipService shipService) {
        this.shipService = shipService;
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
        } catch (NoCaptainException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
