package ao.com.costs.costswebapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.com.costs.costswebapi.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
