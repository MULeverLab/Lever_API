package schedule;

import project.Competency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.Set;

@Entity
public class MethodSequence {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Set<MethodSequenceItem> methodSequenceItemSet;

    public MethodSequence() {
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
