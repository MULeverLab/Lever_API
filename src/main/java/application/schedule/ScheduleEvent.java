package application.schedule;

import application.animal.Animal;
import application.method.Method;
import application.project.Project;
import application.project.User;

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
    private User user;
    private Long addedDate;
    private Long dueDate;
    private Long completionDate;

    protected ScheduleEvent() {
    }

    public ScheduleEvent(Method method, Project project, Animal animal, User user, Long addedDate, Long dueDate, Long completionDate) {
        this.method = method;
        this.project = project;
        this.animal = animal;
        this.user = user;
        this.addedDate = addedDate;
        this.dueDate = dueDate;
        this.completionDate = completionDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }
}
