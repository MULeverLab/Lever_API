package application.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Competency {

    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;

    public Competency(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Competency() {
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
}
