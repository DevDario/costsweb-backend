package ao.com.costs.costswebapi.exception;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(Long id){
        super(String.format("No project with ID{%d} was saved !", id));
    }
}
