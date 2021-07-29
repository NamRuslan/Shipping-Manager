package nam.ruslan.shippingmanager.repository;

import nam.ruslan.shippingmanager.model.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
}
