package nam.ruslan.shippingmanager.repository;

import nam.ruslan.shippingmanager.model.Sailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SailorRepository extends JpaRepository<Sailor, Long> {

    int countByShipId(Long shipId);
}
