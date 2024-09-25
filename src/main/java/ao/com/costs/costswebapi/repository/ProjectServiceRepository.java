package ao.com.costs.costswebapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.com.costs.costswebapi.domain.ProjectService;


public interface ProjectServiceRepository extends JpaRepository<ProjectService, Long> {

    List<ProjectService> findByProjectid(Long projectid);
}
