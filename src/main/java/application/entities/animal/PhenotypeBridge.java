package application.entities.animal;

import javax.persistence.*;

@Entity
public class PhenotypeBridge {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Phenotype phenotype;
    private Long dateAssigned;

    protected PhenotypeBridge() {
    }

    public PhenotypeBridge(Phenotype phenotype, Long dateAssigned) {
        this.phenotype = phenotype;
        this.dateAssigned = dateAssigned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

    public Long getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Long dateAssigned) {
        this.dateAssigned = dateAssigned;
    }
}
