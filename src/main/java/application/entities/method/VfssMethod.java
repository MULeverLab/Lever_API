package application.entities.method;

import javax.persistence.*;

@Entity
public class VfssMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = {CascadeType.ALL})
    private VfssPositionOneMethod onePositionOne;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionOneMethod onePostionTwo;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionOneMethod onePostionThree;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionOneMethod onePositionFour;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionOneMethod onePositionFive;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod twoPositionOne;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod twoPositionTwo;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod twoPositionThree;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod twoPositionFour;

    @OneToOne (cascade = {CascadeType.ALL})
    private VfssPositionTwoMethod twoPositionFive;










}
