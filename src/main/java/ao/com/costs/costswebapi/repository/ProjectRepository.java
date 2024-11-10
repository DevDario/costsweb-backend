package ao.com.costs.costswebapi.repository;

import ao.com.costs.costswebapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import ao.com.costs.costswebapi.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser(User user);

    Optional<Project> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);
}
