package animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mouse {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer cageId;
    private Integer coatColor;
    private Integer leftEarPunches;
    private Integer rightEarPunches;

    protected Mouse() {

    }

    public Mouse(int cageId, int coatColor, int leftEarPunches, int rightEarPunches) {
        this.cageId = cageId;
        this.coatColor = coatColor;
        this.leftEarPunches = leftEarPunches;
        this.rightEarPunches = rightEarPunches;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public int getCageId() {
        return cageId;
    }

    public void setCageId(int cageId) {
        this.cageId = cageId;
    }

    public int getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(int coatColor) {
        this.coatColor = coatColor;
    }

    public int getLeftEarPunches() {
        return leftEarPunches;
    }

    public void setLeftEarPunches(int leftEarPunches) {
        this.leftEarPunches = leftEarPunches;
    }

    public int getRightEarPunches() {
        return rightEarPunches;
    }

    public void setRightEarPunches(int rightEarPunches) {
        this.rightEarPunches = rightEarPunches;
    }
}
