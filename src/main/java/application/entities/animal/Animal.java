package application.entities.animal;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Genotype genotype;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<PhenotypeBridge> phenotypeBridgeSet;
    private String description;
    private Integer sex;
    private Long dateOfBirth;
    private Long dateOfDeath;
    private Integer causeOfDeath;

    @OneToOne(cascade = {CascadeType.ALL})
    private Mouse mouse;

    protected Animal() {

    }

    public Animal(Genotype genotype, Set<PhenotypeBridge> phenotypeBridgeSet, String description, Integer sex, Long dateOfBirth, Long dateOfDeath, Integer causeOfDeath, Mouse mouse) {
        this.genotype = genotype;
        this.phenotypeBridgeSet = phenotypeBridgeSet;
        this.description = description;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.causeOfDeath = causeOfDeath;
        this.mouse = mouse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype genotype) {
        this.genotype = genotype;
    }

    public Set<PhenotypeBridge> getPhenotypeBridgeSet() {
        return phenotypeBridgeSet;
    }

    public void setPhenotypeBridgeSet(Set<PhenotypeBridge> phenotypeBridgeSet) {
        this.phenotypeBridgeSet = phenotypeBridgeSet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Long dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Integer getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(Integer causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}
