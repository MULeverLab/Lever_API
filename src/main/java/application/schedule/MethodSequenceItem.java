package application.schedule;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MethodSequenceItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer methodType;
    private Integer startOffset;
    private Integer frequency;
    private Integer repetitions;
    private Integer window;
    @OneToMany
    private Set<MsiCompetencyBridge> msiCompetencyBridgeSet;

    protected MethodSequenceItem() {
    }

    public MethodSequenceItem(Integer methodType, Integer startOffset, Integer frequency, Integer repetitions, Integer window, Set<MsiCompetencyBridge> msiCompetencyBridgeSet) {
        this.methodType = methodType;
        this.startOffset = startOffset;
        this.frequency = frequency;
        this.repetitions = repetitions;
        this.window = window;
        this.msiCompetencyBridgeSet = msiCompetencyBridgeSet;
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

    public Integer getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(Integer startOffset) {
        this.startOffset = startOffset;
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

    public Set<MsiCompetencyBridge> getMsiCompetencyBridgeSet() {
        return msiCompetencyBridgeSet;
    }

    public void setMsiCompetencyBridgeSet(Set<MsiCompetencyBridge> msiCompetencyBridgeSet) {
        this.msiCompetencyBridgeSet = msiCompetencyBridgeSet;
    }
}
