package nam.ruslan.shippingmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "ships")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long portId;

    @Column
    @Enumerated(EnumType.STRING)
    private ShipStatus status;

    @Column
    private boolean hasCaptain;

    public Ship() {
    }

    public Ship(Long id, String name, Long portId, ShipStatus status, boolean hasCaptain) {
        this.id = id;
        this.name = name;
        this.portId = portId;
        this.status = status;
        this.hasCaptain = hasCaptain;
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

    public boolean isHasCaptain() {
        return hasCaptain;
    }

    public void setHasCaptain(boolean hasCaptain) {
        this.hasCaptain = hasCaptain;
    }
}
