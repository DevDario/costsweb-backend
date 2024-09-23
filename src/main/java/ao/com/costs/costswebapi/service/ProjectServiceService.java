package ao.com.costs.costswebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.ProjectService;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.repository.ProjectRepository;
import ao.com.costs.costswebapi.repository.ProjectServiceRepository;

@Service
public class ProjectServiceService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectServiceRepository projectServiceRepository;
    
    // ADD's a new service to a project
    public ResponseEntity<?> addNewService(@PathVariable Long projectID, @RequestBody ProjectService newService) throws ProjectNotFoundException{

        projectRepository.findById(projectID).orElseThrow(()-> new ProjectNotFoundException(projectID));

        projectServiceRepository.save(newService);

        return ResponseEntity.ok().build();

    }

}
