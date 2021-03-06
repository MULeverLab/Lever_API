package application.entities.project;

import javax.persistence.*;

@Entity
public class UserCompetencyBridge {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Competency competency;
    private Integer level;

    public UserCompetencyBridge() {
    }

    public UserCompetencyBridge(Competency competency, Integer level) {
        this.competency = competency;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
