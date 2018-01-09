package animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BreedingEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Animal dadBreeder;

    private Animal momBreeder;

    protected BreedingEvent() {
    }

    public BreedingEvent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getID(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Animal getDadBreeder() {
        return dadBreeder;
    }

    public void setDadBreeder(Animal dadBreeder) {
        this.dadBreeder = dadBreeder;
    }

    public Animal getMomBreeder() {
        return momBreeder;
    }

    public void setMomBreeder(Animal momBreeder) {
        this.momBreeder = momBreeder;
    }
}
