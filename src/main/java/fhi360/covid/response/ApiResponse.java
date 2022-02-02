package fhi360.covid.response;

import lombok.Data;

/*created by Hector Developers
06-08-2019
*/
@Data
public class ApiResponse<T> {

    private int status;
    private String message;
    private T result;
    private long id;
    private String userType;
    private String passcode;
    private String password;
    private String age;
    private String gender;
    private String maritalStatus;


    public ApiResponse(int status, String message, T result, long id, String passcode, String age, String maritalStatus, String gender, String userType, String password) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.id = id;
        this.passcode = passcode;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.gender = gender;
        this.password = password;
        this.userType=userType;
    }

}