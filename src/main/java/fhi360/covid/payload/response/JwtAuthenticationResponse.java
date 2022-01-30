package fhi360.covid.payload.response;

import fhi360.covid.model.User;
import lombok.Data;

@Data
public class JwtAuthenticationResponse<T> {
    private String accessToken;
    private String tokenType = "Bearer";
    private String message = "Login Successful";
    private int status;
    private T result;
//    private String email;
//    private String phone;
//    private String password;
    private User user;
//    private List<Role> roles;
//    private Long id;

    public JwtAuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
//        this.email = email;
//        this.phone = phone;
        this.user = user;
//        this.roles = roles;
//        this.password = password;
//        this.id = id;
    }

    public JwtAuthenticationResponse(String accessToken, String tokenType, String message, int status, T result, String email, String phone, String password) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.message = message;
        this.status = status;
        this.result = result;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
    }


}
