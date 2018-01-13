package application.entities.method;

import javax.persistence.*;

@Entity
public class VfssPositionTwoMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    //Trial Number corresponds to 1-5
    private Integer trialNumber;
    //Reviewer type corresponds to R1, R2, Consensus
    private Integer reviewerType;
    private Long firstSwallowOnsetFrame;
    private Long pttEndFrame;
    private Long ettEndFrame;
    private Long secondSwallowOnsetFrame;
    private Long esophagusEmptiesPriorToSecondSwallow;
    private Long numberOfSwallowsToClearEsophagus;
    private boolean swallowInhibition;
    private Integer methodRunId;



    @OneToOne (cascade = {CascadeType.ALL})
    private VfssBolusMethod vfssBolusMethod;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionOneMethod vfssPositionOneMethod;

    public VfssPositionTwoMethod() {
    }

    public VfssPositionTwoMethod(Integer trialNumber, Integer reviewerType, Long firstSwallowOnsetFrame, Long pttEndFrame, Long ettEndFrame, Long secondSwallowOnsetFrame, Long esophagusEmptiesPriorToSecondSwallow, Long numberOfSwallowsToClearEsophagus, boolean swallowInhibition, Integer methodRunId, VfssBolusMethod vfssBolusMethod, VfssPositionOneMethod vfssPositionOneMethod) {
        this.trialNumber = trialNumber;
        this.reviewerType = reviewerType;
        this.firstSwallowOnsetFrame = firstSwallowOnsetFrame;
        this.pttEndFrame = pttEndFrame;
        this.ettEndFrame = ettEndFrame;
        this.secondSwallowOnsetFrame = secondSwallowOnsetFrame;
        this.esophagusEmptiesPriorToSecondSwallow = esophagusEmptiesPriorToSecondSwallow;
        this.numberOfSwallowsToClearEsophagus = numberOfSwallowsToClearEsophagus;
        this.swallowInhibition = swallowInhibition;
        this.methodRunId = methodRunId;
        this.vfssBolusMethod = vfssBolusMethod;
        this.vfssPositionOneMethod = vfssPositionOneMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrialNumber() {
        return trialNumber;
    }

    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    public Integer getReviewerType() {
        return reviewerType;
    }

    public void setReviewerType(Integer reviewerType) {
        this.reviewerType = reviewerType;
    }

    public Long getFirstSwallowOnsetFrame() {
        return firstSwallowOnsetFrame;
    }

    public void setFirstSwallowOnsetFrame(Long firstSwallowOnsetFrame) {
        this.firstSwallowOnsetFrame = firstSwallowOnsetFrame;
    }

    public Long getPttEndFrame() {
        return pttEndFrame;
    }

    public void setPttEndFrame(Long pttEndFrame) {
        this.pttEndFrame = pttEndFrame;
    }

    public Long getEttEndFrame() {
        return ettEndFrame;
    }

    public void setEttEndFrame(Long ettEndFrame) {
        this.ettEndFrame = ettEndFrame;
    }

    public Long getSecondSwallowOnsetFrame() {
        return secondSwallowOnsetFrame;
    }

    public void setSecondSwallowOnsetFrame(Long secondSwallowOnsetFrame) {
        this.secondSwallowOnsetFrame = secondSwallowOnsetFrame;
    }

    public Long getEsophagusEmptiesPriorToSecondSwallow() {
        return esophagusEmptiesPriorToSecondSwallow;
    }

    public void setEsophagusEmptiesPriorToSecondSwallow(Long esophagusEmptiesPriorToSecondSwallow) {
        this.esophagusEmptiesPriorToSecondSwallow = esophagusEmptiesPriorToSecondSwallow;
    }

    public Long getNumberOfSwallowsToClearEsophagus() {
        return numberOfSwallowsToClearEsophagus;
    }

    public void setNumberOfSwallowsToClearEsophagus(Long numberOfSwallowsToClearEsophagus) {
        this.numberOfSwallowsToClearEsophagus = numberOfSwallowsToClearEsophagus;
    }

    public boolean isSwallowInhibition() {
        return swallowInhibition;
    }

    public void setSwallowInhibition(boolean swallowInhibition) {
        this.swallowInhibition = swallowInhibition;
    }

    public Integer getMethodRunId() {
        return methodRunId;
    }

    public void setMethodRunId(Integer methodRunId) {
        this.methodRunId = methodRunId;
    }

    public VfssBolusMethod getVfssBolusMethod() {
        return vfssBolusMethod;
    }

    public void setVfssBolusMethod(VfssBolusMethod vfssBolusMethod) {
        this.vfssBolusMethod = vfssBolusMethod;
    }

    public VfssPositionOneMethod getVfssPositionOneMethod() {
        return vfssPositionOneMethod;
    }

    public void setVfssPositionOneMethod(VfssPositionOneMethod vfssPositionOneMethod) {
        this.vfssPositionOneMethod = vfssPositionOneMethod;
    }
}
