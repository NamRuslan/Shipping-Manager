package nam.ruslan.shippingmanager.model;
package nam.ruslan.shippingmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "sailors")
public class Sailor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column
    private Long shipId;

    public Sailor() {
    }

    public Sailor(Long id, String name, Rank rank, Long shipId) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.shipId = shipId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
