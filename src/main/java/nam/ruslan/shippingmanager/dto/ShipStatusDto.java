package nam.ruslan.shippingmanager.dto;

import nam.ruslan.shippingmanager.model.ShipStatus;

public class ShipStatusDto {

    private ShipStatus status;
    private Long portId;

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }
}
