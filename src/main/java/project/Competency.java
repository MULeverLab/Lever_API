package project;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Competency {

    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;

    public Competency(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Competency() {
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
