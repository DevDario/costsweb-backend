package ao.com.costs.costswebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.Project;
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
    public ResponseEntity<?> addNewService(@PathVariable Long projectid, @RequestBody ProjectService newService) throws ProjectNotFoundException,Exception{

        Project project = projectRepository.findById(projectid).orElseThrow(()-> new ProjectNotFoundException(projectid));

        if(newService.getBudget() > project.getBudget()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("There's not enough budget for this service");
        }

        projectServiceRepository.save(newService);

        project.setNumServices(project.getNumServices() + 1);
        project.setBudget(project.getBudget() - newService.getBudget());
        project.setUsedBudget(project.getUsedBudget() + newService.getBudget());

        projectRepository.save(project);

        return ResponseEntity
                .ok()
                .body(newService.getName() + " service added !");

    }

}
