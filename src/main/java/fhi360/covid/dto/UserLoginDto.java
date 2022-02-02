package fhi360.covid.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/*created by Hector Developers
06-08-2019
*/

@Data
public class UserLoginDto {

    @NotBlank
    private String passcode;

    @NotBlank
    private String password;

}