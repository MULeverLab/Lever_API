package application.project;

import application.animal.Animal;
import application.animal.Colony;
import application.schedule.MethodSequence;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Long startDate;
    private Long completionDate;
    @OneToMany
    private Set<Colony> colonySet;
    @OneToMany
    private Set<Animal> animalSet;
    @OneToMany
    private Set<Account> accountSet;
    @OneToMany
    private Set<MethodSequence> methodSequences;

    protected Project() {
    }

    public Project(String name, String description, Long startDate, Long completionDate, Set<Colony> colonySet, Set<Animal> animalSet, Set<Account> accountSet, Set<MethodSequence> methodSequences) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.colonySet = colonySet;
        this.animalSet = animalSet;
        this.accountSet = accountSet;
        this.methodSequences = methodSequences;
    }

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public void setAnimalSet(Set<Animal> animalSet) {
        this.animalSet = animalSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }

    public Set<Colony> getColonySet() {
        return colonySet;
    }

    public void setColonySet(Set<Colony> colonySet) {
        this.colonySet = colonySet;
    }

    public Set<Account> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<Account> accountSet) {
        this.accountSet = accountSet;
    }

    public Set<MethodSequence> getMethodSequences() {
        return methodSequences;
    }

    public void setMethodSequences(Set<MethodSequence> methodSequences) {
        this.methodSequences = methodSequences;
    }
}
