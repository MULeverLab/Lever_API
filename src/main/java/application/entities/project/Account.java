package application.entities.project;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    //@JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String privilege;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<UserCompetencyBridge> userCompetencyBridgeSet;

    private String pictureName;

    protected Account() {
    }

    public Account(String username, String password, String firstName, String lastName, String email, String phoneNumber, String privilege, Set<UserCompetencyBridge> userCompetencyBridgeSet, String pictureName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.privilege = privilege;
        this.userCompetencyBridgeSet = userCompetencyBridgeSet;
        this.pictureName = pictureName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public Set<UserCompetencyBridge> getUserCompetencyBridgeSet() {
        return userCompetencyBridgeSet;
    }

    public void setUserCompetencyBridgeSet(Set<UserCompetencyBridge> userCompetencyBridgeSet) {
        this.userCompetencyBridgeSet = userCompetencyBridgeSet;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
