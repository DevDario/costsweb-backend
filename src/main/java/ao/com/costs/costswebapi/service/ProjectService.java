package ao.com.costs.costswebapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import ao.com.costs.costswebapi.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ao.com.costs.costswebapi.domain.Project;
import ao.com.costs.costswebapi.exception.ProjectNotFoundException;
import ao.com.costs.costswebapi.repository.ProjectRepository;

@Service
@AllArgsConstructor
public class ProjectService {

    ProjectRepository projectRepository;
    UserService userService;

    // CREATE a new Project
    public ResponseEntity<String> createProjectForUser(@RequestBody Project project, String email) throws Exception{

        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserByEmail(email));

        if(optionalUser.isPresent()){
            User loggedUser = optionalUser.get();
            project.setServices(List.of());
            project.setCreatedAt(LocalDateTime.now());
            project.setUser(loggedUser);

            this.projectRepository.save(project);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .build();
        }else{
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // GET all projects registered
    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    // GET all projects from User
    public List<Project> findAllProjectsFromUser(String email) throws Exception{
        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserByEmail(email));

        if(optionalUser.isPresent()){
            User loggedUser = optionalUser.get();
            return this.projectRepository.findByUser(loggedUser);
        }else{
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // GET one project from a User
    public Project findSingleProjectFromUser(Long id, String email) throws Exception,ProjectNotFoundException{

        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserByEmail(email));

        if(optionalUser.isPresent()){
            User loggeduser = optionalUser.get();
            return projectRepository.findByIdAndUser(id,loggeduser).orElseThrow(() -> new ProjectNotFoundException(id));
        }else{
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // EDIT's(PUT) a project
    public ResponseEntity<String> editProjectFromUser(@PathVariable Long id, @RequestBody Project projectDetails, String email) throws Exception {
        
        Project project = this.findSingleProjectFromUser(id, email);

        project.setName(projectDetails.getName());
        project.setBudget(projectDetails.getBudget());
        project.setCategory(projectDetails.getCategory());

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    // DELETE a project
    @Transactional
    public ResponseEntity<String> deleteProjectFromUser(@PathVariable Long id, String email) throws Exception {

        Optional<User> optionalUser = Optional.ofNullable(this.userService.getUserByEmail(email));

        if(optionalUser.isPresent()){
            User loggedUser = optionalUser.get();
            this.projectRepository.deleteByIdAndUser(id, loggedUser);

            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }else{
            throw new RuntimeException("User not found with email: " + email);
        }

    }
}
