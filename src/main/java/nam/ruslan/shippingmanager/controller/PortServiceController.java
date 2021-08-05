package nam.ruslan.shippingmanager.controller;

import io.swagger.annotations.*;
import nam.ruslan.shippingmanager.dto.PortDto;
import nam.ruslan.shippingmanager.exception.ResourceNotFoundException;
import nam.ruslan.shippingmanager.service.PortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"Port service"})
public class PortServiceController {

    private final PortService portService;

    public PortServiceController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping("/ports/{id}/capacity")
    @ApiOperation(
            value = "Get current capacity of port",
            httpMethod = "GET",
            produces = "application/json",
            response = PortDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Port is not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<?> getPortCapacity(
            @ApiParam (value = "Port id", name = "id", required = true, example = "3")
            @PathVariable Long id) {
        PortDto capacity;
        try {
            capacity = portService.getPortCapacity(id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(capacity, HttpStatus.OK);
    }
}
