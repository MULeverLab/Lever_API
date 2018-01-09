package application.animal;

import javax.persistence.*;

@Entity
public class BreedingEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToOne
    private Animal dadBreeder;
    @OneToOne
    private Animal momBreeder;

    protected BreedingEvent() {
    }

    public BreedingEvent(String name, String description, Animal dadBreeder, Animal momBreeder) {
        this.name = name;
        this.description = description;
        this.dadBreeder = dadBreeder;
        this.momBreeder = momBreeder;
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
