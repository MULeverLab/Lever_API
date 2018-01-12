package application.animal;

import javax.persistence.*;
import java.util.Set;

@Entity
public class BreedingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToOne(cascade = {CascadeType.ALL})
    private Animal dadBreeder;
    @OneToOne(cascade = {CascadeType.ALL})
    private Animal momBreeder;
    private Long pairFormingDate;
    private Long weanedDay;
    private Integer litterSize;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Animal> animalSet;

    protected BreedingEvent() {
    }

    public BreedingEvent(Integer id, String name, String description, Animal dadBreeder, Animal momBreeder, Long pairFormingDate, Long weanedDay, Integer litterSize, Set<Animal> animalSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dadBreeder = dadBreeder;
        this.momBreeder = momBreeder;
        this.pairFormingDate = pairFormingDate;
        this.weanedDay = weanedDay;
        this.litterSize = litterSize;
        this.animalSet = animalSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getPairFormingDate() {
        return pairFormingDate;
    }

    public void setPairFormingDate(Long pairFormingDate) {
        this.pairFormingDate = pairFormingDate;
    }

    public Long getWeanedDay() {
        return weanedDay;
    }

    public void setWeanedDay(Long weanedDay) {
        this.weanedDay = weanedDay;
    }

    public Integer getLitterSize() {
        return litterSize;
    }

    public void setLitterSize(Integer litterSize) {
        this.litterSize = litterSize;
    }

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public void setAnimalSet(Set<Animal> animalSet) {
        this.animalSet = animalSet;
    }
}