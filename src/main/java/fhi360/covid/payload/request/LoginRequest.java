package fhi360.covid.payload.request;

import fhi360.covid.enums.RoleName;

import javax.validation.constraints.NotBlank;


public class LoginRequest {

    private RoleName role;

	@NotBlank
	private String usernameOrEmail;

	@NotBlank
	private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}