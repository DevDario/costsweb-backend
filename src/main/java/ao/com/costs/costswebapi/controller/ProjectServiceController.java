package ao.com.costs.costswebapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ao.com.costs.costswebapi.domain.ProjectService;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.service.ProjectServiceService;


@RestController
public class ProjectServiceController {
    
    @Autowired
    ProjectServiceService projectServiceService;


    // CREATE's a new service 'inside' a project
    @PostMapping("/project/{projectid}/services/new")
    public ResponseEntity<?> addNewService(@PathVariable Long projectid, @RequestBody ProjectService newService) throws ProjectNotFoundException,Exception{
        return projectServiceService.addNewService(projectid, newService) ;
    }

    
    // GET's all services from all projects
    @GetMapping("/service/all")
    public List<ProjectService> getAllServices() {
        return projectServiceService.AllServices();
    }

    // GET's all services from a single project
    @GetMapping("/project/{projectid}/service/services")
    public List<ProjectService> getServices(@PathVariable Long projectid) {
        return projectServiceService.getServicesFromProject(projectid);
    }
    
    // DELETE's a service
    @DeleteMapping("/project/{projectid}/service/services/{serviceid}")
    public ResponseEntity<?> deleteService(@PathVariable Long projectid, @PathVariable Long serviceid) throws ProjectNotFoundException,Exception{
        return projectServiceService.deleteService(projectid, serviceid);
    }

}
