package application.method;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VfssMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    //Position will either be 1 or 2
    private Integer position;
    //Trial Number corresponds to 1-5
    private Integer trialNumber;
    //Reviewer type corresponds to R1, R2, Consensus
    private Integer reviewerType;
    private Long firstSwallowOnsetFrame;
    private Long pttEndFrame;
    private Long ettEndFrame;
    private Long secondSwallowOnsetFrame;
    private Long swallowsPer2Seconds;
    private Long lickOnsetFrame;
    private Long lickEndFrame;
    private Long lickRate;




    public VfssMethod() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
