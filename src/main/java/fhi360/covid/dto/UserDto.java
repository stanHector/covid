package fhi360.covid.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    private long id;
    private String age;
    private String passcode;
    private String gender;
    private String userType;
    private String maritalStatus;
    private String password;

}
