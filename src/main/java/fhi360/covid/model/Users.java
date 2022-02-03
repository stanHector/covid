package fhi360.covid.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String age;
    private String passcode;
    private String gender;
    private String userType;
    private String maritalStatus;
    private String password;

    public Users() {
    }
}