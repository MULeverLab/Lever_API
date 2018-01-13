package application.entities.schedule;

import application.entities.project.Competency;

import javax.persistence.*;
import java.util.List;

@Entity
public class MethodSequenceItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer methodType;
    private Integer startOffList;
    private Integer frequency;
    private Integer repetitions;
    private Integer window;
    @OneToOne(cascade = {CascadeType.ALL})
    private Competency competency;
    private Integer level;

    protected MethodSequenceItem() {
    }

    public MethodSequenceItem(Integer methodType, Integer startOffList, Integer frequency, Integer repetitions, Integer window, Competency competency, Integer level) {
        this.methodType = methodType;
        this.startOffList = startOffList;
        this.frequency = frequency;
        this.repetitions = repetitions;
        this.window = window;
        this.competency = competency;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public Integer getStartOffList() {
        return startOffList;
    }

    public void setStartOffList(Integer startOffList) {
        this.startOffList = startOffList;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Integer getWindow() {
        return window;
    }

    public void setWindow(Integer window) {
        this.window = window;
    }

    public Competency getCompetency() {
        return competency;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
