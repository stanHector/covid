package fhi360.covid.serviceImpl;

import fhi360.covid.dto.UserLoginDto;
import fhi360.covid.exception.ResourceNotFoundException;
import fhi360.covid.model.Users;
import fhi360.covid.repository.UserRepository;
import fhi360.covid.response.ApiResponse;
import fhi360.covid.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;

/*created by Hector
06-08-2021
*/

@Transactional
@Service
public class UserServiceImpl implements UsersService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ApiResponse<Users> login(@Valid UserLoginDto loginDto) throws ResourceNotFoundException {
        Users user = usersRepository.findByPasscode(loginDto.getPasscode());

        if (user == null) {
            throw new ResourceNotFoundException(String.format("User with passcode %s does not exist.", loginDto.getPasscode()));
        }

        if (!BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {

            throw new RuntimeException("Password mismatch.");
        }

        return new ApiResponse<>(200, "Login success", user, user.getId(), user.getAge(), user.getGender(),user.getMaritalStatus(),user.getPasscode(), user.getPassword(), user.getUserType());
    }


    @Override
    public boolean isUserAlreadyPresent(Users user) {
        boolean isUserAlreadyExists = false;
        Users existingUser = usersRepository.getByPasscode(user.getPasscode());
        // If user is found in database, then then user already exists.
        if (existingUser != null) {
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }


    @Override
    public Users save(@RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

//    @Override
//    public void delete(int id) {
//
//    }
}