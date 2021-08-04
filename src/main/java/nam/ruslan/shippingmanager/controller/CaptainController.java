package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import nam.ruslan.shippingmanager.dto.ShipStatusDto;
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
    @ApiOperation(
            value = "Change status of ship",
            httpMethod = "PATCH"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Ship is not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> updateShipStatus(@PathVariable Long id, @RequestBody ShipStatusDto status) {

        try {
            System.out.println(status.getStatus());
            shipService.updateStatus(id, status.getStatus());
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
