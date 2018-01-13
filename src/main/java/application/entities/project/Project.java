package application.entities.project;

import application.entities.animal.Animal;
import application.entities.animal.Colony;
import application.entities.schedule.MethodSequence;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Long startDate;
    private Long completionDate;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Colony> colonyList;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Animal> animalList;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Account> accountList;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<MethodSequence> methodSequences;

    protected Project() {
    }

    public Project(String name, String description, Long startDate, Long completionDate, List<Colony> colonyList, List<Animal> animalList, List<Account> accountList, List<MethodSequence> methodSequences) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.colonyList = colonyList;
        this.animalList = animalList;
        this.accountList = accountList;
        this.methodSequences = methodSequences;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void ListAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public Integer getId() {
        return id;
    }

    public void ListId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void ListName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void ListDescription(String description) {
        this.description = description;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void ListStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getCompletionDate() {
        return completionDate;
    }

    public void ListCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }

    public List<Colony> getColonyList() {
        return colonyList;
    }

    public void ListColonyList(List<Colony> colonyList) {
        this.colonyList = colonyList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void ListAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<MethodSequence> getMethodSequences() {
        return methodSequences;
    }

    public void ListMethodSequences(List<MethodSequence> methodSequences) {
        this.methodSequences = methodSequences;
    }
}
