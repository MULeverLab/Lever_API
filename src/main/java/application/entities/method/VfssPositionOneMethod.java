package application.entities.method;

import javax.persistence.*;

@Entity
public class VfssPositionOneMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    //Trial Number corresponds to 1-5
    private Integer trialNumber;
    //Reviewer type corresponds to R1, R2, Consensus
    private Integer reviewerType;
    private Long firstSwallowOnsetFrame;
    private Long pttEndFrame;
    private Long secondSwallowOnsetFrame;
    private Long jawCyclesPerSwallow;
    private Long twoSecFromSwallowOnsetFrame;
    private Long swallowsPerTwoSeconds;
    private Long lickOnsetFrame;
    private Long lickEndFrame;
    private Long lickRate;
    private Integer methodRunId;



    @OneToOne (cascade = {CascadeType.ALL})
    private VfssBolusMethod vfssBolusMethod;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod vfssPositionTwoMethod;

    public VfssPositionOneMethod() {
    }

    public VfssPositionOneMethod(Integer trialNumber, Integer reviewerType, Long firstSwallowOnsetFrame, Long pttEndFrame, Long secondSwallowOnsetFrame, Long jawCyclesPerSwallow, Long twoSecFromSwallowOnsetFrame, Long swallowsPerTwoSeconds, Long lickOnsetFrame, Long lickEndFrame, Long lickRate, Integer methodRunId, VfssBolusMethod vfssBolusMethod, VfssPositionTwoMethod vfssPositionTwoMethod) {
        this.trialNumber = trialNumber;
        this.reviewerType = reviewerType;
        this.firstSwallowOnsetFrame = firstSwallowOnsetFrame;
        this.pttEndFrame = pttEndFrame;
        this.secondSwallowOnsetFrame = secondSwallowOnsetFrame;
        this.jawCyclesPerSwallow = jawCyclesPerSwallow;
        this.twoSecFromSwallowOnsetFrame = twoSecFromSwallowOnsetFrame;
        this.swallowsPerTwoSeconds = swallowsPerTwoSeconds;
        this.lickOnsetFrame = lickOnsetFrame;
        this.lickEndFrame = lickEndFrame;
        this.lickRate = lickRate;
        this.methodRunId = methodRunId;
        this.vfssBolusMethod = vfssBolusMethod;
        this.vfssPositionTwoMethod = vfssPositionTwoMethod;
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

    public Long getSecondSwallowOnsetFrame() {
        return secondSwallowOnsetFrame;
    }

    public void setSecondSwallowOnsetFrame(Long secondSwallowOnsetFrame) {
        this.secondSwallowOnsetFrame = secondSwallowOnsetFrame;
    }

    public Long getJawCyclesPerSwallow() {
        return jawCyclesPerSwallow;
    }

    public void setJawCyclesPerSwallow(Long jawCyclesPerSwallow) {
        this.jawCyclesPerSwallow = jawCyclesPerSwallow;
    }

    public Long getTwoSecFromSwallowOnsetFrame() {
        return twoSecFromSwallowOnsetFrame;
    }

    public void setTwoSecFromSwallowOnsetFrame(Long twoSecFromSwallowOnsetFrame) {
        this.twoSecFromSwallowOnsetFrame = twoSecFromSwallowOnsetFrame;
    }

    public Long getSwallowsPerTwoSeconds() {
        return swallowsPerTwoSeconds;
    }

    public void setSwallowsPerTwoSeconds(Long swallowsPerTwoSeconds) {
        this.swallowsPerTwoSeconds = swallowsPerTwoSeconds;
    }

    public Long getLickOnsetFrame() {
        return lickOnsetFrame;
    }

    public void setLickOnsetFrame(Long lickOnsetFrame) {
        this.lickOnsetFrame = lickOnsetFrame;
    }

    public Long getLickEndFrame() {
        return lickEndFrame;
    }

    public void setLickEndFrame(Long lickEndFrame) {
        this.lickEndFrame = lickEndFrame;
    }

    public Long getLickRate() {
        return lickRate;
    }

    public void setLickRate(Long lickRate) {
        this.lickRate = lickRate;
    }

    public VfssBolusMethod getVfssBolusMethod() {
        return vfssBolusMethod;
    }

    public void setVfssBolusMethod(VfssBolusMethod vfssBolusMethod) {
        this.vfssBolusMethod = vfssBolusMethod;
    }

    public VfssPositionTwoMethod getVfssPositionTwoMethod() {
        return vfssPositionTwoMethod;
    }

    public void setVfssPositionTwoMethod(VfssPositionTwoMethod vfssPositionTwoMethod) {
        this.vfssPositionTwoMethod = vfssPositionTwoMethod;
    }

    public Integer getMethodRunId() {
        return methodRunId;
    }

    public void setMethodRunId(Integer methodRunId) {
        this.methodRunId = methodRunId;
    }
}
