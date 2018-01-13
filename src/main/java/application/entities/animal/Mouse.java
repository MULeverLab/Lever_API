package application.entities.animal;

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

    public Mouse(Integer cageId, Integer coatColor, Integer leftEarPunches, Integer rightEarPunches) {
        this.cageId = cageId;
        this.coatColor = coatColor;
        this.leftEarPunches = leftEarPunches;
        this.rightEarPunches = rightEarPunches;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCageId() {
        return cageId;
    }

    public void setCageId(Integer cageId) {
        this.cageId = cageId;
    }

    public Integer getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(Integer coatColor) {
        this.coatColor = coatColor;
    }

    public Integer getLeftEarPunches() {
        return leftEarPunches;
    }

    public void setLeftEarPunches(Integer leftEarPunches) {
        this.leftEarPunches = leftEarPunches;
    }

    public Integer getRightEarPunches() {
        return rightEarPunches;
    }

    public void setRightEarPunches(Integer rightEarPunches) {
        this.rightEarPunches = rightEarPunches;
    }
}
