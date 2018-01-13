package application.entities.schedule;

import javax.persistence.*;
import java.util.List;

@Entity
public class MethodSequence {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<MethodSequenceItem> methodSequenceItemList;

    protected MethodSequence() {
    }

    public MethodSequence(String name, String description, List<MethodSequenceItem> methodSequenceItemList) {
        this.name = name;
        this.description = description;
        this.methodSequenceItemList = methodSequenceItemList;
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

    public List<MethodSequenceItem> getMethodSequenceItemList() {
        return methodSequenceItemList;
    }

    public void ListMethodSequenceItemList(List<MethodSequenceItem> methodSequenceItemList) {
        this.methodSequenceItemList = methodSequenceItemList;
    }
}
