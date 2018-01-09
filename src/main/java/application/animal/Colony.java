package application.animal;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Colony {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @OneToMany
    private Set<Animal> animalSet;

    protected Colony() {

    }

    public Colony(String name, String description, Set<Animal> animalSet) {
        this.name = name;
        this.description = description;
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

    public Set<Animal> getAnimalSet() {
        return animalSet;
    }

    public void setAnimalSet(Set<Animal> animalSet) {
        this.animalSet = animalSet;
    }
}
