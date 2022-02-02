package fhi360.covid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*created by Hector Developers
06-08-2019
*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequest extends Exception{

    private static final long serialVersionUID = 1L;
    public BadRequest(String message){
        super(message);
    }
}