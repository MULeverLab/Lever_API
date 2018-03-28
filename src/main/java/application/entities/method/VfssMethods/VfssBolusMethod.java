package application.entities.method.VfssMethods;

import javax.persistence.*;

@Entity
public class VfssBolusMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer onsetFrame;
    private Integer trialNumber;
    private Long timeGrabbedFrame;
    private Long firstReviewer;
    private Long secondReviewer;
    private Integer methodRunId;

    //averages would have a function that updates them
    private Long average;
    private Long combindedAverage;

    public VfssBolusMethod() {
    }

    public VfssBolusMethod(Integer onsetFrame, Integer trialNumber, Long timeGrabbedFrame, Long firstReviewer, Long secondReviewer, Integer methodRunId, Long average, Long combindedAverage) {
        this.onsetFrame = onsetFrame;
        this.trialNumber = trialNumber;
        this.timeGrabbedFrame = timeGrabbedFrame;
        this.firstReviewer = firstReviewer;
        this.secondReviewer = secondReviewer;
        this.methodRunId = methodRunId;
        this.average = average;
        this.combindedAverage = combindedAverage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOnsetFrame() {
        return onsetFrame;
    }

    public void setOnsetFrame(Integer onsetFrame) {
        this.onsetFrame = onsetFrame;
    }

    public Integer getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    public Long getTimeGrabbedFrame() {
        return timeGrabbedFrame;
    }

    public void setTimeGrabbedFrame(Long timeGrabbedFrame) {
        this.timeGrabbedFrame = timeGrabbedFrame;
    }

    public Long getFirstReviewer() {
        return firstReviewer;
    }

    public void setFirstReviewer(Long firstReviewer) {
        this.firstReviewer = firstReviewer;
    }

    public Long getSecondReviewer() {
        return secondReviewer;
    }

    public void setSecondReviewer(Long secondReviewer) {
        this.secondReviewer = secondReviewer;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Long getCombindedAverage() {
        return combindedAverage;
    }

    public void setCombindedAverage(Long combindedAverage) {
        this.combindedAverage = combindedAverage;
    }


    public Integer getMethodRunId() {
        return methodRunId;
    }

    public void setMethodRunId(Integer methodRunId) {
        this.methodRunId = methodRunId;
    }
}
