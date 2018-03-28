package application.entities.method;

import application.entities.method.VfssMethods.VfssMethod;

import javax.persistence.*;

@Entity
public class Method {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer methodType;
    private Integer methodId;
    private Long date;

    @OneToOne(cascade = {CascadeType.ALL})
    private VfssMethod vfssMethod;

    public Method() {
    }

    public Method(String name, String description, Integer methodType, Integer methodId, Long date, VfssMethod vfssMethod) {
        this.name = name;
        this.description = description;
        this.methodType = methodType;
        this.methodId = methodId;
        this.date = date;
        this.vfssMethod = vfssMethod;
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

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public VfssMethod getVfssMethod() {
        return vfssMethod;
    }

    public void setVfssMethod(VfssMethod vfssMethod) {
        this.vfssMethod = vfssMethod;
    }
}
