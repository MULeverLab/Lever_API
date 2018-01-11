package application.schedule;

import application.animal.Animal;
import application.method.Method;
import application.project.Project;
import application.project.Account;

import javax.persistence.*;

@Entity
public class ScheduleEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Method method;
    @OneToOne
    private Project project;
    @OneToOne
    private Animal animal;
    @OneToOne
    private Account account;
    private Long addedDate;
    private Long dueDate;
    private Long claimDate;
    private Long completionDate;
    private String description;

    protected ScheduleEvent() {
    }

    public ScheduleEvent(Method method, Project project, Animal animal, Account account, Long addedDate, Long dueDate, Long claimDate, Long completionDate, String description) {
        this.method = method;
        this.project = project;
        this.animal = animal;
        this.account = account;
        this.addedDate = addedDate;
        this.dueDate = dueDate;
        this.claimDate = claimDate;
        this.completionDate = completionDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Long addedDate) {
        this.addedDate = addedDate;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Long getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Long claimDate) {
        this.claimDate = claimDate;
    }

    public Long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
