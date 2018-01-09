package animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Genotype genotype;

    private Set<Phenotype> phenotypeSet;

    private String description;

    private int sex;

    private long dateOfBirth;

    private long dateOfDeath;

    private int causeOfDeath;

    private Colony colony;

    private int species;

    private Integer childId;

    protected Animal() {

    }

    public Animal(Genotype genotype, Set<Phenotype> phenotypeSet, String description, int sex, long dateOfBirth, long dateOfDeath, int causeOfDeath, Colony colony, int species, Integer childId) {
        this.genotype = genotype;
        this.phenotypeSet = phenotypeSet;
        this.description = description;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.causeOfDeath = causeOfDeath;
        this.colony = colony;
        this.species = species;
        this.childId = childId;
    }

    public Set<Phenotype> getPhenotypeSet() {
        return phenotypeSet;
    }

    public void setPhenotypeSet(Set<Phenotype> phenotypeSet) {
        this.phenotypeSet = phenotypeSet;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype genotype) {
        this.genotype = genotype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(long dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public int getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(int causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public Colony getColony() {
        return colony;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        this.species = species;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
