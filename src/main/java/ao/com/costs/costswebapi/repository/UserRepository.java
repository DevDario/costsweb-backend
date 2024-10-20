package ao.com.costs.costswebapi.repository;

import ao.com.costs.costswebapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
