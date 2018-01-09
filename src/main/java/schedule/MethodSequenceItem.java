package schedule;

import project.Competency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class MethodSequenceItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private int methodType;
    private int startOffset;
    private int frequency;
    private int repetitions;
    private int window;
    private Map<Competency, Integer> competencyLevelMap;

    public MethodSequenceItem() {
    }

    public MethodSequenceItem(int methodType, int startOffset, int frequency, int repetitions, int window, Map<Competency, Integer> competencyLevelMap) {
        this.methodType = methodType;
        this.startOffset = startOffset;
        this.frequency = frequency;
        this.repetitions = repetitions;
        this.window = window;
        this.competencyLevelMap = competencyLevelMap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public Map<Competency, Integer> getCompetencyLevelMap() {
        return competencyLevelMap;
    }

    public void setCompetencyLevelMap(Map<Competency, Integer> competencyLevelMap) {
        this.competencyLevelMap = competencyLevelMap;
    }
}
