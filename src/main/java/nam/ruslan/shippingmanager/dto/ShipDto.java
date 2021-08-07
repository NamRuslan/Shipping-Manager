package nam.ruslan.shippingmanager.dto;

import nam.ruslan.shippingmanager.model.ShipStatus;

public class ShipDto {
    private Long id;
    private String name;
    private Long portId;
    private ShipStatus status;
    private int sailorsCount;

    public ShipDto(Long id, String name, Long portId, ShipStatus status, int sailorsCount) {
        this.id = id;
        this.name = name;
        this.portId = portId;
        this.status = status;
        this.sailorsCount = sailorsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public int getSailorsCount() {
        return sailorsCount;
    }

    public void setSailorsCount(int sailorsCount) {
        this.sailorsCount = sailorsCount;
    }
}
