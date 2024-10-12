package ao.com.costs.costswebapi.service;

import java.util.List;

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
    public ResponseEntity<String> addNewService(@PathVariable Long projectid, @RequestBody ProjectService newService) throws ProjectNotFoundException,Exception{

        Project project = projectRepository.findById(projectid).orElseThrow(()-> new ProjectNotFoundException(projectid));

        if(newService.getBudget() > project.getBudget()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("There's not enough budget for this service");
        }

        newService.setProject(project);

        projectServiceRepository.save(newService);

        project.setBudget(project.getBudget() - newService.getBudget());
        project.setUsedBudget(project.getUsedBudget() + newService.getBudget());

        projectRepository.save(project);

        return ResponseEntity
                .ok()
                .body(newService.getName() + " service added !");

    }

    // GET's all services from a project
    public List<ProjectService> getServicesFromProject(@PathVariable Long projectid){
        return projectServiceRepository.findByProject_id(projectid);
    }

    // GET's all services from all projects
    public List<ProjectService> AllServices(){
        return projectServiceRepository.findAll();
    }

    // DELETE's a service
    public ResponseEntity<String> deleteService(@PathVariable Long projectid,@PathVariable Long serviceid) throws ProjectNotFoundException, Exception{
        
        Project project = projectRepository.findById(projectid).orElseThrow(()-> new ProjectNotFoundException(projectid));

        ProjectService service = projectServiceRepository.findById(serviceid).orElseThrow(
            () -> new Exception(String.format("There's no service associated with id of %s",serviceid))
        );

        project.setBudget(project.getBudget() + service.getBudget());
        project.setUsedBudget(project.getUsedBudget() - service.getBudget());

        projectServiceRepository.delete(service);
        projectRepository.save(project);

        return ResponseEntity
                .ok()
                .body(service.getName() + " service was deleted from your " + project.getName() + " project !");
    }
}
