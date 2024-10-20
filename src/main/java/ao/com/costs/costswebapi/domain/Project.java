package ao.com.costs.costswebapi.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import ao.com.costs.costswebapi.enums.Category;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="project")
public class Project {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="budget", nullable = false)
    private float budget;

    @Column(name="usedBudget", nullable = false)
    private float usedBudget;

    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="deadline")
    private Date deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProjectService> services;
}
