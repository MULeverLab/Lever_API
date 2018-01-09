package animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Phenotype {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private long dateAssigned;

    private String name;

    private String description;

    public Phenotype(){

    }

    public Phenotype(long dateAssigned, String name, String description) {
        this.dateAssigned = dateAssigned;
        this.name = name;
        this.description = description;
    }

    public Integer getID(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public long getDateAssigned(){
        return dateAssigned;
    }

    public void setDateAssigned(long dateAssigned){
        this.dateAssigned = dateAssigned;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
