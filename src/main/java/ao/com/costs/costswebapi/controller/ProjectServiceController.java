package ao.com.costs.costswebapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.ProjectService;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.service.ProjectServiceService;

public class ProjectServiceController {
    
    @Autowired
    ProjectServiceService projectServiceService;

    @PostMapping("/project/{id}/services/new")
    public ResponseEntity<?> addNewService(@PathVariable Long projectID, @RequestBody ProjectService newService) throws ProjectNotFoundException{
        return projectServiceService.addNewService(projectID, newService) ;
    }

}
