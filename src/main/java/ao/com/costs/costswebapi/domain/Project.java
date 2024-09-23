package ao.com.costs.costswebapi.domain;

import java.util.Date;

import ao.com.costs.costswebapi.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name="name")
    private String name;

    @Column(name="budget")
    private float budget;

    @Column(name="usedBudget")
    private float usedBudget;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;

    @Column(name="numServices")
    private Long numServices;

    @Column(name="createdAt")
    private Date createdAt;

    @Column(name="deadline")
    private Date deadline;
}
