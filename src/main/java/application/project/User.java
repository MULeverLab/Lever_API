package application.project;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer privilege;
    @OneToMany
    private Set<UserCompetencyBridge> userCompetencyBridgeSet;

    protected User() {
    }


    public User(String firstName, String lastName, String email, String phoneNumber, Integer privilege, Set<UserCompetencyBridge> userCompetencyBridgeSet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
        this.userCompetencyBridgeSet = userCompetencyBridgeSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public Set<UserCompetencyBridge> getUserCompetencyBridgeSet() {
        return userCompetencyBridgeSet;
    }

    public void setUserCompetencyBridgeSet(Set<UserCompetencyBridge> userCompetencyBridgeSet) {
        this.userCompetencyBridgeSet = userCompetencyBridgeSet;
    }
}
