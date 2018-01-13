package application.entities.schedule;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MethodSequence {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<MethodSequenceItem> methodSequenceItemSet;

    protected MethodSequence() {
    }

    public MethodSequence(String name, String description, Set<MethodSequenceItem> methodSequenceItemSet) {
        this.name = name;
        this.description = description;
        this.methodSequenceItemSet = methodSequenceItemSet;
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

    public Set<MethodSequenceItem> getMethodSequenceItemSet() {
        return methodSequenceItemSet;
    }

    public void setMethodSequenceItemSet(Set<MethodSequenceItem> methodSequenceItemSet) {
        this.methodSequenceItemSet = methodSequenceItemSet;
    }
}
