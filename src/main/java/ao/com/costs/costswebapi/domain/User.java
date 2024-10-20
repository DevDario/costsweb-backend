package ao.com.costs.costswebapi.domain;

import ao.com.costs.costswebapi.enums.AuthProvider;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "auth_provider")
    private AuthProvider authProvider;

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Project> projects;
}
