package fhi360.covid.service;

import fhi360.covid.dto.UserLoginDto;
import fhi360.covid.exception.ResourceNotFoundException;
import fhi360.covid.model.Users;
import fhi360.covid.response.ApiResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*created by Hector
06-08-2021
*/
@Transactional
@Service
public interface UsersService {

    ApiResponse login(UserLoginDto loginDto) throws ResourceNotFoundException;

    boolean isUserAlreadyPresent(Users users);

    Users save(Users users);

//    void delete(int id);
}
